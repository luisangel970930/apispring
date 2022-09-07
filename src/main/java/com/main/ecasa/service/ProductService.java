package com.main.ecasa.service;

import com.main.ecasa.model.Product;
import com.main.ecasa.model.Section;
import com.main.ecasa.repository.ProductRepo;
import com.main.ecasa.repository.SectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    @Autowired
    SectionRepo sectionRepo;

    @Autowired
    ProductRepo productRepo;


    public List<Product>findAll(){
     return  productRepo.findAll();

    }

    public void addProduct(Product product, Long idSection) {

        Optional<Section> section = sectionRepo.findById(idSection);
        if (section.isEmpty()){
        throw  new IllegalStateException("Section with id "+idSection+" does not exits");

        }
        product.setSection(section.get());
        productRepo.save(product);

    }

    public List<Product> findIdSection(Long idSection) {
        Optional<Section> section = sectionRepo.findById(idSection);
        if (section.isEmpty()){
            throw  new IllegalStateException("Section with id "+idSection+" does not exits");

        }
        return  productRepo.findBySection_IdSection(idSection);

    }

    public List<Product> findLot(String lot) {

    return  productRepo.findByLot(lot);
    }

    public List<Product> findColor(String color) {
        return  productRepo.findByColor(color);
    }

    public List<Product> findFragile(String fragile) {
        return  productRepo.findByFragile(fragile);
    }

    public List<Product> findPrice(float priceStart, float priceEnd) {

        return  productRepo.findByPriceBetween(priceStart,priceEnd);
    }

    public List<Product> findType(String type) {
        return  productRepo.findByType_container(type);
    }
}
