package com.epsi.fr.arosaje.repository;

import com.epsi.fr.arosaje.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.utilisateur.id_utilisateur = :utilisateurId")
    List<Reservation> findByUtilisateurId(@Param("utilisateurId") Integer utilisateurId);
}
