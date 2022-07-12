package com.example.food_app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodWithinIdRangeRequest {
    private long startId;
    private long endId;
}
