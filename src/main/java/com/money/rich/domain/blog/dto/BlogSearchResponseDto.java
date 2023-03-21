package com.money.rich.domain.blog.dto;

import java.util.List;

import com.money.rich.domain.popularKeyword.dto.PopularKeywordDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogSearchResponseDto {

	private BlogsDto blogs;
	private List<PopularKeywordDto> keywords;

	@Builder
	public BlogSearchResponseDto (BlogsDto blogs, List<PopularKeywordDto> keywords) {
		this.blogs = blogs;
		this.keywords = keywords;
	}

}
