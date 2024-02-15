package com.dev.scaffdone.core.user;

import lombok.Getter;

@Getter
public enum Role {
    USER("user"), ADMIN("admin");
    private final String roleName;
    private Role(String roleName) {
        this.roleName = roleName;
    }

}