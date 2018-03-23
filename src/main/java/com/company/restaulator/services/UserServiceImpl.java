package com.company.restaulator.services;

import com.company.restaulator.models.entities.Role;
import com.company.restaulator.models.entities.User;
import com.company.restaulator.models.dtos.UserRegisterDTO;
import com.company.restaulator.repositories.UserRepository;
import com.company.restaulator.utils.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void save(UserRegisterDTO user) {
        User u = DTOConverter.convert(user, User.class);
        // TODO: 3/20/2018 crypt the password before saving the user in db
        u.setRole(Role.USER);
        this.userRepo.save(u);
    }

    @Override
    public UserRegisterDTO findByEmail(String email) {
        User u = this.userRepo.findFirstByEmail(email);
        if(u != null) {
            return DTOConverter.convert(u, UserRegisterDTO.class);
        }
        return null;
    }
}