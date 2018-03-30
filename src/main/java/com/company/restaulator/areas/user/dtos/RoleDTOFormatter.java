package com.company.restaulator.areas.user.dtos;

import com.company.restaulator.areas.user.dtos.RoleDTO;
import com.company.restaulator.areas.user.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class RoleDTOFormatter implements Formatter<RoleDTO> {

    private final RoleService roleService;

    @Autowired
    public RoleDTOFormatter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO parse(String s, Locale locale) throws ParseException {
        long id = Long.parseLong(s);
        return this.roleService.findById(id);
    }

    @Override
    public String print(RoleDTO role, Locale locale) {
        return (role != null ? String.valueOf(role.getId()) : "");
    }
}
