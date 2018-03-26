package com.company.restaulator.services;

import com.company.restaulator.models.dtos.RoleDTO;

public interface RoleService {
    RoleDTO findByAuthority(String authority);
}
