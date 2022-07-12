package com.example.food_app.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CampbellFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brandOwner;
    private String brandName;
    @Column(columnDefinition = "nvarchar(max)")
    private String ingredients;
    private Double servingSize;
    private String servingSizeUnit;

    public CampbellFood(String brandOwner, String brandName, String ingredients, String servingSize, String servingSizeUnit) {
        this.brandOwner = brandOwner.replace("\"", "");
        this.brandName = brandName.replace("\"", "");
        this.ingredients = ingredients.replace("\"", "");
        this.servingSize = parseDoubleOrNull(servingSize.replace("\"", ""));
        this.servingSizeUnit = servingSizeUnit.replace("\"", "");
    }

    private Double parseDoubleOrNull(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
