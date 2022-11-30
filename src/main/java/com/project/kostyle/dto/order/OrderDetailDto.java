package com.project.kostyle.dto.order;

import com.project.kostyle.entity.Order;
import com.project.kostyle.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
public class OrderDetailDto {

    private Long odno;
    private Order order;
    private Product product;

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 99, message = "최대 주문 수량은 99개 입니다.")
    private Integer amount;
    private Integer price;

}
