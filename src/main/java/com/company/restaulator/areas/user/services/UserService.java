package com.company.restaulator.areas.user.services;

import com.company.restaulator.areas.user.dtos.UserDTO;
import com.company.restaulator.areas.user.dtos.UserRegisterDTO;
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