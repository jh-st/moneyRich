package com.money.rich.domain.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BlogMetaDto {

	private Integer total;
	private Integer size;
	private Integer page;

}
