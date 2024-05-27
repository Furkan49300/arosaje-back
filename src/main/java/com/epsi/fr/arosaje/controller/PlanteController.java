package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.bo.Plante;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.PlanteRepository;
import com.epsi.fr.arosaje.dal.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plantes")
@CrossOrigin(origins = "http://localhost:3000")
public class PlanteController {

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/reservation/{idReservation}")
    public ResponseEntity<List<Plante>> getPlantesByReservationId(@PathVariable Integer idReservation) {
        List<Plante> plantes = planteRepository.findByReservationId(idReservation);
        if (plantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plantes);
    }
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

    // Endpoint to get all plants
    @GetMapping
    public ResponseEntity<List<Plante>> getAllPlantes() {
        List<Plante> plantes = planteRepository.findAll();
        return ResponseEntity.ok(plantes);
    }

    // Endpoint to get a plant by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlanteById(@PathVariable Integer id) {
        Optional<Plante> plante = planteRepository.findById(id);
        if (plante.isPresent()) {
            return ResponseEntity.ok(plante.get());
        } else {
            return ResponseEntity.status(404).body("Plante not found");
        }
    }
    @GetMapping("/utilisateur/{id_utilisateur}")
    public ResponseEntity<?> getPlantesByUtilisateurId(@PathVariable Integer id_utilisateur) {
        List<Plante> plantes = planteRepository.findByUtilisateurId(id_utilisateur);
        if (plantes.isEmpty()) {
            return ResponseEntity.status(404).body("No plants found for this user");
        } else {
            return ResponseEntity.ok(plantes);
        }
    }
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
