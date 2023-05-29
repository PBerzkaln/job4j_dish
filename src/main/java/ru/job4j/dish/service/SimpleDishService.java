package ru.job4j.dish.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dish.dto.DishDto;
import ru.job4j.dish.model.Dish;
import ru.job4j.dish.repository.DishRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@ThreadSafe
public class SimpleDishService implements DishService {
    private final DishRepository dishRepository;

    @Override
    public DishDto create(Dish dish) {
        dishRepository.save(dish);
        var dishDto = new DishDto();
        dishDto.setName(dish.getName());
        dishDto.setDescription(dish.getDescription());
        return dishDto;
    }

    @Override
    public boolean update(Dish dish) {
        if (dishRepository.existsById(dish.getId())) {
            dishRepository.save(dish);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Dish dish) {
        if (dishRepository.existsById(dish.getId())) {
            dishRepository.delete(dish);
            return true;
        }
        return false;
    }

    @Override
    public List<DishDto> findAll() {
        return dishRepository.findAll().stream()
                .map(d -> new DishDto(d.getName(), d.getDescription()))
                .toList();
    }

    @Override
    public Optional<DishDto> findById(int id) {
        var dish = dishRepository.findById(id);
        if (dish.isEmpty()) {
            return Optional.empty();
        }
        var dishDto = new DishDto();
        dishDto.setName(dish.get().getName());
        dishDto.setDescription(dish.get().getDescription());
        return Optional.of(dishDto);
    }
}