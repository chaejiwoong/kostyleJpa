package com.project.kostyle.repository;

import com.project.kostyle.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOno(Long ono);
}
