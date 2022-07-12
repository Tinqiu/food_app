package com.example.food_app.rest;


import com.example.food_app.messaging.FoodCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counts")
@RequiredArgsConstructor
public class FoodCountController {
    private final FoodCounter foodCounter;

    @GetMapping("")
    public Long getFoodCount() {
        return foodCounter.getCount();
    }
}
