package com.project.kostyle.dto.order;

import com.project.kostyle.entity.Address;
import com.project.kostyle.entity.Member;
import com.project.kostyle.entity.OrderDetail;
import com.project.kostyle.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
    private Member member;
    private Address address;
    private String payment;
    private Integer totalPrice;
    private OrderStatus status;
    private List<OrderDetail> orderDetails;
    //상품에 대한 아이디
    private Long productId;

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 99, message = "최대 주문 수량은 99개 입니다.")
    private Integer amount;
}
