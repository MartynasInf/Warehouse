package com.example.warehouse.controller;

import com.example.warehouse.model.Product;
import com.example.warehouse.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts/{givenPrice}")
    public ResponseEntity<List<Product>> getProductsByPrice(@PathVariable Double givenPrice) {
        try {
            List<Product> products = productService.findProductsExpensiveThanGivenPrice(givenPrice);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add/{id}")
    public ResponseEntity<List<Product>> addNew(@RequestBody Product product, @PathVariable Long id) {
        try {
            List<Product> products = productService.addNew(product, id);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<List<Product>> delete(@RequestBody Product product) {
        try {
            List<Product> products = productService.delete(product);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getByName/{name}")
    public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getByName(name);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
