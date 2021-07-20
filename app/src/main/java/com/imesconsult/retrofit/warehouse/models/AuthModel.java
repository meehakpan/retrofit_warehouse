package com.imesconsult.retrofit.warehouse.models;

public class AuthModel {
    String email;
    String password;

    public AuthModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
