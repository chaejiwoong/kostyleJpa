package com.project.kostyle.dto.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchDto {

    private Long scno;

    private Long pcno;

    private String name;
}
