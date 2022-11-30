package com.project.kostyle.entity;

import com.project.kostyle.dto.order.OrderDetailDto;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class OrderDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long odno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ono")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private Product product;

    private Integer amount;

    private Integer price;

    public static OrderDetail createOrderDetail(Product product, Integer amount) {
        OrderDetail orderDetail = OrderDetail.builder()
                .product(product)
                .amount(amount)
                .build();
        product.removeAmount(amount);
        return orderDetail;
    }

//    public static OrderDetail createOrderDetail(Product product, Integer amount) {
//    }

    // 주문 가격과
    public int getTotalPrice() {
        return amount*price;
    }

}
