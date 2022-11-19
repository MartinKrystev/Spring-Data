package com.example.json_processing.repositories.car_dealer;

import com.example.json_processing.domain.car_dealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query(value = "select * from db_json.parts order by rand() limit 1", nativeQuery = true)
    Optional<Part> getRandomPartEntity();
}
