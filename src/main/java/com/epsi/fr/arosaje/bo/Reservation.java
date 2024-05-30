package com.epsi.fr.arosaje.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
    public class Reservation implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_reservation;
        private Date dateDebut;
        private Date dateFin;
        private boolean etat;



        @ManyToOne
        @JsonBackReference
        @JoinColumn(name="id_utilisateur")
        private Utilisateur utilisateur;

        @JsonProperty("id_utilisateur")
        public Integer getUtilisateurId() {
            return utilisateur != null ? utilisateur.getId_utilisateur() : null;
        }
        @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("id_reservation=").append(id_reservation);
        sb.append(", dateDebut=").append(dateDebut);
        sb.append(", dateFin=").append(dateFin);
        sb.append(", etat=").append(etat);
        sb.append('}');
        return sb.toString();
    }
}
