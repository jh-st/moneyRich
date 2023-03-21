package com.money.rich.domain.blog.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.money.rich.common.annotation.Enum;
import com.money.rich.common.variable.Sorting;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSearchRequestVo {

	@NotEmpty(message = "검색어를 입력해 주세요.")
	private String query;

	@Enum(enumClass = Sorting.class, ignoreCase = true, message = "잘못된 정렬값 입니다.")
	private String sort = "accuracy";

	@Max(50) @Min(1)
	protected Integer page = 1;

	@Max(50) @Min(1)
	protected Integer size = 10;

}
