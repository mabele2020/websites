package com.example.turukiazbeach.service.impl;

import com.example.turukiazbeach.model.Food;
import com.example.turukiazbeach.repository.FoodRepository;
import com.example.turukiazbeach.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food addFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public Food updateFood(Long id, Food updatedFood) {
        Optional<Food> existingFoodOpt = foodRepository.findById(id);
        if (existingFoodOpt.isPresent()) {
            Food existingFood = existingFoodOpt.get();
            existingFood.setName(updatedFood.getName());
            existingFood.setEmail(updatedFood.getEmail());
            existingFood.setGuest(updatedFood.getGuest());
            existingFood.setFoodtype(updatedFood.getFoodtype());
            existingFood.setDate(updatedFood.getDate());
            return foodRepository.save(existingFood);
        }
        return null;
    }

    @Override
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }
}
