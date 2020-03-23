package com.czerwo.armybuilder.security;

public enum ApplicationUserPermission {
    USER_ROSTER("user:roster"),
    ADMIN_PANEL("admin:admin");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
