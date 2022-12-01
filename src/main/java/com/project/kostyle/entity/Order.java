package com.project.kostyle.entity;

import com.project.kostyle.dto.member.MemberDto;
import com.project.kostyle.dto.order.OrderDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
//@ToString
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mno")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ano")
    private Address address;

    private String payment;

    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    //상품을 List에 담아줌
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetails.add(orderDetail);
        //양방향 객체이기 때문에 orderDetail 객체를 담아 줘야 한다.
        orderDetail.builder()
                .order(this)
                .build();
    }

    //DB에 직접 값이 들어가짐
    public static Order createOrder(Member member, Address address, List<OrderDetail> orderDetailList) {
        Order order = Order.builder()
                .member(member)
                .address(address)
                .payment("카드")
                .totalPrice(10000)
                .status(OrderStatus.ORDER)
                .build();
        for (OrderDetail orderDetail : orderDetailList) {
            order.addOrderDetail(orderDetail);
        }
        return order;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderDetail orderDetail : orderDetails) {
            totalPrice += orderDetail.getTotalPrice();
        }
        return totalPrice;
    }
}

