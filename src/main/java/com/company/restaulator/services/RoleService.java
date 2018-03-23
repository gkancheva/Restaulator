package com.company.restaulator.services;

import com.company.restaulator.models.entities.Role;

public interface RoleService {
    Role findByAuthority(String authority);
}
