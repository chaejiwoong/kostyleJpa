package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Address extends BaseEntity{

    @Id
    @GeneratedValue
    private Long ano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mno")
    private Member member;

    private String address;

    private String tel;

    private String name;

    @Column(columnDefinition = "char")
    private boolean isDefault;

    private String recipient;

}
