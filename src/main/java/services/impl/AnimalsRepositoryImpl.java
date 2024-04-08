package services.impl;

import entities.Animal;
import services.AnimalsRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalsRepositoryImpl implements AnimalsRepository {

    @Override
    public Map<String, LocalDate> findLeapYearNames(List<Animal> animalList) {
        Map<String, LocalDate> animalMap = animalList.stream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(
                        animal -> String.format("%s %s", animal.getClass().getSimpleName(), animal.getName()),
                        Animal::getBirthDate
                ));

        System.out.println(animalMap);
        return animalMap;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<Animal> animalList, int age) {
        Map<Animal, Integer> animalMap = animalList.stream()
                .filter(animal -> animal.getAge() > age)
                .collect(Collectors.toMap(animal -> animal, Animal::getAge));

        if (animalMap.isEmpty()) {
            Animal oldestAnimal = animalList.stream()
                    .max(Comparator.comparingInt(Animal::getAge)).orElseThrow();
            animalMap.put(oldestAnimal, oldestAnimal.getAge());
        }

        System.out.println(animalMap);
        return animalMap;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate(List<Animal> animalList) {
        Map<Animal, Integer> animalMap = new HashMap<>();
        animalList.forEach(animal -> animalMap.put(animal, animalMap.getOrDefault(animal, 0) + 1));

        System.out.println(animalMap);

        Map<String, List<Animal>> groupedByClass = animalMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().getClass().getSimpleName(),
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));

        System.out.println(groupedByClass);

        return groupedByClass;
    }

    @Override
    public int findAverageAge(List<Animal> animalList) {
        return (int) animalList.stream().mapToInt(Animal::getAge).average().orElseThrow();
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animalList, int age) {
        double averageCost = animalList.stream()
                .mapToDouble(Animal::getCost)
                .average()
                .orElseThrow();

        System.out.println(averageCost);

        return animalList.stream()
                .filter(animal -> animal.getAge() > age)
                .filter(animal -> animal.getCost() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animalList) {
        return animalList.stream()
                .sorted(Comparator.comparingDouble(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
