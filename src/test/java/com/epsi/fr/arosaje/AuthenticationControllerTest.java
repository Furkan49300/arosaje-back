package com.epsi.fr.arosaje;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.epsi.fr.arosaje.auth.*;
import com.epsi.fr.arosaje.Entity.Role;
import com.epsi.fr.arosaje.Entity.Utilisateur;
import com.epsi.fr.arosaje.config.JwtService;
import com.epsi.fr.arosaje.repository.UtilisateurRepository;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthenticationControllerTest {

    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest("John", "Doe", "john.doe@example.com", "password", Role.USER); // Assuming Role enum is defined with USER role
        Utilisateur user = Utilisateur.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .mail(request.getMail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
        String token = "generated_jwt_token"; // Mocking the generated JWT token

        AuthenticationService authService = mock(AuthenticationService.class);
        when(authService.register(request)).thenReturn(AuthenticationResponse.builder().token(token).build());

        AuthenticationController controller = new AuthenticationController(authService);

        ResponseEntity<AuthenticationResponse> responseEntity = controller.register(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(token, responseEntity.getBody().getToken());
    }

    @Test
    public void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest("john.doe@example.com", "password");

        Utilisateur user = Utilisateur.builder()
                .prenom("John")
                .nom("Doe")
                .mail("john.doe@example.com")
                .password("encoded_password") // Assuming password is encoded
                .role(Role.USER)
                .build();
        String token = "generated_jwt_token"; // Mocking the generated JWT token

        UtilisateurRepository repository = mock(UtilisateurRepository.class);
        when(repository.findByMail(request.getMail())).thenReturn(java.util.Optional.of(user));

        JwtService jwtService = mock(JwtService.class);
        when(jwtService.generateToken(user)).thenReturn(token);

        AuthenticationService authService = new AuthenticationService(repository, null, jwtService, null);
        AuthenticationController controller = new AuthenticationController(authService);

        ResponseEntity<AuthenticationResponse> responseEntity = controller.authenticate(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(token, responseEntity.getBody().getToken());
    }
}
