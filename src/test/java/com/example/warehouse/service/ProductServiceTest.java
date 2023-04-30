package com.example.warehouse.service;

import com.example.warehouse.model.Product;
import com.example.warehouse.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock //Si anotacija sukuria fake/mocked repositorijos instance
    private ProductRepository productRepository;

    @InjectMocks //Injectina visus MOCKUS i duotaji instance
    private ProductService productService;

    @BeforeEach
    public void setUp(){
        //Nesvarbu ka jis daro, bet reikia kiekvienam testui, kur naudoju Mockus
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldFindProductsMoreExpensiveThan1000() {
        Product product = new Product(1L, "Telefonas", 999.0, 5.0, null);
        Product product2 = new Product(2L, "TV", 1050.0, 2.0, null);
        Product product3 = new Product(3L, "Kompiuteris", 600.0, 5.0, null);

        List<Product> products = Arrays.asList(product, product2, product3);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> filteredProducts = productService.findProductsExpensiveThanGivenPrice(1000.0);
        assertEquals(1, filteredProducts.size());
        assertEquals(product2, filteredProducts.get(0));

    }
}