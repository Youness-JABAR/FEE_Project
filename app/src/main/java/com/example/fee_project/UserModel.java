package com.example.fee_project;

public class UserModel {
    private int id;
    private String name;
    private String family_name;
    private String email;
    private String password;

    public UserModel(int id, String name, String family_name, String email, String password) {
        this.id = id;
        this.name = name;
        this.family_name = family_name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

