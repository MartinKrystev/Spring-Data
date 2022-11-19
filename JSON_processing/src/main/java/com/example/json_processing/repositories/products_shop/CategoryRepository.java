package com.example.json_processing.repositories.products_shop;

import com.example.json_processing.domain.products_shop.dtos.categories.CategoriesByProductsDTO;
import com.example.json_processing.domain.products_shop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from db_json.categories order by rand() limit 1", nativeQuery = true)
    Optional<Category> getRandomEntity();

    @Query(value = "select new com.example.json_processing.domain.products_shop.dtos.categories.CategoriesByProductsDTO" +
            "(c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "from Product as p " +
            "join p.categories as c " +
            "group by c.id " +
            "order by count(p.id)")
    Optional<List<CategoriesByProductsDTO>> getCategoriesByProducts();
}
