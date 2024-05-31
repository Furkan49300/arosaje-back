package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.ConseilDTO;
import com.epsi.fr.arosaje.bo.Conseil;
import com.epsi.fr.arosaje.bo.Plante;
import com.epsi.fr.arosaje.bo.Utilisateur;
import com.epsi.fr.arosaje.repository.ConseilRepository;
import com.epsi.fr.arosaje.repository.PlanteRepository;
import com.epsi.fr.arosaje.repository.UtilisateurRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/conseils")
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
@Tag(name = "Conseil API", description = "API pour gérer les conseils")
public class ConseilController {

    @Autowired
    private ConseilRepository conseilRepository;

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Operation(summary = "Obtenir des conseils par ID de plante", description = "Récupère tous les conseils associés à une plante spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des conseils trouvée",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conseil.class))),
            @ApiResponse(responseCode = "404", description = "Plante non trouvée",
                    content = @Content)
    })
    @GetMapping("/plante/{id_plante}")
    public ResponseEntity<List<Conseil>> getConseilsByPlanteId(@Parameter(description = "ID de la plante à laquelle les conseils sont associés") @PathVariable Integer id_plante) {
        Optional<Plante> optionalPlante = planteRepository.findById(id_plante);

        if (optionalPlante.isPresent()) {
            Plante plante = optionalPlante.get();
            List<Conseil> conseils = conseilRepository.findByPlante(plante);
            return ResponseEntity.ok(conseils);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Ajouter un nouveau conseil", description = "Ajoute un nouveau conseil pour une plante et un utilisateur spécifiés.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conseil ajouté avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Conseil.class),
                            examples = @ExampleObject(value = "{\"id_plante\":1,\"id_utilisateur\":1,\"contenu\":\"Arrosez la plante tous les matins.\"}"))),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content)
    })
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
