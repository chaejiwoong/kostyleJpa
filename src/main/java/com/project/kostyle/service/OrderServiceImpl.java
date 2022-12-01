package com.project.kostyle.service;

import com.project.kostyle.dto.order.OrderDetailDto;
import com.project.kostyle.dto.order.OrderDto;
import com.project.kostyle.entity.*;
import com.project.kostyle.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    private final AddressRepository addressRepository;

    @Override
    public Long order(OrderDto orderDto, String email) {
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Address address = addressRepository.findById(1L)
                .orElseThrow(EntityExistsException::new);



        List<OrderDetail> orderDetailList = new ArrayList<>();

        Order order = Order.createOrder(member, address, orderDetailList);
        orderRepository.save(order);

        OrderDetail orderDetail = OrderDetail.createOrderDetail(product,  order, orderDto.getAmount());
        orderDetailList.add(orderDetail);

        return order.getOno();
    }
}
