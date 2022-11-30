package com.project.kostyle.service;

import com.project.kostyle.dto.order.OrderDetailDto;
import com.project.kostyle.dto.order.OrderDto;
import com.project.kostyle.entity.Member;
import com.project.kostyle.entity.Order;
import com.project.kostyle.entity.OrderDetail;
import com.project.kostyle.entity.Product;
import com.project.kostyle.repository.MemberRepository;
import com.project.kostyle.repository.OrderDetailRepository;
import com.project.kostyle.repository.OrderRepository;
import com.project.kostyle.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    public Long order(OrderDto orderDto, String email) {
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = OrderDetail.createOrderDetail(product, orderDto.getAmount());
        orderDetailList.add(orderDetail);

        Order order = Order.createOrder(member, orderDetailList);
        orderRepository.save(order);

        return order.getOno();
    }
}
