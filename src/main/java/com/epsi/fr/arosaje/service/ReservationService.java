package com.epsi.fr.arosaje.service;

import com.epsi.fr.arosaje.Entity.Reservation;
import com.epsi.fr.arosaje.Entity.Utilisateur;
import com.epsi.fr.arosaje.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseEntity<Reservation> getReservationById(Integer id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (!reservationOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = reservationOptional.get();
        return ResponseEntity.ok(reservation);
    }

    public ResponseEntity<List<Reservation>> getReservationsByUtilisateurId(Integer idUtilisateur) {
        List<Reservation> reservations = reservationRepository.findByUtilisateurId(idUtilisateur);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }

    public ResponseEntity<?> updateReservation(Integer id, Map<String, Object> updates) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (!reservationOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = reservationOptional.get();

        if (updates.containsKey("id_utilisateur")) {
            Integer newUserId = (Integer) updates.get("id_utilisateur");
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId_utilisateur(newUserId);
            reservation.setUtilisateur(utilisateur);
        }

        if (updates.containsKey("etat")) {
            Boolean newEtat = (Boolean) updates.get("etat");
            reservation.setEtat(newEtat);
        }

        reservationRepository.save(reservation);
        return ResponseEntity.ok().build();
    }
}
