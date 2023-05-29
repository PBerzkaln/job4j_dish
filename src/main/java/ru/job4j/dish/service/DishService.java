package ru.job4j.dish.service;

import ru.job4j.dish.dto.DishDto;
import ru.job4j.dish.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishService {
    DishDto create(Dish dish);

    boolean update(Dish dish);

    boolean delete(Dish dish);

    List<DishDto> findAll();
    Optional<DishDto> findById(int id);
}