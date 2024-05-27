package com.epsi.fr.arosaje;

public class ConseilDTO {
    private Integer id_utilisateur;
    private Integer id_plante;
    private String contenu;

    // Getters and Setters
    public Integer getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(Integer id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Integer getId_plante() {
        return id_plante;
    }

    public void setId_plante(Integer id_plante) {
        this.id_plante = id_plante;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
