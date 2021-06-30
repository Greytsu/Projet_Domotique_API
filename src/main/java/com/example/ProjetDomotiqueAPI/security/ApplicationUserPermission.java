package com.example.ProjetDomotiqueAPI.security;

public enum ApplicationUserPermission {
    UTILISATEUR_READ("utilisateur:read"),
    UTILISATEUR_WRITE("utilisateur:write"),
    APPAREIL_READ("appareil:read"),
    APPAREIL_WRITE("appareil:write"),
    AUTORISATION_READ("autorisation:read"),
    AUTORISATION_WRITE("autorisation:write"),
    DONNEE_READ("donnee:read"),
    DONNEE_REF_READ("donneeRef:read"),
    DONNEE_REF_WRITE("donneeRef:write"),
    PIECE_READ("piece:read"),
    PIECE_WRITE("piece:write"),
    ;

    private final String permissions;

    ApplicationUserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
