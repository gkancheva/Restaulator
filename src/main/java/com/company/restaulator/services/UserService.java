package com.company.restaulator.services;

import com.company.restaulator.models.dtos.UserDTO;
import com.company.restaulator.models.dtos.UserRegisterDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    void save(UserRegisterDTO user);
    UserRegisterDTO findByEmail(String email);
    UserDTO findUserByEmail(String email);
    List<UserDTO> findAll();
    void update(UserDTO userDTO);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}