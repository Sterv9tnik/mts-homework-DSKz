package services.impl;

import entities.Animal;
import services.AnimalsRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalsRepositoryImpl implements AnimalsRepository {

    @Override
    public Map<String, LocalDate> findLeapYearNames(List<Animal> animalList) {
        Map<String, LocalDate> animalMap = new HashMap<>();

        for (Animal animal : animalList) {
            if (animal.getBirthDate().isLeapYear()) {
                String animalName = String.format("%s %s", animal.getClass().toString(), animal.getName());
                animalMap.put(animalName, animal.getBirthDate());
            }
        }

        System.out.println(animalMap);
        return animalMap;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(List<Animal> animalList, int age) {
        Map<Animal, Integer> animalMap = new HashMap<>();
        Animal oldestAnimal = animalList.get(0);

        for (Animal animal : animalList) {
            if (animal.getAge() > age) {
                animalMap.put(animal, animal.getAge());
            }

            if (oldestAnimal.getAge() < animal.getAge()) {
                oldestAnimal = animal;
            }
        }

        if (animalMap.isEmpty()) {
            animalMap.put(oldestAnimal, oldestAnimal.getAge());
        }

        System.out.println(animalMap);
        return animalMap;
    }

    @Override
    public Map<String, Integer> findDuplicate(List<Animal> animalList) {
        Map<Animal, Integer> animalMap = new HashMap<>();
        for (Animal animal : animalList) {
            if (animalMap.containsKey(animal)) {
                animalMap.put(animal, animalMap.get(animal) + 1);
            } else {
                animalMap.put(animal, 0);
            }
        }

        System.out.println(animalMap);

        Map<String, Integer> animalClassDuplicateMap = new HashMap<>();
        for (Map.Entry<Animal, Integer> entry : animalMap.entrySet()) {
            String animalClass = entry.getKey().getClass().toString();
            if (animalClassDuplicateMap.containsKey(animalClass)) {
                animalClassDuplicateMap.put(animalClass, animalClassDuplicateMap.get(animalClass) + entry.getValue());
            } else {
                animalClassDuplicateMap.put(animalClass, entry.getValue());
            }
        }

        System.out.println(animalClassDuplicateMap);

        return animalClassDuplicateMap;
    }
}
