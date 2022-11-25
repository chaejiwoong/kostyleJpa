package com.project.kostyle.entity;

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
    @GeneratedValue
    private Long scno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pcno")
    private ParentCategory parentCategory;

    private String name;
}
