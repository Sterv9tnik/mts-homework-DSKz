package entities;

import java.util.List;
import java.util.Random;

public class Dog extends Pet {

    public Dog(String breed, String name, Double cost, String character) {
        super(breed, name, cost, character);
    }

    public static Dog getRandomDog() {
        return new Dog(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter());
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Овчарка", "Вест-Хайленд-Вайт-Терьер", "Хаски", "Маламут", "Сиба-Ину", "Лайка");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
