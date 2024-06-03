package com.epsi.fr.arosaje.controller;

import com.epsi.fr.arosaje.ConseilDTO;
import com.epsi.fr.arosaje.Entity.Conseil;
import com.epsi.fr.arosaje.service.ConseilService;
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

@RestController
@RequestMapping("/api/conseils")
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
@Tag(name = "Conseil API", description = "API pour gérer les conseils")
public class ConseilController {

    @Autowired
    private ConseilService conseilService;

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
        return conseilService.getConseilsByPlanteId(id_plante);
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
        return conseilService.addConseil(conseilDTO);
    }
}
