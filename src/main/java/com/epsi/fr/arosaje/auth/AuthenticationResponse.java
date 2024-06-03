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
public class AuthenticationResponse {
    private String token;
    private Integer id_utilisateur;
    private Role role;
}
