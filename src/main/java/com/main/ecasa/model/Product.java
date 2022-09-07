package com.main.ecasa.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "Product")
@Table(name = "product")
@ToString
public class Product {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idProduct")
    private long idProduct;


    @Getter @Setter
   @Column (name = "name_product",nullable = false )
    private String name_product;

    @Getter @Setter
    @Column (name = "size" )
    private float size;

    @Getter @Setter
    @Column (name = "count" )
    private long count;
    @Getter @Setter
    @Column (name = "color" )
    private String color;

    @Getter @Setter
    @Column (name = "price" )
    private float price;

    @Getter @Setter
    @Column (name = "fragile" )
    private String fragile;

    @Getter @Setter
    @Column (name = "type_container")
    private String type_container;

    @Getter @Setter
    @Column (name = "lot" )
    private String lot;

    @ManyToOne @Getter @Setter
    @JoinColumn(name = "idSection", referencedColumnName = "id_section")
    private Section section;

    public Product(long idProduct, String name_product, float size,long count, String color, float price, String fragile, String type_container, String lot, Section section) {
       super();
        this.idProduct = idProduct;
        this.name_product = name_product;
        this.size = size;
        this.count= count;
        this.color = color;
        this.price = price;
        this.fragile = fragile;
        this.type_container = type_container;
        this.lot = lot;
        this.section = section;
    }
    public Product(long idProduct, String name_product, float size,long count, String color, float price, String fragile, String type_container, String lot) {
  super();
  this.idProduct = idProduct;
  this.name_product = name_product;
  this.size = size;
  this.count= count;
  this.color = color;
  this.price = price;
  this.fragile = fragile;
  this.type_container = type_container;
  this.lot = lot;

 }

    public Product() {
        super();
    }
}
