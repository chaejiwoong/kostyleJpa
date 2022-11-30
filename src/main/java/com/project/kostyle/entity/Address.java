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

    //배송지 등록
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

    //배송지 수정
    public Long update(AddressDto addressDto){

        this.address = addressDto.getAddress();
        this.tel = addressDto.getTel();
        this.name = addressDto.getName();
        this.isDefault = addressDto.isDefault();
        this.recipient = addressDto.getRecipient();

        return this.ano;
    }

    //배송지 삭제



}
