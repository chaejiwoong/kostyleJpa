package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product extends BaseEntity{

    @Id
    @GeneratedValue
    private Long pno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scno")
    private SubCategory subCategory;

    private String name;

    private Integer price;

    private Integer hitCount;

    private Integer amount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImg> imgs;

}
