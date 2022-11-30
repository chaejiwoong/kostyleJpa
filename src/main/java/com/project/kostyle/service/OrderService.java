package com.project.kostyle.service;

import com.project.kostyle.dto.order.OrderDto;

public interface OrderService {

    public Long order(OrderDto orderDto, String email);


}
