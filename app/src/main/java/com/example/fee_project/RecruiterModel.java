package com.example.fee_project;

public class RecruiterModel extends UserModel {
    public RecruiterModel(int id, String name, String family_name, String email, String password, int entrepriseId) {
        super(id, name, family_name, email, password);
        this.entrepriseId = entrepriseId;
    }
    private int entrepriseId;

    @Override
    public String toString() {
        return "RecruiterModel{" +
                "id=" + getId() +
                ", fullname='" + getName() +getFamily_name()+ '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

    public int getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(int entrepriseId) {
        this.entrepriseId = entrepriseId;
    }
}

