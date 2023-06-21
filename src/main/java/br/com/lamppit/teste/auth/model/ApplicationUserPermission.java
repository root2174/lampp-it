package br.com.lamppit.teste.auth.model;

public enum ApplicationUserPermission {
    PRODUCT_WRITE("product_write"),
    PRODUCT_READ("product_read");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
