package services;

import entities.*;

import java.util.Random;

public interface CreateAnimalService {

    default void createAnimals() {
        int animalCount = 0;

        while (animalCount < 20) {
            int rand = new Random().nextInt(0, 4);
            Animal animal = getRandomAnimal(rand);
            System.out.println(animal);
            animalCount++;
        }
    }

    static Animal getRandomAnimal(int rand) {
        return switch (rand) {
            case 0 -> Dog.getRandomDog();
            case 1 -> Cat.getRandomCat();
            case 2 -> Shark.getRandomShark();
            case 3 -> Wolf.getRandomWolf();
            default -> null;
        };
    }
}
