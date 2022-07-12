package com.example.food_app.rest;

import com.example.food_app.model.BrandedFood;
import com.example.food_app.model.FoodWithinIdRangeRequest;
import com.example.food_app.persistence.BrandedFoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class BrandedFoodController {
    private final BrandedFoodRepository foodRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<BrandedFood> getFood(@PathVariable("id") long id) {
        var food = foodRepository.findById(id);
        return ResponseEntity.of(food);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<Long> getFoodCount() {
        return ResponseEntity.ok(foodRepository.countBrandedFood());
    }

    @PostMapping(value = "/getByIdRange")
    public ResponseEntity<List<BrandedFood>> getFoodBetweenIds(@RequestBody FoodWithinIdRangeRequest request) {
        return ResponseEntity.ok(foodRepository.getBrandedFoodByIdBetween(request.getStartId(), request.getEndId()));
    }
}
