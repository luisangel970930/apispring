package com.main.ecasa.repository;

import com.main.ecasa.model.Product;
import com.main.ecasa.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {


    @Query("select p from Product p where p.section = ?1")
    Optional<Product> findProductBySection(Section section);


    @Query("select p from Product p where p.section.idSection = ?1")
   List<Product> findBySection_IdSection(long idSection);

    @Query("select p from Product p where p.lot = ?1")
    List<Product> findByLot(String lot);

    @Query("select p from Product p where p.fragile = ?1")
    List<Product> findByFragile(String fragile);

    @Query("select p from Product p where p.color = ?1")
    List<Product> findByColor(String color);

    @Query("select p from Product p where p.type_container = ?1")
    List<Product> findByType_container(String type_container);

    @Query("select p from Product p where p.price between ?1 and ?2")
    List<Product> findByPriceBetween(float priceStart, float priceEnd);







}
