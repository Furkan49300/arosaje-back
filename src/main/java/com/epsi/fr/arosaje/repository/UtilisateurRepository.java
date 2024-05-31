package com.epsi.fr.arosaje.repository;

import com.epsi.fr.arosaje.bo.Role;
import com.epsi.fr.arosaje.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@RepositoryRestResource
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByMail(String mail);

    @Query("SELECT COUNT(u) FROM Utilisateur u WHERE u.role = :role")
    long countUtilisateursByRole(Role role);
}
