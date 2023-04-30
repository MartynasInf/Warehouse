package com.example.warehouse.service;
import com.example.warehouse.model.Company;
import com.example.warehouse.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MockTestData {


    public List<Company> getCompanies(){

        Company samsung = new Company(1L, "Samsung", 1000000L, 1200, null);
        Company huawei = new Company(2L, "Huawei", 3000000L, 1000, null);
        Company apple = new Company(3L, "Apple", 10000000L, 2500, null);

        Product product1 = new Product(1L, "Telefonas", 999.0, 5.0, samsung);
        Product product2 = new Product(2L, "TV", 1050.0, 2.0, samsung);
        Product product3 = new Product(3L, "Kompiuteris", 600.0, 10.0, huawei);
        Product product4 = new Product(4L, "Dulkiu siurblys", 400.0, 20.0, huawei);
        Product product5 = new Product(5L, "Laikrodis", 600.0, 15.0, apple);
        Product product6 = new Product(6L, "Ausines", 200.0, 60.0, apple);

        samsung.setProducts(Arrays.asList(product1, product2));
        huawei.setProducts(Arrays.asList(product3, product4));
        apple.setProducts(Arrays.asList(product5, product6));

        return Arrays.asList(samsung, huawei, apple);
    }
}
