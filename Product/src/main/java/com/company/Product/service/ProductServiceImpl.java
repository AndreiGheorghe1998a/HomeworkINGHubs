package com.company.Product.service;

import com.company.Product.constants.ProductType;
import com.company.Product.constants.Wrapping;
import com.company.Product.entities.Product;
import com.company.Product.exceptions.MandatoryFieldsException;
import com.company.Product.exceptions.ProductIdNotFoundException;
import com.company.Product.exceptions.ProductsWrappingException;
import com.company.Product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    java.util.logging.Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    @Override
    public Product saveProduct(Product product){
        if(product.getProductType() == null || product.getProductType().toString().isBlank()
                || product.getProductName() == null || product.getProductName().isBlank()){
            logger.warning("The product type and the product name are mandatory fields. Please fill them!");
            throw new MandatoryFieldsException("The product type and the product name are mandatory fields. Please fill them!");
        }
        else if((product.getWrapping() == null || product.getWrapping().toString().isBlank())
                && product.getProductType().equals(ProductType.DRINKS)){
            logger.warning("A product in drinks category must have a wrapping in order to calculate the final price!");
            throw new ProductsWrappingException("A product in drinks category must have a wrapping in order to calculate the final price!");
        }
        else if(product.getWrapping() != null && (product.getWrapping().equals(Wrapping.PLASTIC)
                || product.getWrapping().equals(Wrapping.GLASS)) && product.getProductType().equals(ProductType.DRINKS)){
            product.setProductPrice(product.getProductPrice() + 0.5d);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductListDesc(){
        Sort sort = Sort.by(Sort.Direction.DESC, "productName");
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> getProductListAsc(){
        Sort sort = Sort.by(Sort.Direction.ASC, "productName");
        return productRepository.findAll(sort);
    }

    @Override
    public Optional<Product> getProduct(Integer id){
        return productRepository.findById(id);
    }

    @Override
    public Product updatePrice(Integer id, double updatedPrice){
        if (productRepository.findById(id).isPresent()) {
            Product productToBeUpdated = productRepository.findById(id).get();
            productToBeUpdated.setProductPrice(updatedPrice);
            return productRepository.save(productToBeUpdated);
        } else {
            logger.warning("The product with the given id does not exist");
            throw new ProductIdNotFoundException("The product with the given id does not exist");
        }
    }


    @Override
    public void deleteProduct(Integer productId) {
        if (productRepository.findById(productId).isPresent()) {
            productRepository.deleteById(productId);
        } else {
            logger.warning("The product with the given id does not exist");
            throw new ProductIdNotFoundException("The product with the given id does not exist");
        }
    }

    @Override
    public List<Product> getProductsByType(Integer productType){
        return productRepository.getProductsByType(productType);
    }

    @Override
    public List<Product> getProductsByBrand(String productBrand){
        return productRepository.getProductsByProductBrand(productBrand);
    }

    @Override
    public List<Product> getProductsByProductDistributorAndCountry(String originCountry, String distributor){
        return productRepository.getProductsByProductDistributorAndCountry(originCountry, distributor);
    }

}
