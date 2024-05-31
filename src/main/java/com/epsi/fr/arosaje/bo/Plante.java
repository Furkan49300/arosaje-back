package com.epsi.fr.arosaje.bo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@CrossOrigin(origins = {"https://arosaje-crud.vercel.app", "http://localhost:3000"})
public class Plante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plante;
    private String nom_plante;
    @Size(max = 300)
    private String description;
    private String variete;
    private Date creele;
    @Size(max = 300)
    private String url_photo1;
    @Size(max = 300)
    private String url_photo2;
    @Size(max = 300)

    private String url_photo3;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "id_utilisateur", nullable = false)
    private Utilisateur utilisateur;


    @OneToMany(mappedBy = "plante")
    @JsonManagedReference
    private Set<Conseil> conseils;

    @OneToOne
    @JoinColumn(name="id_reservation")
    private Reservation reservation;
    public Integer getId_utilisateur() {
        return utilisateur != null ? utilisateur.getId_utilisateur() : null;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plante{");
        sb.append("id_plante=").append(id_plante);
        sb.append(", nom_plante='").append(nom_plante).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", variete='").append(variete).append('\'');
        sb.append(", creele=").append(creele);
        sb.append(", url_photo1='").append(url_photo1).append('\'');
        sb.append(", url_photo2='").append(url_photo2).append('\'');
        sb.append(", url_photo3='").append(url_photo3).append('\'');
        sb.append(", utilisateur=").append(utilisateur);
        sb.append(", conseils=").append(conseils);
        sb.append(", reservation=").append(reservation);
        sb.append('}');
        return sb.toString();
    }

}

