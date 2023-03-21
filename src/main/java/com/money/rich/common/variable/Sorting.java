package com.money.rich.common.variable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Sorting {
    ACCURACY("accuracy", "sim"), RECENCY("recency", "date");

    private final String kText;
    private final String nText;

}
