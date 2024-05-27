package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.Reservation;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (!reservationOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = reservationOptional.get();
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/user/{idUtilisateur}")
    public ResponseEntity<List<Reservation>> getReservationsByUtilisateurId(@PathVariable Integer idUtilisateur) {
        List<Reservation> reservations = reservationRepository.findByUtilisateurId(idUtilisateur);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
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
