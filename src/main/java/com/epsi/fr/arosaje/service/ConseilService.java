package com.epsi.fr.arosaje.service;

import com.epsi.fr.arosaje.ConseilDTO;
import com.epsi.fr.arosaje.Entity.Conseil;
import com.epsi.fr.arosaje.Entity.Plante;
import com.epsi.fr.arosaje.Entity.Utilisateur;
import com.epsi.fr.arosaje.repository.ConseilRepository;
import com.epsi.fr.arosaje.repository.PlanteRepository;
import com.epsi.fr.arosaje.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConseilService {

    @Autowired
    private ConseilRepository conseilRepository;

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public ResponseEntity<List<Conseil>> getConseilsByPlanteId(Integer id_plante) {
        Optional<Plante> optionalPlante = planteRepository.findById(id_plante);

        if (optionalPlante.isPresent()) {
            Plante plante = optionalPlante.get();
            List<Conseil> conseils = conseilRepository.findByPlante(plante);
            return ResponseEntity.ok(conseils);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Conseil> addConseil(ConseilDTO conseilDTO) {
        Optional<Plante> optionalPlante = planteRepository.findById(conseilDTO.getId_plante());
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(conseilDTO.getId_utilisateur());

        if (optionalPlante.isPresent() && optionalUtilisateur.isPresent()) {
            Plante plante = optionalPlante.get();
            Utilisateur utilisateur = optionalUtilisateur.get();

            Conseil conseil = new Conseil();
            conseil.setPlante(plante);
            conseil.setUtilisateur(utilisateur);
            conseil.setContenu(conseilDTO.getContenu());

            Conseil savedConseil = conseilRepository.save(conseil);
            return ResponseEntity.ok(savedConseil);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
