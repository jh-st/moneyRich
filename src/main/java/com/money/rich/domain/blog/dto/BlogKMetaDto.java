package com.money.rich.domain.blog.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogKMetaDto {

	@JsonAlias(value = "is_end")
	private Boolean is_end;
	private Integer pageable_count;
	private Integer total_count;

}
