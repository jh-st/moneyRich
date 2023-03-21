package com.money.rich.domain.popularKeyword.dto;

import com.money.rich.domain.popularKeyword.entity.PopularKeywordEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularKeywordDto {

	private String keyword;
	private int hit;

	@Builder
	public PopularKeywordDto (final PopularKeywordEntity popularKeyword) {
		this.keyword = popularKeyword.getKeyword();
		this.hit = popularKeyword.getHit();
	}

	public static PopularKeywordDto of(final PopularKeywordEntity popularKeyword) {
		return PopularKeywordDto.builder()
			.popularKeyword(popularKeyword)
			.build();
	}

}
