package com.money.rich.domain.blog.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogsDto {

	private BlogMetaDto meta;
	private List<BlogDocumentsDto> documents;

	@Builder
	public BlogsDto (BlogMetaDto meta, List<BlogDocumentsDto> documents) {
		this.meta = meta;
		this.documents = documents;
	}

}
