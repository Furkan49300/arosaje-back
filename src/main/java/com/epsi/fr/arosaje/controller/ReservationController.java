package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.Reservation;
import com.epsi.fr.arosaje.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
@Tag(name = "Reservation API", description = "API pour gérer les réservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

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
        return reservationService.getReservationById(id);
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
        return reservationService.getReservationsByUtilisateurId(idUtilisateur);
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
        return reservationService.updateReservation(id, updates);
    }
}
