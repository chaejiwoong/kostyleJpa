package com.project.kostyle.repository;

import com.project.kostyle.entity.OrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCancelRepository extends JpaRepository<OrderCancel, Long> {
}
