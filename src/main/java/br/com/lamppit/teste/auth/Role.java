package br.com.lamppit.teste.auth;

import com.google.common.collect.Sets;

import java.util.Set;

import static br.com.lamppit.teste.auth.ApplicationUserPermission.*;

public enum Role {
    CUSTOMER(Sets.newHashSet()),
    COMPANY(Sets.newHashSet(PRODUCT_WRITE, PRODUCT_READ)),
    DELIVERY_PERSON(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    Role(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
