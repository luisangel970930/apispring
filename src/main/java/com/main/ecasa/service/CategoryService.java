package com.main.ecasa.service;

import com.main.ecasa.model.Product;
import com.main.ecasa.model.Category;
import com.main.ecasa.repository.ProductRepo;
import com.main.ecasa.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ProductRepo productRepo;

    //Valid
    public void valid(Long idCategory){
        boolean exits = categoryRepo.existsById(idCategory);

        if (!exits){
            throw  new IllegalStateException("Category with id "+idCategory+" does not exits");

        }

    }

    public List<Category> findSection (){

    return categoryRepo.findAll();
    }

    public void create(Category category) {

        categoryRepo.save(category);

    }
    @Transactional
    public void updateSection(Long idCategory, String name_category, float area, String type_product) {
        valid(idCategory);

        Category category = categoryRepo.findById(idCategory).get();

        if (name_category!=null
                &&name_category.length()>0
                && !name_category.equals(category.getName_category()))
        {    category.setName_category(name_category); }

        if (area>0
                && area!= category.getArea())
        {    category.setArea(area);}

        if (type_product!=null
                    &&type_product.length()>0
                    && !type_product.equals(category.getType_product()))
            {    category.setType_product(type_product);}



    }
    public void deleteSection(Long idCategory) {
        valid(idCategory);
        Category category = categoryRepo.findById(idCategory).get();

        Optional<Product>exists =productRepo.findProductByCategory(category);

        if (!exists.isEmpty()){
            throw  new IllegalStateException("Section with id "+idCategory+" contains products you cannot delete it");
        }

     categoryRepo.deleteById(idCategory);

    }
}
