package com.epsi.fr.arosaje.bo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Plante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_plante;
    private String nom_plante;
    private String description;
    private String variete;
    private Date creele;

    public Date getCreele() {
        return creele;
    }

    public void setCreele(Date creele) {
        this.creele = creele;
    }

    public Plante() {
    }

    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy="plante")
    private Set<Photo> photos;

    @OneToMany(mappedBy="plante")
    private Set<Conseil> conseils;

    @OneToOne
    private Reservation reservation;

    public Integer getId_plante() {
        return id_plante;
    }

    public void setId_plante(Integer id_plante) {
        this.id_plante = id_plante;
    }

    public String getNom_plante() {
        return nom_plante;
    }

    public void setNom_plante(String nom_plante) {
        this.nom_plante = nom_plante;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return variete;
    }

    public void setLieu(String lieu) {
        this.variete = variete;
    }

    public String getVariete() {
        return variete;
    }

    public void setVariete(String variete) {
        this.variete = variete;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plante{");
        sb.append("id_plante=").append(id_plante);
        sb.append(", nom_plante='").append(nom_plante).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", variete='").append(variete).append('\'');
        sb.append(", creele=").append(creele);
        sb.append(", utilisateur=").append(utilisateur);
        sb.append(", photos=").append(photos);
        sb.append(", conseils=").append(conseils);
        sb.append(", reservation=").append(reservation);
        sb.append('}');
        return sb.toString();
    }
}
