package com.project.kostyle.entity;

import com.project.kostyle.dto.product.SubCategoryDto;
import lombok.*;
import org.hibernate.annotations.Parent;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class SubCategory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pcno")
    private ParentCategory parentCategory;

    private String name;

    public void changeInfo(SubCategoryDto dto, ParentCategory category) {
        name = dto.getName();
        parentCategory = category;
    }

}
