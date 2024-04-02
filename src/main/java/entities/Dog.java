package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static helpers.EAverageLifeExpectancy.DOG_YEARS;

public class Dog extends Pet {

    public Dog(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public static Dog getRandomDog() {
        return new Dog(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter(), getRandomBirthDate(DOG_YEARS));
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Овчарка", "Вест-Хайленд-Вайт-Терьер", "Хаски", "Маламут", "Сиба-Ину", "Лайка");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
