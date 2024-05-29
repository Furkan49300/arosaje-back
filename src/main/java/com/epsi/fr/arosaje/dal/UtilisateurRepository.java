package com.epsi.fr.arosaje.dal;

import com.epsi.fr.arosaje.bo.Role;
import com.epsi.fr.arosaje.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("https://arosaje-crud.vercel.app/")
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    Optional<Utilisateur> findByMail(String mail);

    @Query("SELECT COUNT(u) FROM Utilisateur u WHERE u.role = :role")
    long countUtilisateursByRole(Role role);
}
