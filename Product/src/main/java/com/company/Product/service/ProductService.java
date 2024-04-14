package com.company.Product.service;

import com.company.Product.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //method to add a new product
    Product saveProduct(Product product);

    //method to get a product by Id
    Optional<Product> getProduct(Integer id);

    //method to retrieve all de products sorted desc by their name
    List<Product> getProductListDesc();

    //method to retrieve all de products sorted asc by their name
    List<Product> getProductListAsc();

    //update the price for a product, based on the id
    Product updatePrice(Integer id, double updatedPrice);

    //delete a product based on the id
    void deleteProduct(Integer id);

    //get a list of products based on their type
    List<Product> getProductsByType(Integer productType);

    //get a list of products based on their brand
    List<Product> getProductsByBrand(String productBrand);

    //get a list of products based on their distributor and country of origin
    List<Product> getProductsByProductDistributorAndCountry(String originCountry, String distributor);




}
