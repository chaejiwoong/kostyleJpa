package com.project.kostyle.dto.member;

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

}
