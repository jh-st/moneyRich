package com.money.rich.domain.popularKeyword.dto;

import com.money.rich.domain.popularKeyword.entity.PopularKeywordEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularKeywordSaveDto {

	private String keyword;
	private int hit;

	@Builder
	public PopularKeywordSaveDto(final String keyword, final int hit) {
		this.keyword = keyword;
		this.hit = hit;
	}

	public PopularKeywordEntity toEntity() {
		return PopularKeywordEntity.builder()
			.keyword(this.keyword)
			.hit(this.hit)
			.build();
	}

}
