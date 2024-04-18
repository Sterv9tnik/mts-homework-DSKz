package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static helpers.EAverageLifeExpectancy.WOLF_YEARS;

public class Wolf extends Predator {

    public Wolf() {
    }

    public Wolf(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public Wolf(Animal animal) {
        super(animal.getBreed(), animal.getName(), animal.getCost(), animal.getCharacter(), animal.getBirthDate());
    }

    public static Wolf getRandomWolf() {
        return new Wolf(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter(), getRandomBirthDate(WOLF_YEARS));
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Эфиопский Волк", "Африканский волчий шакал", "Лирый Волк", "Красный волк", "Серый волк");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
