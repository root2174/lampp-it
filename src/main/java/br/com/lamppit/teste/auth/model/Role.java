package br.com.lamppit.teste.auth.model;

import com.google.common.collect.Sets;

import java.util.Set;

public enum Role {
    CUSTOMER(Sets.newHashSet()),
    COMPANY(Sets.newHashSet()),
    DELIVERY_PERSON(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    Role(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
