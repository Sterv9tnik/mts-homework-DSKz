package entities;

import lombok.ToString;

import java.util.List;
import java.util.Random;

@ToString
public abstract class AbstractAnimal implements Animal {

    public AbstractAnimal(String breed, String name, Double cost, String character) {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.character = character;
    }

    protected String breed;

    protected String name;

    protected Double cost;

    protected String character;

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
}
