package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    private Long mno;

    private String email;

    private String name;

    private String password;

    private Integer point;

    @Enumerated(EnumType.STRING)
    private Role authority;

    @OneToMany(mappedBy = "member")
    private List<Address> addressList;
}
