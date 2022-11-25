package com.project.kostyle.dto.product;

import com.project.kostyle.entity.ParentCategory;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ParentCategoryDto {

    private Long pcno;
    private String name;

    public static ParentCategory toEntity(ParentCategoryDto dto) {
        return ParentCategory.builder()
                .name(dto.getName())
                .build();
    }

    public static ParentCategoryDto of(ParentCategory entity) {
        return ParentCategoryDto.builder()
                .pcno(entity.getPcno())
                .name(entity.getName())
                .build();
    }
}
