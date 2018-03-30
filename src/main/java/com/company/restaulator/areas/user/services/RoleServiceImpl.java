package com.company.restaulator.areas.user.services;

import com.company.restaulator.areas.user.dtos.RoleDTO;
import com.company.restaulator.areas.user.entities.Role;
import com.company.restaulator.areas.user.repositories.RoleRepository;
import com.company.restaulator.areas.DTOConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleDTO findById(long id) {
        Role r = this.roleRepo.findById(id);
        return DTOConverter.convert(r, RoleDTO.class);
    }

    @Override
    public RoleDTO findByAuthority(String authority) {
        return DTOConverter.convert(this.roleRepo.findByAuthority(authority), RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = this.roleRepo.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            roleDTOS.add(DTOConverter.convert(role, RoleDTO.class));
        }
        return roleDTOS;
    }
}
