package com.company.Product.entities;

import com.company.Product.Filter.StringFilter;
import com.company.Product.constants.ProductType;
import com.company.Product.constants.Wrapping;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer productId;

    @Column(name = "product_type")
    private ProductType productType;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "wrapping")
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = StringFilter.class)
    private Wrapping wrapping;

    @Column(name = "origin_country")
    private String originCountry;

    @Column(name = "distributor")
    private String distributor;

    @Column(name = "details")
    private String details;
}
