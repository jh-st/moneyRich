package com.money.rich.common.variable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {
    KAKAO("KAKAO"), NAVER("NAVER");

    private final String text;

}
