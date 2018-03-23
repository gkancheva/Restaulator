package com.company.restaulator.services;

import com.company.restaulator.models.dtos.UserRegisterDTO;

public interface UserService {
    void save(UserRegisterDTO user);
    UserRegisterDTO findByEmail(String email);
}