package com.example.ProjetDomotiqueAPI.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.ProjetDomotiqueAPI.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    SUPER_ADMIN(Sets.newHashSet(UTILISATEUR_READ,UTILISATEUR_WRITE,APPAREIL_READ,APPAREIL_WRITE,AUTORISATION_READ,AUTORISATION_WRITE,DONNEE_READ,DONNEE_REF_READ,DONNEE_REF_WRITE,PIECE_READ,PIECE_WRITE)),
    ADMIN(Sets.newHashSet(UTILISATEUR_READ,UTILISATEUR_WRITE,APPAREIL_READ,APPAREIL_WRITE,AUTORISATION_READ,AUTORISATION_WRITE,DONNEE_READ,DONNEE_REF_READ,DONNEE_REF_WRITE,PIECE_READ,PIECE_WRITE)),
    USER(Sets.newHashSet(APPAREIL_READ,AUTORISATION_READ,DONNEE_READ,DONNEE_REF_READ,DONNEE_REF_WRITE,PIECE_READ)),
    CONSULTANT(Sets.newHashSet(APPAREIL_READ,AUTORISATION_READ,DONNEE_READ,DONNEE_REF_READ,PIECE_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
