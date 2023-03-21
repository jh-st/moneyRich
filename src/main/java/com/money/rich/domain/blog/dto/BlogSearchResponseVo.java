package com.money.rich.domain.blog.dto;

import java.util.List;
import java.util.Map;

import com.money.rich.domain.popularKeyword.dto.PopularKeywordDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BlogSearchResponseVo {

	private String provider;
	private Map<String, Object> blogs;
	private List<PopularKeywordDto> keywords;

}
