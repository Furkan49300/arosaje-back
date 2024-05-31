package com.epsi.fr.arosaje;

import com.epsi.fr.arosaje.bo.Role;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilisateurRepositoryTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testFindByMail() {
        // Création d'un utilisateur fictif
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId_utilisateur(1);
        utilisateur.setPrenom("John");
        utilisateur.setNom("Doe");
        utilisateur.setMail("john.doe@example.com");
        utilisateur.setPassword("password");
        utilisateur.setRole(Role.USER);

        // Définition du comportement du mock du repository
        when(utilisateurRepository.findByMail("john.doe@example.com")).thenReturn(Optional.of(utilisateur));

        // Appel de la méthode du repository
        Optional<Utilisateur> retrievedUtilisateur = utilisateurRepository.findByMail("john.doe@example.com");

        // Vérification
        assertEquals(utilisateur, retrievedUtilisateur.orElse(null));
    }


    @Test
    public void testCountUtilisateursByRole() {
        // Mock des données de test
        Role role = Role.USER;
        long expectedCount = 5L;

        // Définition du comportement du mock du repository
        when(utilisateurRepository.countUtilisateursByRole(role)).thenReturn(expectedCount);

        // Appel de la méthode du repository
        long count = utilisateurRepository.countUtilisateursByRole(role);

        // Vérification
        assertEquals(expectedCount, count);
    }
}
