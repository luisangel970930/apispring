package com.main.ecasa.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity (name = "Section")
@Table(name = "section")
@ToString
public class Section {

    @Id @Getter
    @Column (name = "id_section",updatable = false ,nullable = false )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSection;

    @Column(name = "name_section" ,nullable = false ) @Getter @Setter
    private String name_section;

    @Column(name = "area",nullable = false ) @Getter @Setter
    private float area;


    @Column(name = "type_product",nullable = false ) @Getter @Setter
    private String type_product;



    public Section(float area, String type_product,String name_section) {
        super();
        this.name_section=name_section;
        this.area = area;
        this.type_product = type_product;
    }

    public Section() {
        super();
    }
}
