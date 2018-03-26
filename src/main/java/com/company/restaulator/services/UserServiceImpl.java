package com.company.restaulator.services;

import com.company.restaulator.models.dtos.RoleDTO;
import com.company.restaulator.models.entities.Role;
import com.company.restaulator.models.entities.User;
import com.company.restaulator.models.dtos.UserRegisterDTO;
import com.company.restaulator.repositories.UserRepository;
import com.company.restaulator.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String PREDEFINED_USER_ROLE = "USER";
    private static final String USERNAME_NOT_FOUND_EXCEPTION = "Username not found.";
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepo;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder, UserRepository userRepo, RoleService roleService) {
        this.encoder = encoder;
        this.userRepo = userRepo;
        this.roleService = roleService;
    }

    @Override
    public void save(UserRegisterDTO userDto) {
        User user = DTOConverter.convert(userDto, User.class);
        user.setPassword(this.encoder.encode(userDto.getPassword()));
        RoleDTO roleDTO = this.roleService.findByAuthority(PREDEFINED_USER_ROLE);
        Role role = DTOConverter.convert(roleDTO, Role.class);
        user.setAuthorities(new HashSet<>(Collections.singletonList(role)));
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        this.userRepo.saveAndFlush(user);
    }

    @Transactional
    @Override
    public UserRegisterDTO findByEmail(String email) {
        User u = this.userRepo.findFirstByEmail(email);
        if(u != null) {
            return DTOConverter.convert(u, UserRegisterDTO.class);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepo.findFirstByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(USERNAME_NOT_FOUND_EXCEPTION);
        }
        return user;
    }
}