package com.money.rich.domain.blog.service;

import static com.money.rich.common.utility.JsonUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.money.rich.common.response.SearchApiResult;
import com.money.rich.domain.blog.dto.BlogSearchRequestVo;
import com.money.rich.domain.blog.dto.BlogSearchResponseVo;
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

	public BlogSearchResponseVo blogs(final BlogSearchRequestVo request) {
		BlogSearchResponseVo result = new BlogSearchResponseVo();
		applyKeyword(request);
		final SearchApiResult blogs = getBlogs(request);
		result.setProvider(blogs.getProvider());
		result.setBlogs(convertMapByBlog(blogs));
		result.setKeywords(keywordService.getsTop10());
		return result;
	}

	public SearchApiResult getBlogs(final BlogSearchRequestVo request) {
		SearchApiResult blog = blogKaSearchService.kApiResponse(request);
		if (blog.getResponseCode() == 500) {
			blog = blogNaSearchService.nApiResponse(request);
		}
		return blog;
	}

	public void applyKeyword(final BlogSearchRequestVo request) {
		Optional<PopularKeywordEntity> byKeyword = keywordService.getByKeyword(request.getQuery());
		if (byKeyword.isPresent()) {
			keywordService.hits(byKeyword.get());
		} else {
			keywordService.post(PopularKeywordSaveDto.builder()
				.keyword(request.getQuery()).hit(1).build());
		}
	}

	public Map<String, Object> convertMapByBlog(SearchApiResult blogs) {
		try {
			return (!ObjectUtils.isEmpty(blogs.getResponse())) ? getStringToMap(blogs.getResponse()) : new HashMap<>();
		} catch (Exception e) {
			return new HashMap<>();
		}
	}
}
