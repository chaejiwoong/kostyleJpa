package com.project.kostyle.dto.product;

import com.project.kostyle.entity.Product;
import com.project.kostyle.entity.ProductImg;
import com.project.kostyle.entity.SubCategory;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProductDto {


    private Long pno;
    private Long scno;
    private String name;
    private Integer price;
    private Integer hitCount;
    private Integer amount;
    private List<ProductImgDto> imgs;

    public static Product toEntity(ProductDto dto, SubCategory subCategory) {
        return Product.builder()
                .subCategory(subCategory)
                .name(dto.getName())
                .price(dto.getPrice())
                .hitCount(dto.getHitCount())
                .amount(dto.getAmount())
                .build();
    }

    public static ProductDto of(Product entity, List<ProductImgDto> imgs) {
        return ProductDto.builder()
                .pno(entity.getPno())
                .scno(entity.getSubCategory().getScno())
                .name(entity.getName())
                .price(entity.getPrice())
                .hitCount(entity.getHitCount())
                .amount(entity.getAmount())
                .imgs(imgs)
                .build();
    }
}
