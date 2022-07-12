package com.example.food_app.persistence;

import com.example.food_app.model.CampbellFood;
import org.springframework.data.repository.CrudRepository;

public interface CampbellFoodRepository extends CrudRepository<CampbellFood, Long> {
}
