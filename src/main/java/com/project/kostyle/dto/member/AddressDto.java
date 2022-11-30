package com.project.kostyle.dto.member;

import com.project.kostyle.entity.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressDto {

    private Long ano;

    private String address;

    private String tel;

    private String name;

    private boolean isDefault;

    private String recipient;

    public static AddressDto of(Address entity){

        return AddressDto.builder()
                .ano(entity.getAno())
                .address(entity.getAddress())
                .tel(entity.getTel())
                .name(entity.getName())
                .isDefault(entity.isDefault())
                .recipient(entity.getRecipient())
                .build();
    }

}
