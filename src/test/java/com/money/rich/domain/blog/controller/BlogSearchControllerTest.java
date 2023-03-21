package com.money.rich.domain.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BlogSearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private final String API_URI = "/v1/search/blogs";
	private final String KEYWORD = "대한민국";
	private final String SORTING = "accuracy";

	@Test
	@DisplayName("기본블로그조회_성공케이스")
	public void k_blogs_success() throws Exception {
		mockMvc.perform(get(API_URI)
				.param("query", KEYWORD)
				.param("sort", SORTING)
				.param("size", String.valueOf(10))
				.param("page",String.valueOf(1)))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.dateTime").exists())
			.andExpect(jsonPath("$.data").exists())
			.andExpect(jsonPath("$.data.provider").exists())
			.andExpect(jsonPath("$.data.provider").isString())

			.andExpect(jsonPath("$.data.keywords").exists())
			.andExpect(jsonPath("$.data.keywords[0].keyword").exists())
			.andExpect(jsonPath("$.data.keywords[0].keyword").isString())
			.andExpect(jsonPath("$.data.keywords[0].hit").exists())
			.andExpect(jsonPath("$.data.keywords[0].hit").isNumber())

			.andExpect(jsonPath("$.data.blogs").exists())
			.andExpect(jsonPath("$.data.blogs.documents").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].blogname").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].blogname").isString())
			.andExpect(jsonPath("$.data.blogs.documents[0].contents").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].contents").isString())
			.andExpect(jsonPath("$.data.blogs.documents[0].datetime").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].datetime").isString())
			.andExpect(jsonPath("$.data.blogs.documents[0].thumbnail").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].thumbnail").isString())
			.andExpect(jsonPath("$.data.blogs.documents[0].title").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].title").isString())
			.andExpect(jsonPath("$.data.blogs.documents[0].url").exists())
			.andExpect(jsonPath("$.data.blogs.documents[0].url").isString())
			.andExpect(jsonPath("$.data.blogs.meta").exists())
			.andExpect(jsonPath("$.data.blogs.meta.is_end").exists())
			.andExpect(jsonPath("$.data.blogs.meta.is_end").isBoolean())
			.andExpect(jsonPath("$.data.blogs.meta.pageable_count").exists())
			.andExpect(jsonPath("$.data.blogs.meta.pageable_count").isNumber())
			.andExpect(jsonPath("$.data.blogs.meta.total_count").exists())
			.andExpect(jsonPath("$.data.blogs.meta.total_count").isNumber())
			.andExpect(status().isOk())
		;
	}

	// 기본블로그 오류 시 테스트케이스
	// @Test
	// @DisplayName("대체블로그조회_기본블로그오류시_성공케이스")
	// public void n_blogs_success() throws Exception {
	// 	mockMvc.perform(get(API_URI)
	// 			.param("query", KEYWORD)
	// 			.param("sort", SORTING)
	// 			.param("size", String.valueOf(10))
	// 			.param("page",String.valueOf(1)))
	// 		.andDo(MockMvcResultHandlers.print())
	// 		.andExpect(jsonPath("$.status").exists())
	// 		.andExpect(jsonPath("$.dateTime").exists())
	// 		.andExpect(jsonPath("$.data").exists())
	// 		.andExpect(jsonPath("$.data.provider").exists())
	// 		.andExpect(jsonPath("$.data.provider").isString())
	//
	// 		.andExpect(jsonPath("$.data.keywords").exists())
	// 		.andExpect(jsonPath("$.data.keywords[0].keyword").exists())
	// 		.andExpect(jsonPath("$.data.keywords[0].keyword").isString())
	// 		.andExpect(jsonPath("$.data.keywords[0].hit").exists())
	// 		.andExpect(jsonPath("$.data.keywords[0].hit").isNumber())
	//
	// 		.andExpect(jsonPath("$.data.blogs").exists())
	// 		.andExpect(jsonPath("$.data.blogs.lastBuildDate").exists())
	// 		.andExpect(jsonPath("$.data.blogs.lastBuildDate").isString())
	// 		.andExpect(jsonPath("$.data.blogs.total").exists())
	// 		.andExpect(jsonPath("$.data.blogs.total").isNumber())
	// 		.andExpect(jsonPath("$.data.blogs.start").exists())
	// 		.andExpect(jsonPath("$.data.blogs.start").isNumber())
	// 		.andExpect(jsonPath("$.data.blogs.display").exists())
	// 		.andExpect(jsonPath("$.data.blogs.display").isNumber())
	// 		.andExpect(jsonPath("$.data.blogs.items").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].title").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].title").isString())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].link").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].link").isString())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].description").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].description").isString())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].bloggername").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].bloggername").isString())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].bloggerlink").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].bloggerlink").isString())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].postdate").exists())
	// 		.andExpect(jsonPath("$.data.blogs.items[0].postdate").isString())
	// 		.andExpect(status().isOk())
	// 	;
	// }

	@Test
	@DisplayName("기본블로그조회_실패케이스_query미입력")
	public void k_blogs_fail1() throws Exception {
		mockMvc.perform(get(API_URI)
				.param("query", "")
				.param("sort", SORTING)
				.param("size", String.valueOf(10))
				.param("page",String.valueOf(1)))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.status").isNumber())
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.dateTime").exists())
			.andExpect(jsonPath("$.dateTime").isString())
			.andExpect(jsonPath("$.message").exists())
			.andExpect(jsonPath("$.message").isString())
			.andExpect(jsonPath("$.message").value("검색어를 입력해 주세요."))
			.andExpect(jsonPath("$.detail").exists())
			.andExpect(jsonPath("$.detail").isString())
			.andExpect(jsonPath("$.detail").value("uri="+API_URI))
			.andExpect(status().isBadRequest())
		;
	}

	@Test
	@DisplayName("기본블로그조회_실패케이스_sort오입력")
	public void k_blogs_fail2() throws Exception {
		mockMvc.perform(get(API_URI)
				.param("query", KEYWORD)
				.param("sort", "developer")
				.param("size", String.valueOf(10))
				.param("page",String.valueOf(1)))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.status").isNumber())
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.dateTime").exists())
			.andExpect(jsonPath("$.dateTime").isString())
			.andExpect(jsonPath("$.message").exists())
			.andExpect(jsonPath("$.message").isString())
			.andExpect(jsonPath("$.message").value("잘못된 정렬값 입니다."))
			.andExpect(jsonPath("$.detail").exists())
			.andExpect(jsonPath("$.detail").isString())
			.andExpect(jsonPath("$.detail").value("uri="+API_URI))
			.andExpect(status().isBadRequest())
		;
	}

	@Test
	@DisplayName("기본블로그조회_실패케이스_size,page값초과")
	public void k_blogs_fail3() throws Exception {
		mockMvc.perform(get(API_URI)
				.param("query", KEYWORD)
				.param("sort", SORTING)
				.param("size", String.valueOf(100))
				.param("page",String.valueOf(1)))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.status").isNumber())
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.dateTime").exists())
			.andExpect(jsonPath("$.dateTime").isString())
			.andExpect(jsonPath("$.message").exists())
			.andExpect(jsonPath("$.message").isString())
			.andExpect(jsonPath("$.detail").exists())
			.andExpect(jsonPath("$.detail").isString())
			.andExpect(jsonPath("$.detail").value("uri="+API_URI))
			.andExpect(status().isBadRequest())
		;

		mockMvc.perform(get(API_URI)
				.param("query", KEYWORD)
				.param("sort", SORTING)
				.param("size", String.valueOf(1))
				.param("page",String.valueOf(100)))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(jsonPath("$.status").exists())
			.andExpect(jsonPath("$.status").isNumber())
			.andExpect(jsonPath("$.status").value(400))
			.andExpect(jsonPath("$.dateTime").exists())
			.andExpect(jsonPath("$.dateTime").isString())
			.andExpect(jsonPath("$.message").exists())
			.andExpect(jsonPath("$.message").isString())
			.andExpect(jsonPath("$.detail").exists())
			.andExpect(jsonPath("$.detail").isString())
			.andExpect(jsonPath("$.detail").value("uri="+API_URI))
			.andExpect(status().isBadRequest())
		;
	}

	// Exception 발생 시 테스트케이스
	// @Test
	// @DisplayName("기본블로그조회_실패케이스_500,400error")
	// public void k_blogs_fail4() throws Exception {
	// 	mockMvc.perform(get(API_URI)
	// 			.param("query", KEYWORD)
	// 			.param("sort", SORTING)
	// 			.param("size", String.valueOf(10))
	// 			.param("page",String.valueOf(1)))
	// 		.andDo(MockMvcResultHandlers.print())
	// 		.andExpect(jsonPath("$.status").exists())
	// 		.andExpect(jsonPath("$.status").isNumber())
	// 		.andExpect(jsonPath("$.status").value(500))
	// 		.andExpect(jsonPath("$.dateTime").exists())
	// 		.andExpect(jsonPath("$.dateTime").isString())
	// 		.andExpect(jsonPath("$.detail").exists())
	// 		.andExpect(jsonPath("$.detail").isString())
	// 		.andExpect(jsonPath("$.detail").value("uri="+API_URI))
	// 		.andExpect(status().isInternalServerError())
	// 	;
	//
	// 	mockMvc.perform(get(API_URI)
	// 			.param("query", KEYWORD)
	// 			.param("sort", SORTING)
	// 			.param("size", String.valueOf(10))
	// 			.param("page",String.valueOf(1)))
	// 		.andDo(MockMvcResultHandlers.print())
	// 		.andExpect(jsonPath("$.status").exists())
	// 		.andExpect(jsonPath("$.status").isNumber())
	// 		.andExpect(jsonPath("$.status").value(400))
	// 		.andExpect(jsonPath("$.dateTime").exists())
	// 		.andExpect(jsonPath("$.dateTime").isString())
	// 		.andExpect(jsonPath("$.error").exists())
	// 		.andExpect(jsonPath("$.error").isString())
	// 		.andExpect(jsonPath("$.detail").exists())
	// 		.andExpect(jsonPath("$.detail").isString())
	// 		.andExpect(jsonPath("$.detail").value("uri="+API_URI))
	// 		.andExpect(status().isBadRequest())
	// 	;
	// }

}