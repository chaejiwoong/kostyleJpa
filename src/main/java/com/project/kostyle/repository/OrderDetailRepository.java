package com.project.kostyle.repository;

import com.project.kostyle.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail , Long> {
}
