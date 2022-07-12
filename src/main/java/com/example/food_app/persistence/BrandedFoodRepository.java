package com.example.food_app.persistence;

import com.example.food_app.model.BrandedFood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandedFoodRepository extends CrudRepository<BrandedFood, Long> {

    @Query("select count(bf) from BrandedFood bf")
    long countBrandedFood();

    List<BrandedFood> getBrandedFoodByIdBetween(long startId, long endId);
}
