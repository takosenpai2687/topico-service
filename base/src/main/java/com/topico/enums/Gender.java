package com.topico.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// ISO/IEC 5218
@Getter
public enum Gender {
    NOT_KNOWN(0, "Not known"),
    MALE(1, "Male"),
    FEMALE(2, "Female"),
    NOT_APPLICABLE(9, "Not applicable");

    private final int code;
    private final String description;

    Gender(int code, String description) {
        this.code = code;
        this.description = description;
    }
}