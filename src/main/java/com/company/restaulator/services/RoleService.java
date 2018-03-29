package com.company.restaulator.services;

import com.company.restaulator.models.dtos.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO findById(long id);
    RoleDTO findByAuthority(String authority);
    List<RoleDTO> findAll();
}
