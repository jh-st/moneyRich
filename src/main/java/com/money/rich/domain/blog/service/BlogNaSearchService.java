package com.money.rich.domain.blog.service;

import static com.money.rich.common.variable.Provider.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.money.rich.common.response.SearchApiResult;
import com.money.rich.common.utility.SearchUtils;
import com.money.rich.common.variable.Sorting;
import com.money.rich.domain.blog.dto.BlogSearchRequestVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogNaSearchService {

	private final static String CLIENT_ID = "uXBDju4YhOs6Sw87Dn23";
	private final static String CLIENT_SECRET = "8Gd7t8bzR8";
	private final static String BLOG_SEARCH_API = "https://openapi.naver.com/v1/search/blog.json";

	public SearchApiResult nApiResponse(final BlogSearchRequestVo request) {
		try {
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
			requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);

			final String text = URLEncoder.encode(ObjectUtils.nullSafeToString(request.getQuery()), StandardCharsets.UTF_8);
			Optional<Sorting> sorting = Arrays.stream(Sorting.values()).filter(e -> request.getSort().equals(e.getKText())).findFirst();
			final String sort = sorting.isPresent() ? sorting.get().getNText() : "";
			final String param = "?query="+text
				+"&display="+request.getSize()
				+"&start="+request.getPage()
				+"&sort="+sort;

			return SearchUtils.get(NAVER.getText(), BLOG_SEARCH_API+param, requestHeaders);

		} catch (Exception e) {
			e.printStackTrace();
			return new SearchApiResult();
		}
	}

}
