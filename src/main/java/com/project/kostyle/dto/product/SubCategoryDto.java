package com.project.kostyle.dto.product;

import com.project.kostyle.entity.ParentCategory;
import com.project.kostyle.entity.SubCategory;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class SubCategoryDto {

    private Long scno;
    private Long pcno;
    private String name;

    public static SubCategory toEntity(SubCategoryDto dto, ParentCategory category) {
        return SubCategory.builder()
                .name(dto.getName())
                .parentCategory(category)
                .build();
    }

    public static SubCategoryDto of(SubCategory entity) {
        return SubCategoryDto.builder()
                .scno(entity.getScno())
                .pcno(entity.getParentCategory().getPcno())
                .name(entity.getName())
                .build();
    }
}
