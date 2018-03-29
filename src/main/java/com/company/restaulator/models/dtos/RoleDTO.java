package com.company.restaulator.models.dtos;

public class RoleDTO {
    private long id;
    private String authority;

    public RoleDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return this.getAuthority();
    }
}
