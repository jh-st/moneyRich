package com.money.rich.domain.popularKeyword.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "POPULAR_KEYWORD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PopularKeywordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "KEYWORD")
	private String keyword;

	@Column(name = "HIT")
	private int hit;

	@Builder
	public PopularKeywordEntity (String keyword, int hit) {
		this.keyword = keyword;
		this.hit = hit;
	}

	public PopularKeywordEntity updateHit() {
		this.hit++;
		return this;
	}

}
