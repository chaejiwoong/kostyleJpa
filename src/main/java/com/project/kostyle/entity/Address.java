package com.project.kostyle.entity;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.dto.member.MemberDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static Address create(AddressDto addressDto, Member member){

        return Address.builder()
                .member(member)
                .address(addressDto.getAddress())
                .tel(addressDto.getTel())
                .name(addressDto.getName())
                .isDefault(addressDto.isDefault())
                .recipient(addressDto.getRecipient())
                .build();
    }

}
