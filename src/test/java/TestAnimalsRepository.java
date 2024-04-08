import entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.impl.AnimalsRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static entities.AbstractAnimal.getLeapYearBirthDate;
import static entities.AbstractAnimal.getNotLeapYearBirthDate;
import static org.assertj.core.api.Assertions.assertThat;

public class TestAnimalsRepository {

    AnimalsRepositoryImpl animalsRepositoryImpl = new AnimalsRepositoryImpl();

    @Test
    @DisplayName("Положительный тест. Возвращен список животных рожденных в високосный год")
    public void successGetLeapYearAnimalList() {
        Animal wolf = Wolf.getRandomWolf();
        wolf.setBirthDate(getLeapYearBirthDate());

        Animal dog = Dog.getRandomDog();
        dog.setBirthDate(getLeapYearBirthDate());

        Animal shark = Shark.getRandomShark();
        shark.setBirthDate(getNotLeapYearBirthDate());

        Animal cat = Cat.getRandomCat();
        cat.setBirthDate(getNotLeapYearBirthDate());

        List<Animal> animalList = List.of(wolf, shark, dog, cat);

        Map<String, LocalDate> actualAnimalMap = animalsRepositoryImpl.findLeapYearNames(animalList);
        Map<String, LocalDate> expectedAnimalMap = Map.of(
                String.format("%s %s", wolf.getClass(), wolf.getName()), wolf.getBirthDate(),
                String.format("%s %s", dog.getClass(), dog.getName()), dog.getBirthDate()
        );

        assertThat(actualAnimalMap).isEqualTo(expectedAnimalMap);
    }

    @Test
    @DisplayName("Положительный тест. Возвращен список животных старше 5 лет")
    public void successGetOldestAnimalList() {
        Animal wolf = Wolf.getRandomWolf();
        wolf.setBirthDate(LocalDate.now().minusYears(3));

        Animal dog = Dog.getRandomDog();
        dog.setBirthDate(LocalDate.now().minusYears(6));

        Animal shark = Shark.getRandomShark();
        shark.setBirthDate(LocalDate.now().minusYears(6).plusDays(1));

        Animal secondShark = Shark.getRandomShark();
        secondShark.setBirthDate(LocalDate.now().minusYears(6).minusDays(1));

        Animal cat = Cat.getRandomCat();
        cat.setBirthDate(LocalDate.now().minusYears(10));

        List<Animal> animalList = List.of(wolf, shark, secondShark, dog, cat);

        Map<Animal, Integer> actualAnimalMap = animalsRepositoryImpl.findOlderAnimal(animalList, 5);
        Map<Animal, Integer> expectedAnimalMap = Map.of(
                secondShark, secondShark.getAge(),
                dog, dog.getAge(),
                cat, cat.getAge()
        );

        assertThat(actualAnimalMap).isEqualTo(expectedAnimalMap);
    }

    @Test
    @DisplayName("Положительный тест. Возвращено самое взрослое животное меньше 5 лет")
    public void successGetOldestAnimal() {
        Animal wolf = Wolf.getRandomWolf();
        wolf.setBirthDate(LocalDate.now().minusYears(3));

        Animal dog = Dog.getRandomDog();
        dog.setBirthDate(LocalDate.now().minusYears(2));

        Animal shark = Shark.getRandomShark();
        shark.setBirthDate(LocalDate.now().minusYears(3).plusDays(1));

        List<Animal> animalList = List.of(wolf, shark, dog);

        Map<Animal, Integer> actualAnimalMap = animalsRepositoryImpl.findOlderAnimal(animalList, 5);
        Map<Animal, Integer> expectedAnimalMap = Map.of(
                wolf, wolf.getAge()
        );

        assertThat(actualAnimalMap).isEqualTo(expectedAnimalMap);
    }

    @Test
    @DisplayName("Положительный тест. Возвращено количество дубликатов")
    public void successGetDuplicateAnimalCount() {
        Animal wolf = Wolf.getRandomWolf();
        Animal cloneWolf = new Wolf(wolf);

        Animal cloneWolfWithDifferentName = new Wolf(wolf);
        cloneWolfWithDifferentName.setName("да я клон");
        Animal cloneOfWolfWithDifferentName = new Wolf(cloneWolfWithDifferentName);

        Animal dog = Dog.getRandomDog();
        Animal cloneDogWithDifferentName = new Dog(dog);
        cloneDogWithDifferentName.setName("Клоун с бипкой");

        List<Animal> animalList = List.of(wolf, cloneWolf, cloneWolfWithDifferentName, cloneOfWolfWithDifferentName, dog, cloneDogWithDifferentName);
        Map<String, Integer> actualAnimalMap = animalsRepositoryImpl.findDuplicate(animalList);
        Map<String, Integer> expectedAnimalMap = Map.of(
                wolf.getClass().toString(), 2,
                dog.getClass().toString(), 0
        );

        assertThat(actualAnimalMap).isEqualTo(expectedAnimalMap);
    }
}