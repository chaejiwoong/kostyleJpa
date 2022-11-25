package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class OrderCancel extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ocno;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odno")
    private OrderDetail orderDetail;

    private String reason;
}
