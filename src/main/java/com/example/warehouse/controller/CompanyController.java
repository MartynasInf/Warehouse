package com.example.warehouse.controller;

import com.example.warehouse.model.Company;
import com.example.warehouse.service.CompanyService;
import com.example.warehouse.service.PdfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private PdfService pdfService;


    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping("/")
    public ResponseEntity<List<Company>> getAll() {
        try {
            List<Company> allCompanies = companyService.getAll();
            LOGGER.info(allCompanies.size() + " companies were found");
            pdfService.convertToPdf(allCompanies);
            return new ResponseEntity<>(allCompanies, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{companyName}")
    public ResponseEntity<Company> getByName(@PathVariable String companyName) {
        try {
            Company company = companyService.getByName(companyName);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/")
    public ResponseEntity<List<Company>> addNew(@RequestBody Company company) {
        try {
            List<Company> companies = companyService.addNew(company);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<List<Company>> delete(@RequestBody Company company) {
        try {
            List<Company> companies = companyService.delete(company);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getWithBiggerEmployeeCount/{givenCount}")
    public ResponseEntity<List<Company>> findWithMoreEmployeesThanGivenCount(@PathVariable Integer givenCount) {
        try {
            List<Company> companies = companyService.findCompaniesWithMoreEmployeesThanGivenNumber(givenCount);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getWithBiggerInStockValue/{givenInStockValue}")
    public ResponseEntity<List<Company>> findWithBiggerInStockValue(@PathVariable Integer givenInStockValue) {
        try {
            List<Company> companies = companyService.findCompaniesWithInStockValueMoreThanGiven(givenInStockValue);
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
