package com.company.restaulator.areas.supplier.repositories;

import com.company.restaulator.areas.supplier.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long id);

    @Query("SELECT o FROM Order AS o WHERE o.approved = FALSE")
    List<Order> findAllUnapproved();
}