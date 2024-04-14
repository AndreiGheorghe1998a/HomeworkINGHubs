package com.company.Product.controller;

import com.company.Product.entities.Product;
import com.company.Product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    Logger logger = Logger.getLogger(ProductController.class.getName());

    @PostMapping("/saveProduct")
    public Product saveProduct(@Valid @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/{id}")
    Optional<Product> getProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }

    @GetMapping("/getProdType/{productType}")
    public List<Product> getProductsByType(@PathVariable Integer productType){
        return productService.getProductsByType(productType);
    }

    @GetMapping("/getProdBrand/{productBrand}")
    public List<Product> getProductsByBrand(@PathVariable String productBrand){
        return productService.getProductsByBrand(productBrand);
    }

    @GetMapping("/getCountryDistributor/{originCountry}/{distributor}")
    public List<Product> getProductsByProductDistributorAndCountry(@PathVariable String originCountry, @PathVariable String distributor){
        return productService.getProductsByProductDistributorAndCountry(originCountry, distributor);
    }

    @GetMapping("/getDesc")
    public List<Product> getProductListDesc(){
        return productService.getProductListDesc();
    }

    @GetMapping("/getAsc")
    public List<Product> getProductListAsc(){
        return productService.getProductListAsc();
    }

    @PutMapping("updatePrice/{id}/{newPrice}")
    public Product updatePrice(@PathVariable int id, @PathVariable double newPrice){
        return productService.updatePrice(id, newPrice);
    }

    @DeleteMapping("deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return "The product with the given id was successfully deleted";
    }


}
