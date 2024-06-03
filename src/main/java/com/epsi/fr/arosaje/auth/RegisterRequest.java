package com.epsi.fr.arosaje.auth;

import com.epsi.fr.arosaje.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String prenom;
    private String nom;
    private String mail;
    private String password;
    private Role role;
}
