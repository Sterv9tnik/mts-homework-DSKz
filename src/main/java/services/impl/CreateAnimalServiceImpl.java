package services.impl;

import entities.Animal;
import services.CreateAnimalService;

import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    public void createAnimals(int n) {
        for (int i = 0; i < n; i++) {
            int rand = new Random().nextInt(0, 4);
            Animal animal = CreateAnimalService.getRandomAnimal(rand);
            System.out.println(animal);
        }
    }

    @Override
    public void createAnimals() {
        int animalCount = 0;
        do {
            int rand = new Random().nextInt(0, 4);
            Animal animal = CreateAnimalService.getRandomAnimal(rand);
            System.out.println(animal);
            animalCount++;
        }
        while (animalCount < 10);
    }
}
