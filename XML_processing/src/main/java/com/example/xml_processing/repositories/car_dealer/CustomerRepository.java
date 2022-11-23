package com.example.xml_processing.repositories.car_dealer;

import com.example.xml_processing.domain.car_dealer.dtos.customers_sales.CustomerSalesDTO;
import com.example.xml_processing.domain.car_dealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from db_xml.customers order by rand() limit 1", nativeQuery = true)
    Optional<Customer> getRandomCustomerEntity();

    @Query(value = "select c from Customer as c order by c.birthDate asc, c.isYoungDriver asc")
    Optional<List<Customer>> getAllCustomersOrderedByBirthDate();

    @Query(value = "select new com.example.xml_processing.domain.car_dealer.dtos.customers_sales.CustomerSalesDTO(" +
            "c.name, count(sale), sum(p.price * (1.0) - sale.discount/100.0) ) " +
            "from Customer as c " +
            "join c.sales as sale " +
            "join sale.car as car " +
            "join car.parts as p " +
            "group by c " +
            "order by sum(p.price * (1.0) - sale.discount/100.0) desc, count(sale) desc ")
    Optional<List<CustomerSalesDTO>> getAllCustomersWithSales();
}