package com.project.kostyle.dto.order;

import com.project.kostyle.entity.Order;
import com.project.kostyle.entity.OrderDetail;
import com.project.kostyle.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class OrderDetailDto {

    public OrderDetailDto(OrderDetail orderDetail) {
        this.productName = orderDetail.getProduct().getName();
        this.amount = orderDetail.getAmount();
        this.price = orderDetail.getPrice();
        this.imgUrl = imgUrl;
    }

    private String productName;
    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 99, message = "최대 주문 수량은 99개 입니다.")
    private Integer amount;

    private Integer price;

    private String imgUrl;

}
