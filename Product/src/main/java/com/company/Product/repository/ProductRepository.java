package com.company.Product.repository;

import com.company.Product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select * from product where product_type = :productType", nativeQuery = true)
    List<Product> getProductsByType(@Param("productType") Integer productType);

    @Query(value = "select * from product where product_brand = :productBrand", nativeQuery = true)
    List<Product> getProductsByProductBrand(@Param("productBrand") String productBrand);

    @Query(value = "select * from product where origin_country = :originCountry AND distributor= :distributor", nativeQuery = true)
    List<Product> getProductsByProductDistributorAndCountry(@Param("originCountry") String originCountry, @Param("distributor") String distributor);

}
