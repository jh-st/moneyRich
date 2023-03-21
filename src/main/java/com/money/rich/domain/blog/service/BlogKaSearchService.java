package com.money.rich.domain.blog.service;

import static com.money.rich.common.variable.Provider.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.money.rich.common.response.SearchApiResult;
import com.money.rich.common.utility.SearchUtils;
import com.money.rich.domain.blog.dto.BlogSearchRequestVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogKaSearchService {

	private final static String AUTHORIZATION = "593f030fcad658d7dfa0909b0b9f2823";
	private final static String BLOG_SEARCH_API = "https://dapi.kakao.com/v2/search/blog";

	public SearchApiResult kApiResponse(final BlogSearchRequestVo request) {
		try {
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("Authorization", "KakaoAK "+AUTHORIZATION);


			final String text = URLEncoder.encode(ObjectUtils.nullSafeToString(request.getQuery()), StandardCharsets.UTF_8);
			final String sort = request.getSort();
			final String param = "?query="+text
				+"&sort="+sort
				+"&page="+request.getPage()
				+"&size="+request.getSize();



			return SearchUtils.get(KAKAO.getText(), BLOG_SEARCH_API+param, requestHeaders);

		} catch (Exception e) {
			return new SearchApiResult();
		}
	}

}
