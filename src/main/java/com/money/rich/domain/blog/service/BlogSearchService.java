package com.money.rich.domain.blog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.money.rich.common.response.SearchApiResult;
import com.money.rich.common.variable.Provider;
import com.money.rich.domain.blog.dto.BlogDocumentsDto;
import com.money.rich.domain.blog.dto.BlogKMetaDto;
import com.money.rich.domain.blog.dto.BlogMetaDto;
import com.money.rich.domain.blog.dto.BlogSearchRequestDto;
import com.money.rich.domain.blog.dto.BlogSearchResponseDto;
import com.money.rich.domain.blog.dto.BlogsDto;
import com.money.rich.domain.popularKeyword.dto.PopularKeywordSaveDto;
import com.money.rich.domain.popularKeyword.entity.PopularKeywordEntity;
import com.money.rich.domain.popularKeyword.service.PopularKeywordService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogSearchService {

	private final PopularKeywordService keywordService;
	private final BlogKaSearchService blogKaSearchService;
	private final BlogNaSearchService blogNaSearchService;

	public BlogSearchResponseDto blogs(final BlogSearchRequestDto request) {
		applyKeyword(request);
		return BlogSearchResponseDto.builder()
			.blogs(responseConvert(request))
			.keywords(keywordService.getsTop10())
			.build();
	}

	public SearchApiResult getBlogs(final BlogSearchRequestDto request) {
		SearchApiResult blog = blogKaSearchService.kApiResponse(request);
		boolean isError = getError(blog.getResponseCode());
		if (isError) {
			blog = blogNaSearchService.nApiResponse(request);
		}
		if (isError) {
			return new SearchApiResult();
		}
		return blog;
	}

	public void applyKeyword(final BlogSearchRequestDto request) {
		Optional<PopularKeywordEntity> byKeyword = keywordService.getByKeyword(request.getQuery());
		if (byKeyword.isPresent()) {
			keywordService.hits(byKeyword.get());
		} else {
			keywordService.post(PopularKeywordSaveDto.builder()
				.keyword(request.getQuery())
				.hit(1)
				.build());
		}
	}

	public BlogsDto responseConvert(final BlogSearchRequestDto request) {
		SearchApiResult searchApiResult = getBlogs(request);

		if (ObjectUtils.isEmpty(searchApiResult.getResponse())) {
			return new BlogsDto();
		}

		final String provider = searchApiResult.getProvider();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> convertMap = objectMapper.readValue(searchApiResult.getResponse(), Map.class);

			if (Provider.KAKAO.getText().equals(provider)) {
				BlogKMetaDto meta = objectMapper.convertValue(convertMap.get("meta"), BlogKMetaDto.class);
				List<Map<String, String>> documentMaps = getDocumentMaps(convertMap, "documents");
				List<BlogDocumentsDto> documents = getDocuments(provider, documentMaps);

				return BlogsDto.builder()
					.meta(BlogMetaDto.builder()
						.total(meta.getTotal_count())
						.size(request.getSize())
						.page(request.getPage()).build())
					.documents(documents)
					.build();
			} else if (Provider.NAVER.getText().equals(provider)) {
				BlogMetaDto meta = BlogMetaDto.builder()
					.page((Integer)convertMap.get("start"))
					.size((Integer)convertMap.get("display"))
					.total((Integer)convertMap.get("total"))
					.build();
				List<Map<String, String>> documentMaps = getDocumentMaps(convertMap, "items");
				List<BlogDocumentsDto> documents = getDocuments(provider, documentMaps);

				return BlogsDto.builder()
					.meta(meta)
					.documents(documents)
					.build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlogsDto();
	}

	public List<Map<String, String>> getDocumentMaps(Map<String, Object> convertMap, String key) {
		return (List<Map<String, String>>)convertMap.get(key);
	}

	public List<BlogDocumentsDto> getDocuments(String provider, List<Map<String, String>> documentMaps) {
		if (Provider.KAKAO.getText().equals(provider)) {
			return documentMaps.stream().map(doc -> BlogDocumentsDto.builder()
				.blogname(doc.get("blogname"))
				.url(doc.get("url"))
				.title(doc.get("title"))
				.datetime(doc.get("datetime"))
				.thumbnail(doc.get("thumbnail"))
				.contents(doc.get("contents"))
				.build()).collect(Collectors.toList());
		} else if (Provider.NAVER.getText().equals(provider)) {
			return documentMaps.stream().map(doc -> BlogDocumentsDto.builder()
				.blogname(doc.get("bloggername"))
				.url(doc.get("link"))
				.title(doc.get("title"))
				.datetime(doc.get("postdate"))
				.thumbnail("")
				.contents(doc.get("description"))
				.build()).collect(Collectors.toList());
		} else {
			return new ArrayList<>();
		}
	}

	private boolean getError(Integer responseCode) {
		return responseCode == 500;
	}
}
