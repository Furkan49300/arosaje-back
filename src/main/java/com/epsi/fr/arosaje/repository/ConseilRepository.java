package com.epsi.fr.arosaje.repository;

import com.epsi.fr.arosaje.bo.Conseil;
import com.epsi.fr.arosaje.bo.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public interface ConseilRepository extends JpaRepository<Conseil,Integer> {
    List<Conseil> findByPlante(Plante plante);
}
