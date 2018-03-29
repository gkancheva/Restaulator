package com.company.restaulator.models.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SupplierDTO {

    private long id;

    @NotEmpty(message = "Supplier`s name cannot be empty.")
    private String name;

    @NotEmpty(message = "Address cannot be empty.")
    @Size(min = 5, message = "Address should be at least 5 characters long.")
    private String address;

    @Pattern(regexp = "\\b(359)?( |-)?(0)?(2|8)?([789]\\d)?( |-)?([0-9]{3})( |-)?([0-9]{3,4})\\b", message = "Invalid phone number")
    private String phone;

    private boolean active;

    public SupplierDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}