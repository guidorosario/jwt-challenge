package com.jwt.challenge.enums;

import java.util.Arrays;

public enum RoleEnum {
    ADMIN("Admin"),
    MEMBER("Member"),
    EXTERNAL("External"),

    NONE("None");

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    private final String role;

    public static RoleEnum fromEnum(String value) {
        return Arrays.stream(values())
                .filter(at -> at.role.equals(value)).findFirst()
                .orElse(NONE);
    }
}
