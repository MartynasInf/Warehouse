package com.example.warehouse.service;

import com.example.warehouse.model.Company;
import com.example.warehouse.repository.CompanyRepository;
import com.example.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    private final ProductRepository productRepository;
    public CompanyService(CompanyRepository companyRepository, ProductRepository productRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    public List<Company> getAll(){
        return companyRepository.findAll();
    }
   public Company getByName(String name){
        return companyRepository.findByName(name);
   }
   @Transactional
   public List<Company> addNew(Company company){
        companyRepository.save(company);
        company.getProducts().forEach(product -> product.setCompany(company));
        productRepository.saveAll(company.getProducts());
        return companyRepository.findAll();
   }
   public List<Company> delete(Company company){
        companyRepository.delete(company);
        return companyRepository.findAll();
   }
   public List<Company> findCompaniesWithMoreEmployeesThanGivenNumber(Integer givenEmployeeCount){
        return companyRepository.findAll().stream()
                .filter(company -> company.getNumberOfEmployees() > givenEmployeeCount)
                .collect(Collectors.toList());
   }
   public List<Company> findCompaniesWithInStockValueMoreThanGiven(Integer givenInStockValue){
        return companyRepository.findAll().stream()
                .filter(company -> company.getProducts().stream()
                        .mapToDouble(product -> product.getPrice() * product.getQuantityInStock())
                        .sum() > givenInStockValue)
                .collect(Collectors.toList());
   }
}
