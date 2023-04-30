package com.example.warehouse.service;

import com.example.warehouse.model.Company;
import com.example.warehouse.model.Product;
import com.example.warehouse.repository.CompanyRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public ProductService(ProductRepository productRepository, CompanyRepository companyRepository) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    public List<Product> findProductsExpensiveThanGivenPrice(Double givenPrice){
        return productRepository.findAll().stream()
                .filter(product -> product.getPrice() > givenPrice)
                .collect(Collectors.toList());
    }
    public List<Product> addNew(Product product, Long id){
        Company productCompany = companyRepository.findAll().stream()
                .filter(company -> Objects.equals(company.getId(), id))
                .collect(Collectors.toList())
                .get(0);
        product.setCompany(productCompany);
        productRepository.save(product);
        return productRepository.findAll();
    }
    public List<Product> delete(Product product) {
        productRepository.delete(product);
        return productRepository.findAll();
    }
    public List<Product> getByName(String name){
        return productRepository.getByName(name);
    }
}
