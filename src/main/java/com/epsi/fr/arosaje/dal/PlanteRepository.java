package com.epsi.fr.arosaje.dal;

import com.epsi.fr.arosaje.bo.Plante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = "https://arosaje-crud.vercel.app")
public interface PlanteRepository extends JpaRepository<Plante,Integer> {


    @Query("SELECT p FROM Plante p WHERE p.reservation.id_reservation = :reservationId")
    List<Plante> findByReservationId(@Param("reservationId") Integer reservationId);
    @Query("SELECT p FROM Plante p WHERE p.utilisateur.id_utilisateur = :utilisateurId")
    List<Plante> findByUtilisateurId(@Param("utilisateurId") Integer utilisateurId);

    @Query("SELECT p FROM Plante p WHERE p.id_plante = :id")
    Plante findPlanteById(@Param("id") int id);

    @Query("SELECT COUNT(p) FROM Plante p")
    long countPlantes();

    @Query("SELECT p FROM Plante p WHERE p.creele > :date")
    List<Plante> findPlantesCreatedAfter(@Param("date") Date date);





}
