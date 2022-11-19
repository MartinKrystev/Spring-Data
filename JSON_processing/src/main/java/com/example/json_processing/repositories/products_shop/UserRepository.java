package com.example.json_processing.repositories.products_shop;

import com.example.json_processing.domain.products_shop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from db_json.users order by rand() limit 1", nativeQuery = true)
    Optional<User> getRandomEntity();

    Optional<List<User>> findAllBySoldProductsIsNotNullOrderByLastName();

    @Query(value = "select u from User as u " +
            "where (select count(p) from Product as p where p.seller.id = u.id and p.buyer is not null ) > 0 " +
            "order by u.lastName, u.firstName")
    Optional<List<User>>getAllUsersWithSoldProducts();
}
