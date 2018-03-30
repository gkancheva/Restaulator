package com.company.restaulator.areas.user.repositories;

import com.company.restaulator.areas.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    User findFirstByEmail(String email);

}