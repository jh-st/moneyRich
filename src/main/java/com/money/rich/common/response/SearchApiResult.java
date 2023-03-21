package com.money.rich.common.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SearchApiResult {

	private String provider;
	private int responseCode;
	private String response;

	@Builder
	public SearchApiResult(final int responseCode, final String response) {
		this.responseCode = responseCode;
		this.response = response;
	}

}
