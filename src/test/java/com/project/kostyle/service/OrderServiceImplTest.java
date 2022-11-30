package com.project.kostyle.service;

import com.project.kostyle.dto.member.MemberDto;
import com.project.kostyle.dto.order.OrderDto;
import com.project.kostyle.entity.Member;
import com.project.kostyle.entity.Order;
import com.project.kostyle.entity.OrderDetail;
import com.project.kostyle.entity.Product;
import com.project.kostyle.repository.MemberRepository;
import com.project.kostyle.repository.OrderRepository;
import com.project.kostyle.repository.ProductRepository;
import groovy.util.logging.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    public Product callProduct() {
        Long pno = 1L;
        return productRepository.findByPno(pno);
    }
    public Member callMember() {
        String email = "ww@naver.com";
        return memberRepository.findByEmail(email);
    }

    @Test
    @DisplayName("주문 테스트")
    public void order() {
        Product product = callProduct();
        Member member = callMember();

        OrderDto orderDto = OrderDto.builder()
                .amount(10)
                .productId(product.getPno())
                .build();

        Long orderId = orderService.order(orderDto, member.getEmail());

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);

        List<OrderDetail> orderDetails = order.getOrderDetails();

        int totalPrice = orderDto.getAmount()*product.getPrice();

        assertEquals(totalPrice, order.getTotalPrice());
    }




}