package com.company.restaulator.areas.user.services;

import com.company.restaulator.areas.user.dtos.RoleDTO;
import com.company.restaulator.areas.user.dtos.UserDTO;
import com.company.restaulator.areas.user.entities.Role;
import com.company.restaulator.areas.user.entities.User;
import com.company.restaulator.areas.user.dtos.UserRegisterDTO;
import com.company.restaulator.areas.user.repositories.UserRepository;
import com.company.restaulator.areas.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public UserRegisterDTO findByEmail(String email) {
        User u = this.userRepo.findFirstByEmail(email);
        if(u != null) {
            return DTOConverter.convert(u, UserRegisterDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = this.userRepo.findFirstByEmail(email);
        if(user == null) {
            return null;
        }
        return DTOConverter.convert(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = this.userRepo.findAll();
        return users.stream()
                .map(u -> DTOConverter.convert(u, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = this.userRepo.findFirstByEmail(userDTO.getEmail());
        Set<Role> roles = new HashSet<>();
        for (RoleDTO roleDTO : userDTO.getAuthorities()) {
            roles.add(DTOConverter.convert(roleDTO, Role.class));
        }
        user.setAuthorities(roles);
        this.userRepo.saveAndFlush(user);
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