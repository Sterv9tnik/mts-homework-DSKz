import entities.*;
import org.junit.jupiter.api.Test;
import services.impl.AnimalsRepositoryImpl;
import services.impl.CreateAnimalServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class TestCreateAnimalToFile {

    CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();

    AnimalsRepositoryImpl animalsRepositoryImpl = new AnimalsRepositoryImpl();

    @Test
    public void createAnimalsToFile() {
        createAnimalServiceImpl.createAnimalsToFileNIO();
        createAnimalServiceImpl.readFromFileNIO();
    }

    @Test
    public void getSecretInformation() {
        Animal cat = Cat.getRandomCat();
        System.out.println(cat.getSecretInformation());

        Animal wolf = Wolf.getRandomWolf();
        System.out.println(wolf.getSecretInformation());
    }

    @Test
    public void getOlderAnimal() {
        Animal wolf = Wolf.getRandomWolf();
        wolf.setBirthDate(LocalDate.now().minusYears(3));

        Animal dog = Dog.getRandomDog();
        dog.setBirthDate(LocalDate.now().minusYears(2));

        Animal shark = Shark.getRandomShark();
        shark.setBirthDate(LocalDate.now().minusYears(3).plusDays(1));

        List<Animal> animalList = List.of(wolf, shark, dog);

        animalsRepositoryImpl.findOlderAnimal(animalList, 5);
        animalsRepositoryImpl.getFromJson();
    }
}
