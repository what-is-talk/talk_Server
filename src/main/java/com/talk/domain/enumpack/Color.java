package com.talk.domain.enumpack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {
    RED("red"),
    BLUE("blue"),
    PINK("pink"),
    ORANGE("orange"),
    YELLOW("yellow");

    private final String value;
}
