package com.company.Product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ProductsWrappingException extends RuntimeException{
    private static final long serialVersionUID = 4L;
    public ProductsWrappingException(String message) {
        super(message);
    }

}
