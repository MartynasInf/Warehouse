package com.example.warehouse.config;

import com.example.warehouse.model.Company;
import com.example.warehouse.model.Product;
import com.example.warehouse.repository.CompanyRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
        //nurodzius metode repository, springas turetu pakisti jo objekta sitam metodui kaip depedency
    CommandLineRunner createInitialDbRecords(CompanyRepository companyRepository, ProductRepository productRepository) {
        return args -> {
            Company samsung = Company.builder()
                    .name("Samsung")
                    .budget(25000000L)
                    .numberOfEmployees(5000)
                    .build();
            Company adata = Company.builder()
                    .name("ADATA")
                    .budget(1200000L)
                    .numberOfEmployees(1000)
                    .build();
            companyRepository.save(samsung);
            companyRepository.save(adata);
            Product galaxy23 = Product.builder().name("Samsung S23").price(1000.1).quantityInStock(15.0).company(samsung).build();
            Product smartTV = Product.builder().name("Samsung SMART TV").price(1500.8).quantityInStock(8.0).company(samsung).build();
            Product hdd = Product.builder().name("ADATA SSD 200GB").price(60.9).quantityInStock(860.0).company(adata).build();
            Product ram = Product.builder().name("RAM DDR4 8gb").price(56.5).quantityInStock(86.0).company(adata).build();
            productRepository.saveAll(Arrays.asList(galaxy23, smartTV, hdd, ram));
        };
    }
}
