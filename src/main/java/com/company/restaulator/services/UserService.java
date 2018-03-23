package com.company.restaulator.services;

import com.company.restaulator.models.dtos.UserRegisterDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    void save(UserRegisterDTO user);
    UserRegisterDTO findByEmail(String email);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}