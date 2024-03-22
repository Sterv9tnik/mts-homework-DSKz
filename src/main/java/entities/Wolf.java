package entities;

import java.util.List;
import java.util.Random;

public class Wolf extends Predator {

    public Wolf(String breed, String name, Double cost, String character) {
        super(breed, name, cost, character);
    }

    public static Wolf getRandomWolf() {
        return new Wolf(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter());
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Эфиопский Волк", "Африканский волчий шакал", "Лирый Волк", "Красный волк", "Серый волк");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
