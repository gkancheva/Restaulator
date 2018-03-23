package com.company.restaulator.models.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginDTO {

    @NotEmpty(message = "Missing email")
    @Pattern(regexp = "\\b[a-zA-Z0-9_\\-.+%]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b",
        message = "Incorrect email")
    private String email;

    @NotEmpty(message = "Missing password")
    private String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
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