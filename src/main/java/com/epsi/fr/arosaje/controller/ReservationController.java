package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.Reservation;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.ReservationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("https://arosaje-crud.vercel.app/")
@Tag(name = "Reservation API", description = "API pour gérer les réservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Operation(summary = "Obtenir une réservation par ID", description = "Récupère une réservation spécifique par son ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réservation trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "Réservation non trouvée",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@Parameter(description = "ID de la réservation à récupérer") @PathVariable Integer id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (!reservationOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Reservation reservation = reservationOptional.get();
        return ResponseEntity.ok(reservation);
    }

    @Operation(summary = "Obtenir des réservations par ID d'utilisateur", description = "Récupère toutes les réservations associées à un utilisateur spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des réservations trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "204", description = "Aucune réservation trouvée",
                    content = @Content)
    })
    @GetMapping("/user/{idUtilisateur}")
    public ResponseEntity<List<Reservation>> getReservationsByUtilisateurId(@Parameter(description = "ID de l'utilisateur dont les réservations sont à récupérer") @PathVariable Integer idUtilisateur) {
        List<Reservation> reservations = reservationRepository.findByUtilisateurId(idUtilisateur);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Mettre à jour une réservation", description = "Met à jour les informations d'une réservation spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Réservation mise à jour avec succès",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Réservation non trouvée",
                    content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateReservation(@Parameter(description = "ID de la réservation à mettre à jour") @PathVariable Integer id, @RequestBody Map<String, Object> updates) {
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
