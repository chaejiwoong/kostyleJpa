package com.project.kostyle.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ParentCategory extends BaseEntity{

    @Id
    @GeneratedValue
    private Long pcno;
    
    private String name;
}
