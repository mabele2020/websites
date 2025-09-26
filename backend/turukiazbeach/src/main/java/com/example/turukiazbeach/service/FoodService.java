package com.example.turukiazbeach.service;

import com.example.turukiazbeach.model.Food;
import java.util.List;

public interface FoodService {
    Food addFood(Food food);
    List<Food> getAllFood();
    Food getFoodById(Long id);
    Food updateFood(Long id, Food food);
    void deleteFood(Long id);
}
