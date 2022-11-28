package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter

@Builder
@ToString
public class OrderDetail {

    @Id
    @GeneratedValue
    private Long odno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ono")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

    private Integer amount;

    private Integer price;
}
