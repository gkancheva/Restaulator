package com.company.restaulator.repositories;

import com.company.restaulator.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String authority);

    Role findById(long id);
}
