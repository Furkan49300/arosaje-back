package com.epsi.fr.arosaje;

import com.epsi.fr.arosaje.bo.Plante;
import com.epsi.fr.arosaje.repository.PlanteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlanteRepositoryTest {

    @Mock
    private PlanteRepository planteRepository;

    @Test
    public void testFindPlanteById() {
        // Création d'une plante fictive
        Plante plante = new Plante();
        plante.setId_plante(1);
        plante.setNom_plante("Rose");
        plante.setDescription("Belle fleur");
        plante.setVariete("Variété A");
        plante.setCreele(new Date());

        // Définition du comportement du mock du repository
        when(planteRepository.findPlanteById(1)).thenReturn(plante);

        // Appel de la méthode du repository
        Plante retrievedPlante = planteRepository.findPlanteById(1);

        // Vérification
        assertEquals(plante, retrievedPlante);
    }

    @Test
    public void testCountPlantes() {
        // Définition du comportement du mock du repository
        when(planteRepository.countPlantes()).thenReturn(10L);

        // Appel de la méthode du repository
        long count = planteRepository.countPlantes();

        // Vérification
        assertEquals(10L, count);
    }

    @Test
    public void testFindPlantesCreatedAfter() {
        // Création d'une date fictive
        Date date = new Date();

        // Création d'une liste fictive de plantes
        Plante plante1 = new Plante();
        plante1.setId_plante(1);
        plante1.setNom_plante("Rose");
        plante1.setDescription("Belle fleur");
        plante1.setVariete("Variété A");
        plante1.setCreele(new Date());

        Plante plante2 = new Plante();
        plante2.setId_plante(2);
        plante2.setNom_plante("Tulipe");
        plante2.setDescription("Fleur colorée");
        plante2.setVariete("Variété B");
        plante2.setCreele(new Date());

        List<Plante> plantes = List.of(plante1, plante2);

        // Définition du comportement du mock du repository
        when(planteRepository.findPlantesCreatedAfter(date)).thenReturn(plantes);

        // Appel de la méthode du repository
        List<Plante> retrievedPlantes = planteRepository.findPlantesCreatedAfter(date);

        // Vérification
        assertEquals(plantes, retrievedPlantes);
    }
}

