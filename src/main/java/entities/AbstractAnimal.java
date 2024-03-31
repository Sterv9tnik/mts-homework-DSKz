package entities;

import helpers.EAverageLifeExpectancy;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@ToString
@Setter
public abstract class AbstractAnimal implements Animal {

    public AbstractAnimal(String breed, String name, Double cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }

    protected String breed;

    protected String name;

    protected Double cost;

    protected String character;

    protected LocalDate birthDate;

    protected static String getRandomName() {
        List<String> names = List.of("Бандит", "Никуся", "Кусака", "Зося", "Кася", "Лаки", "Буба", "Юморист");

        return names.get(new Random().nextInt(names.size()));
    }

    protected static String getRandomCharacter() {
        List<String> characters = List.of("Холерик", "Сангвиник", "Флегматик", "Меланхолик");

        return characters.get(new Random().nextInt(characters.size()));
    }

    protected static Double getRandomCost() {
        Random rand = new Random();

        return rand.nextDouble(100, 1000);
    }

    protected static LocalDate getRandomBirthDate(EAverageLifeExpectancy averageLifeExpectancy) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minBirthDate = currentDate.minusYears(averageLifeExpectancy.getYears());
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(minBirthDate.toEpochDay(), currentDate.toEpochDay());

        return LocalDate.ofEpochDay(randomDay);
    }

    public static LocalDate getLeapYearBirthDate() {
        LocalDate currentDate = LocalDate.now();

        while (!currentDate.isLeapYear()) {
            currentDate = currentDate.minusYears(1);
        }

        return currentDate;
    }

    public static LocalDate getNotLeapYearBirthDate() {
        LocalDate currentDate = LocalDate.now();

        while (currentDate.isLeapYear()) {
            currentDate = currentDate.minusYears(1);
        }

        return currentDate;
    }
}
