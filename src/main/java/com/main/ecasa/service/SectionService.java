package com.main.ecasa.service;

import com.main.ecasa.model.Product;
import com.main.ecasa.model.Section;
import com.main.ecasa.repository.ProductRepo;
import com.main.ecasa.repository.SectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class SectionService {

    @Autowired
    SectionRepo sectionRepo;
    @Autowired
    ProductRepo productRepo;

    //Valid
    public void valid(Long idSection){
        boolean exits = sectionRepo.existsById(idSection);

        if (!exits){
            throw  new IllegalStateException("Section with id "+idSection+" does not exits");

        }

    }

    public List<Section> findSection (){

    return sectionRepo.findAll();
    }

    public void create(Section section) {

        sectionRepo.save(section);

    }
    @Transactional
    public void updateSection(Long idSection, String name_section, float area, String type_product) {
        valid(idSection);

        Section section = sectionRepo.findById(idSection).get();

        if (name_section!=null
                &&name_section.length()>0
                && !name_section.equals(section.getName_section()))
        {    section.setName_section(name_section); }

        if (area>0
                && area!=section.getArea())
        {    section.setArea(area);}

        if (type_product!=null
                    &&type_product.length()>0
                    && !type_product.equals(section.getType_product()))
            {    section.setType_product(type_product);}



    }
    public void deleteSection(Long idSection) {
        valid(idSection);
        Section section = sectionRepo.findById(idSection).get();

        Optional<Product>exists =productRepo.findProductBySection(section);

        if (!exists.isEmpty()){
            throw  new IllegalStateException("Section with id "+idSection+" contains products you cannot delete it");
        }

     sectionRepo.deleteById(idSection);

    }
}
