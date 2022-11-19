package com.example.json_processing.repositories.car_dealer;

import com.example.json_processing.domain.car_dealer.entities.Supplier;
import com.example.json_processing.domain.products_shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "select * from db_json.suppliers order by rand() limit 1", nativeQuery = true)
    Optional<Supplier> getRandomSupplierEntity();

    Optional<List<Supplier>> getSuppliersByIsImporterIsFalse();
}
