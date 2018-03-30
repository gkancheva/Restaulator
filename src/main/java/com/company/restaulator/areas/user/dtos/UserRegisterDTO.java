package com.company.restaulator.areas.user.dtos;

import com.company.restaulator.areas.user.validators.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ValidPassword(message = "Passwords does not match")
public class UserRegisterDTO {

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Pattern(regexp = "\\b[a-zA-Z0-9_\\-.+%]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b",
        message = "Incorrect email")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3, message = "Password should be at least 3 characters long")
    private String password;

    @NotEmpty(message = "Missing confirm password")
    private String confirmPassword;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String firstName, String lastName, String email, String password, String confPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}