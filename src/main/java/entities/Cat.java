package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static helpers.EAverageLifeExpectancy.CAT_YEARS;

public class Cat extends Pet {

    public Cat(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public static Cat getRandomCat() {
        return new Cat(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter(), getRandomBirthDate(CAT_YEARS));
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Манчкин", "Сиамская", "Шиншилла", "Египетская");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
