package com.company.restaulator.services;

import com.company.restaulator.models.entities.Role;
import com.company.restaulator.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepo.findByAuthority(authority);
    }
}
