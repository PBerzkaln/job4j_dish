package ru.job4j.dish.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @EqualsAndHashCode.Include
    @Column(unique = true)
    String name;

    String description;
}