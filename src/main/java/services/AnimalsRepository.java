package services;

import entities.Animal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AnimalsRepository {

    Map<String, LocalDate> findLeapYearNames(List<Animal> animalList);

    Map<Animal, Integer> findOlderAnimal(List<Animal> animalList, int age);

    Map<String, List<Animal>> findDuplicate(List<Animal> animalList);
}
