package com.epsi.fr.arosaje.dal;

import com.epsi.fr.arosaje.bo.Conseil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestResource
public interface ConseilRepository extends JpaRepository<Conseil,Integer> {
}
