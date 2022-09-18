package com.main.ecasa.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity (name = "Section")
@Table(name = "section")
@ToString
public class Category {

    @Id @Getter
    @Column (name = "id_category",updatable = false ,nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategory;

    @Column(name = "name_category" ,nullable = false ) @Getter @Setter
    private String name_category;

    @Column(name = "area",nullable = false ) @Getter @Setter
    private float area;


    @Column(name = "type_product",nullable = false ) @Getter @Setter
    private String type_product;



    public Category(float area, String type_product, String name_category) {
        super();
        this.name_category=name_category;
        this.area = area;
        this.type_product = type_product;
    }

    public Category() {
        super();
    }
}
