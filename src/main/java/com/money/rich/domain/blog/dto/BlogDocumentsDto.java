package com.money.rich.domain.blog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogDocumentsDto {

	private String blogname;
	private String contents;
	private String datetime;
	private String thumbnail;
	private String title;
	private String url;

	@Builder
	public BlogDocumentsDto (String blogname, String contents, String datetime, String thumbnail, String title, String url) {
		this.blogname = blogname;
		this.contents = contents;
		this.datetime = datetime;
		this.thumbnail = thumbnail;
		this.title = title;
		this.url = url;
	}

}
