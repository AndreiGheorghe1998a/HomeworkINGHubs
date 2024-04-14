package com.company.Product;

import com.company.Product.constants.ProductType;
import com.company.Product.constants.Wrapping;
import com.company.Product.entities.Product;
import com.company.Product.exceptions.ProductIdNotFoundException;
import com.company.Product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @MockBean
    ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }


    @Test
    public void testCreateShouldReturn200OK() throws Exception{
        Product product = new Product(1, ProductType.CARE,"Apa Plata", "Dorna", 8.7, Wrapping.PLASTIC,"Romania", "RoLegal", "Sticla Apa plata 2L");

        String requestURI = "/product/saveProduct";

        Mockito.when(productService.saveProduct(product)).thenReturn(product);
        String requestBody = objectMapper.writeValueAsString(product);
        mockMvc.perform(post(requestURI).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());
    }

     @Test
    public void testDeleteShouldReturn404NotFound() throws Exception {
        Integer productId = 10;
        String requestURI = "/product/deleteProduct/" + productId;

        Mockito.doThrow(ProductIdNotFoundException.class).when(productService).deleteProduct(productId);

        mockMvc.perform(delete(requestURI))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testDeleteShouldReturn200OK() throws Exception {
        Integer productId = 10;
        String requestURI = "/product/deleteProduct/" + productId;

        Mockito.doNothing().when(productService).deleteProduct(productId);;

        mockMvc.perform(delete(requestURI))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetShouldReturn404NotFound() throws Exception {
        Integer productId = 10;
        String requestURI = "/product/" + productId;

        Mockito.when(productService.getProduct(productId)).thenThrow(ProductIdNotFoundException.class);

        mockMvc.perform(get(requestURI))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testGetShouldReturn200OK() throws Exception {
        Integer productId = 10;
        String requestURI = "/product/" + productId;
        Product product = new Product(productId, ProductType.CARE,"Apa Plata", "Dorna", 8.7, Wrapping.PLASTIC,"Romania", "RoLegal", "Sticla Apa plata 2L");
        Mockito.when(productService.getProduct(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get(requestURI))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }





    @Test
    public void testUpdateShouldReturn404NotFound() throws Exception {
        Integer productId = 10;
        double newPrice = 20;
        String requestURI = "/product/updatePrice/" + productId + "/" + newPrice;

        Product product = new Product(productId, ProductType.CARE,"Apa Plata", "Dorna", 8.7, Wrapping.PLASTIC,"Romania", "RoLegal", "Sticla Apa plata 2L");

        Mockito.when(productService.updatePrice(productId, newPrice)).thenThrow(ProductIdNotFoundException.class);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testUpdateShouldReturn200OK() throws Exception {
        Integer productId = 10;
        double newPrice = 20;
        String requestURI = "/product/updatePrice/" + productId + "/" + newPrice;

        Product product = new Product(productId, ProductType.CARE,"Apa Plata", "Dorna", 8.7, Wrapping.PLASTIC,"Romania", "RoLegal", "Sticla Apa plata 2L");

        Mockito.when(productService.updatePrice(productId, newPrice)).thenReturn(product);

        String requestBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
