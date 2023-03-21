package com.money.rich.domain.blog.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.rich.common.response.ResponseResult;
import com.money.rich.common.utility.ResponseUtils;
import com.money.rich.domain.blog.dto.BlogSearchRequestVo;
import com.money.rich.domain.blog.dto.BlogSearchResponseVo;
import com.money.rich.domain.blog.service.BlogSearchService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/v1/search", name = "블로그검색 v1.0")
@RestController
@RequiredArgsConstructor
public class BlogSearchController {

	private final BlogSearchService blogSearchService;

	@GetMapping(value = "blogs", name = "블로그 및 인기검색어 조회 검색", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseResult<BlogSearchResponseVo> blogs(@Valid final BlogSearchRequestVo request) {
		return ResponseUtils.getResult(blogSearchService.blogs(request));
	}

}
