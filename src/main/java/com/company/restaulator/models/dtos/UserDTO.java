package com.company.restaulator.models.dtos;

import java.util.Set;

public class UserDTO {

    private String email;
    private Set<RoleDTO> authorities;

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<RoleDTO> authorities) {
        this.authorities = authorities;
    }
}