package com.rrohan419.bookreview.dto;

import jakarta.validation.constraints.NotBlank;

public class SignupUser {

    @NotBlank
    private String userName;
    
    @NotBlank
    private String password;

    public SignupUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
