package com.example.fee_project;

public class Documents {
    private int id;
    private int idStudent;
    private int idOffer;
    private String cvName;
    private String letterName;

    public Documents(int id, int idStudent, int idOffer, String cvName, String letterName) {
        this.id = id;
        this.idStudent = idStudent;
        this.idOffer = idOffer;
        this.cvName = cvName;
        this.letterName = letterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public String getLetterName() {
        return letterName;
    }

    public void setLetterName(String letterName) {
        this.letterName = letterName;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", idOffer=" + idOffer +
                ", cvName='" + cvName + '\'' +
                ", letterName='" + letterName + '\'' +
                '}';
    }
}
