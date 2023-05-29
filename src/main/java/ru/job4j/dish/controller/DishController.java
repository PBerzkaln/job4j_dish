package ru.job4j.dish.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.dish.dto.DishDto;
import ru.job4j.dish.model.Dish;
import ru.job4j.dish.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/dish")
@AllArgsConstructor
public class DishController {
    private final DishService dishService;

    @GetMapping("/all")
    public List<DishDto> findAll() {
        return dishService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DishDto> findById(@PathVariable int id) {
        var dishDto = dishService.findById(id);
        if (dishDto.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Dish is not found. Please, check id.");
        }
        return new ResponseEntity<>(dishDto.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DishDto> create(@RequestBody Dish dish) {
        return new ResponseEntity<>(dishService.create(dish), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Dish dish) {
        var rsl = dishService.update(dish);
        if (!rsl) {
            return ResponseEntity.badRequest()
                    .body("Не удалось обновить данные");
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        var dish = new Dish();
        dish.setId(id);
        var rsl = dishService.delete(dish);
        if (!rsl) {
            return ResponseEntity.badRequest()
                    .body("Dish is not found. Please, check id.");
        }
        return ResponseEntity.ok().build();
    }
}