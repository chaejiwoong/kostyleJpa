package com.project.kostyle.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class ParentCategory extends BaseEntity{

    @Id
    @GeneratedValue
    private Long pcno;
    
    private String name;
}
