package com.company.restaulator.services;

import com.company.restaulator.models.dtos.RoleDTO;
import com.company.restaulator.repositories.RoleRepository;
import com.company.restaulator.utils.DTOConverter;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public RoleDTO findByAuthority(String authority) {
        return DTOConverter.convert(this.roleRepo.findByAuthority(authority), RoleDTO.class);
    }
}
