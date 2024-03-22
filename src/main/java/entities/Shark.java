package entities;

import java.util.List;
import java.util.Random;

public class Shark extends Predator {

    public Shark(String breed, String name, Double cost, String character) {
        super(breed, name, cost, character);
    }

    public static Shark getRandomShark() {
        return new Shark(getRandomBreed(), getRandomName(), getRandomCost(), getRandomCharacter());
    }

    public static String getRandomBreed() {
        List<String> breeds = List.of("Большая белая акула", "Тигровая акула", "Акула-бык", "Акула-молот", "Синяя акула", "Китовая акула");

        return breeds.get(new Random().nextInt(breeds.size()));
    }
}
