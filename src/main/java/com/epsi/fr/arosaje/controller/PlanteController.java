package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.Plante;
import com.epsi.fr.arosaje.service.PlanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plantes")
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public class PlanteController {

    @Autowired
    private PlanteService planteService;

    @Operation(summary = "Get plants by reservation ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the plants",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "204", description = "No plants found")
    })
    @GetMapping("/reservation/{idReservation}")
    public ResponseEntity<List<Plante>> getPlantesByReservationId(@PathVariable Integer idReservation) {
        return planteService.getPlantesByReservationId(idReservation);
    }

    @Operation(summary = "Create a new plant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<?> createPlante(@RequestBody Plante plante) {
        return planteService.createPlante(plante);
    }

    @Operation(summary = "Get all plants")
    @ApiResponse(responseCode = "200", description = "Found all plants",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) })
    @GetMapping
    public ResponseEntity<List<Plante>> getAllPlantes() {
        return planteService.getAllPlantes();
    }

    @Operation(summary = "Get a plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the plant",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanteById(@PathVariable Integer id) {
        return planteService.getPlanteById(id);
    }

    @Operation(summary = "Get plants by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the plants",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "404", description = "No plants found for this user")
    })
    @GetMapping("/utilisateur/{id_utilisateur}")
    public ResponseEntity<?> getPlantesByUtilisateurId(@PathVariable Integer id_utilisateur) {
        return planteService.getPlantesByUtilisateurId(id_utilisateur);
    }

    @Operation(summary = "Delete a plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant deleted"),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlante(@PathVariable Integer id) {
        return planteService.deletePlante(id);
    }

    @Operation(summary = "Update a plant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plant updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Plante.class)) }),
            @ApiResponse(responseCode = "404", description = "Plant not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlante(@PathVariable Integer id, @RequestBody Plante updatedPlante) {
        return planteService.updatePlante(id, updatedPlante);
    }
}
