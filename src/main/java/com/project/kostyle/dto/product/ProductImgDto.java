package com.project.kostyle.dto.product;

import com.project.kostyle.entity.ParentCategory;
import com.project.kostyle.entity.Product;
import com.project.kostyle.entity.ProductImg;
import com.project.kostyle.entity.SubCategory;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ProductImgDto {

    private Long pino;

    private Long pno;

    private String filePath;

    private String fileName;

    private String uuid;

    private boolean isDefault;

    public static ProductImg toEntity(ProductImgDto dto, Product product) {
        return ProductImg.builder()
                .product(product)
                .fileName(dto.getFileName())
                .filePath(dto.getFilePath())
                .uuid(dto.getUuid())
                .isDefault(dto.isDefault())
                .build();
    }

    public static ProductImgDto of(ProductImg entity) {
        return ProductImgDto.builder()
                .pino(entity.getPino())
                .pno(entity.getProduct().getPno())
                .fileName(entity.getFileName())
                .filePath(entity.getFilePath())
                .uuid(entity.getUuid())
                .isDefault(entity.isDefault())
                .build();
    }
}
