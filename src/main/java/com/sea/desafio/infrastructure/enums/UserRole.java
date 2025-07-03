package com.sea.desafio.infrastructure.enums;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRol() {
        return role;
    }
}
