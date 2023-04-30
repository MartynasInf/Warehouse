package com.example.warehouse.service;

import com.example.warehouse.model.Company;
import com.example.warehouse.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    MockTestData mockTestData = new MockTestData();
    List<Company> mockList = mockTestData.getCompanies();

    @Mock //Si anotacija sukuria fake/mocked repositorijos instance
    private CompanyRepository companyRepository;

    @InjectMocks //Injectina visus MOCKUS i duotaji instance
    private CompanyService companyService;

    @BeforeEach
    public void setUp(){
        //Nesvarbu ka jis daro, bet reikia kiekvienam testui, kur naudoju Mockus
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindCompaniesWithMoreEmployeesThan1050() {
        when(companyRepository.findAll()).thenReturn(mockList);

        List<Company> companies = companyService.findCompaniesWithMoreEmployeesThanGivenNumber(1050);
        assertEquals(2, companies.size());
        assertEquals(mockList.get(2), companies.get(1));

    }

    @Test
    void shouldFindCompaniesWithInStockValueMoreThan15000() {
        when(companyRepository.findAll()).thenReturn(mockList);

        List<Company> companies = companyService.findCompaniesWithInStockValueMoreThanGiven(15000);
        assertEquals(1, companies.size());
        assertEquals("Apple", companies.get(0).getName());

    }
}