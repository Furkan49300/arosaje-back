package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.Plante;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.PlanteRepository;
import com.epsi.fr.arosaje.dal.UtilisateurRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plantes")
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public class PlanteController {

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Operation(summary = "Get plants by reservation ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the plants",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "204", description = "No plants found")
    })
    @GetMapping("/reservation/{idReservation}")
    public ResponseEntity<List<Plante>> getPlantesByReservationId(@PathVariable Integer idReservation) {
        List<Plante> plantes = planteRepository.findByReservationId(idReservation);
        if (plantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plantes);
    }

    @Operation(summary = "Create a new plant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<?> createPlante(@RequestBody Plante plante) {
        if (plante.getUtilisateur() != null && plante.getUtilisateur().getId_utilisateur() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(plante.getUtilisateur().getId_utilisateur())
                    .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
            plante.setUtilisateur(utilisateur);
        } else {
            return ResponseEntity.badRequest().body("Utilisateur ID is required");
        }

        Plante savedPlante = planteRepository.save(plante);
        return ResponseEntity.ok(savedPlante);
    }

    @Operation(summary = "Get all plants")
    @ApiResponse(responseCode = "200", description = "Found all plants",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) })
    @GetMapping
    public ResponseEntity<List<Plante>> getAllPlantes() {
        List<Plante> plantes = planteRepository.findAll();
        return ResponseEntity.ok(plantes);
    }

    @Operation(summary = "Get a plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the plant",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanteById(@PathVariable Integer id) {
        Optional<Plante> plante = planteRepository.findById(id);
        if (plante.isPresent()) {
            return ResponseEntity.ok(plante.get());
        } else {
            return ResponseEntity.status(404).body("Plante not found");
        }
    }

    @Operation(summary = "Get plants by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the plants",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "404", description = "No plants found for this user")
    })
    @GetMapping("/utilisateur/{id_utilisateur}")
    public ResponseEntity<?> getPlantesByUtilisateurId(@PathVariable Integer id_utilisateur) {
        List<Plante> plantes = planteRepository.findByUtilisateurId(id_utilisateur);
        if (plantes.isEmpty()) {
            return ResponseEntity.status(404).body("No plants found for this user");
        } else {
            return ResponseEntity.ok(plantes);
        }
    }

    @Operation(summary = "Delete a plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant deleted"),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlante(@PathVariable Integer id) {
        Optional<Plante> plante = planteRepository.findById(id);
        if (plante.isPresent()) {
            planteRepository.delete(plante.get());
            return ResponseEntity.ok().body("Plante supprimée avec succès");
        } else {
            return ResponseEntity.status(404).body("Plante non trouvée");
        }
    }

    @Operation(summary = "Update a plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlante(@PathVariable Integer id, @RequestBody Plante updatedPlante) {
        Optional<Plante> existingPlanteOptional = planteRepository.findById(id);
        if (existingPlanteOptional.isPresent()) {
            Plante existingPlante = existingPlanteOptional.get();
            existingPlante.setNom_plante(updatedPlante.getNom_plante());
            existingPlante.setDescription(updatedPlante.getDescription());
            existingPlante.setVariete(updatedPlante.getVariete());
            // Ajoutez les autres champs que vous souhaitez mettre à jour

            Plante updatedPlanteEntity = planteRepository.save(existingPlante);
            return ResponseEntity.ok(updatedPlanteEntity);
        } else {
            return ResponseEntity.status(404).body("Plante non trouvée");
        }
    }
}
