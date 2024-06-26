package com.epsi.fr.arosaje.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public class Conseil implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_conseil;
    private String contenu;


    @ManyToOne
    @JoinColumn(name="id_plante")
    @JsonBackReference
    private Plante plante;

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    @JsonIgnore
    private Utilisateur utilisateur;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Conseil{");
        sb.append("id_conseil=").append(id_conseil);
        sb.append(", contenu='").append(contenu).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
