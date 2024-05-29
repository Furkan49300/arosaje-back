package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.ConseilDTO;
import com.epsi.fr.arosaje.bo.Conseil;
import com.epsi.fr.arosaje.bo.Plante;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.dal.ConseilRepository;
import com.epsi.fr.arosaje.dal.PlanteRepository;
import com.epsi.fr.arosaje.dal.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conseils")
public class ConseilController {

    @Autowired
    private ConseilRepository conseilRepository;

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @GetMapping("/plante/{id_plante}")
    public ResponseEntity<List<Conseil>> getConseilsByPlanteId(@PathVariable Integer id_plante) {
        Optional<Plante> optionalPlante = planteRepository.findById(id_plante);

        if (optionalPlante.isPresent()) {
            Plante plante = optionalPlante.get();
            List<Conseil> conseils = conseilRepository.findByPlante(plante);
            return ResponseEntity.ok(conseils);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Conseil> addConseil(@RequestBody ConseilDTO conseilDTO) {
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
