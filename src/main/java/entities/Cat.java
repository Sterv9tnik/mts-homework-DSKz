package entities;

import java.util.List;
import java.util.Random;

public class Cat extends Pet {

    public Cat(String breed, String name, Double cost, String character) {
        super(breed, name, cost, character);
    }

    public static Cat getRandomCat() {
        return new Cat(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter());
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Манчкин", "Сиамская", "Шиншилла", "Египетская");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
