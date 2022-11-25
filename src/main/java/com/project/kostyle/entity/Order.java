package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Order extends BaseEntity {

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "orderDetail")
    private List<OrderDetail> orderDetails;
}
