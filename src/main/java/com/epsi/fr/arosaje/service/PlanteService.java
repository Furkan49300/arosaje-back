package com.epsi.fr.arosaje.service;

import com.epsi.fr.arosaje.Entity.Plante;
import com.epsi.fr.arosaje.Entity.Utilisateur;
import com.epsi.fr.arosaje.repository.PlanteRepository;
import com.epsi.fr.arosaje.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanteService {

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public ResponseEntity<List<Plante>> getPlantesByReservationId(Integer idReservation) {
        List<Plante> plantes = planteRepository.findByReservationId(idReservation);
        if (plantes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(plantes);
    }

    public ResponseEntity<?> createPlante(Plante plante) {
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

    public ResponseEntity<List<Plante>> getAllPlantes() {
        List<Plante> plantes = planteRepository.findAll();
        return ResponseEntity.ok(plantes);
    }

    public ResponseEntity<?> getPlanteById(Integer id) {
        Optional<Plante> plante = planteRepository.findById(id);
        if (plante.isPresent()) {
            return ResponseEntity.ok(plante.get());
        } else {
            return ResponseEntity.status(404).body("Plante not found");
        }
    }

    public ResponseEntity<?> getPlantesByUtilisateurId(Integer id_utilisateur) {
        List<Plante> plantes = planteRepository.findByUtilisateurId(id_utilisateur);
        if (plantes.isEmpty()) {
            return ResponseEntity.status(404).body("No plants found for this user");
        } else {
            return ResponseEntity.ok(plantes);
        }
    }

    public ResponseEntity<?> deletePlante(Integer id) {
        Optional<Plante> plante = planteRepository.findById(id);
        if (plante.isPresent()) {
            planteRepository.delete(plante.get());
            return ResponseEntity.ok().body("Plante supprimée avec succès");
        } else {
            return ResponseEntity.status(404).body("Plante non trouvée");
        }
    }

    public ResponseEntity<?> updatePlante(Integer id, Plante updatedPlante) {
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
