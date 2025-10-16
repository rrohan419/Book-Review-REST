package com.rrohan419.bookreview.dto;

import java.util.Set;

public class AuthResponse {
    private String token;
    private Set<String> role;
    private Long userid;
    private String name;

    public AuthResponse() {}

    public AuthResponse(String token, Set<String> role, Long userId, String name) {
        this.token = token;
        this.role = role;
        this.userid = userId;
        this.name = name;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Set<String> getRole() { return role; }
    public void setRole(Set<String> role) { this.role = role; }

    public Long getUserId(){
        return this.userid;
    }

    public void setUserId(Long userId) {
        this.userid = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
