package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static helpers.EAverageLifeExpectancy.SHARK_YEARS;

public class Shark extends Predator {

    public Shark(String breed, String name, Double cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

    public Shark(Animal animal) {
        super(animal.getBreed(), animal.getName(), animal.getCost(), animal.getCharacter(), animal.getBirthDate());
    }

    public static Shark getRandomShark() {
        return new Shark(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter(), getRandomBirthDate(SHARK_YEARS));
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Большая белая акула", "Тигровая акула", "Акула-бык", "Акула-молот", "Синяя акула", "Китовая акула");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
