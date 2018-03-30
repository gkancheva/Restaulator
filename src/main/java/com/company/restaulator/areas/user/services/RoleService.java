package com.company.restaulator.areas.user.services;

import com.company.restaulator.areas.user.dtos.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO findById(long id);
    RoleDTO findByAuthority(String authority);
    List<RoleDTO> findAll();
}
