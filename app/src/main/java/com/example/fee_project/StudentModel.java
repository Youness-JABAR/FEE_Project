package com.example.fee_project;

public class StudentModel extends UserModel {
    public StudentModel(int id, String name, String family_name, String email, String password) {
        super(id, name, family_name, email, password);
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + getId() +
                ", fullname='" + getName() +getFamily_name()+ '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';

    }
}
