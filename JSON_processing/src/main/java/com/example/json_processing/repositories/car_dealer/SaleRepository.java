package com.example.json_processing.repositories.car_dealer;

import com.example.json_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountDTO;
import com.example.json_processing.domain.car_dealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select new com.example.json_processing.domain.car_dealer.dtos.sales_discount.SalesDiscountDTO(" +
            "c.make, c.model, c.travelledDistance, s.customer.name, s.discount, sum(p.price)) " +
            "from Sale as s " +
            "join s.customer as cus " +
            "join s.car as c " +
            "join c.parts as p " +
            "group by c")
    Optional<List<SalesDiscountDTO>> getAllSalesWithDiscounts();
}
