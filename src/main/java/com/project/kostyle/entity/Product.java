package com.project.kostyle.entity;

import com.project.kostyle.dto.product.ProductDto;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scno")
    private SubCategory subCategory;

    private String name;

    private Integer price;

    private Integer hitCount;

    private Integer amount;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImg> imgs;

    public void changeInfo(ProductDto dto,List<ProductImg> imgs, SubCategory subCategory) {
        this.subCategory = subCategory;
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.hitCount = dto.getHitCount();
        this.amount = dto.getAmount();

        this.imgs.clear();
        this.imgs.addAll(imgs);

    }

    public void addHitCount() {
        this.hitCount += 1;
    }
}
