package com.example.fee_project;

public class Offre {

    private int id;
    private String titre;
    private String type;/// type de l'offre
    private Boolean remuneration;
    private String description;
    private String periode;

    public Offre(int id, String titre, String type, Boolean remuneration, String description, String periode) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.remuneration = remuneration;
        this.description = description;
        this.periode = periode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Boolean remuneration) {
        this.remuneration = remuneration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                ", remuneration=" + remuneration +
                ", description='" + description + '\'' +
                ", periode='" + periode + '\'' +
                '}';
    }
}
