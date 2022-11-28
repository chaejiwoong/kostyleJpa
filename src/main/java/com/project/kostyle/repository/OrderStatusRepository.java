package com.project.kostyle.repository;

import com.project.kostyle.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
