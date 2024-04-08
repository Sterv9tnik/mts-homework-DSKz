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
                String.format("%s %s", wolf.getClass().getSimpleName(), wolf.getName()), wolf.getBirthDate(),
                String.format("%s %s", dog.getClass().getSimpleName(), dog.getName()), dog.getBirthDate()
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
        Map<String, List<Animal>> actualAnimalMap = animalsRepositoryImpl.findDuplicate(animalList);
        Map<String, List<Animal>> expectedAnimalMap = Map.of(
                wolf.getClass().getSimpleName(), List.of(wolf, cloneWolfWithDifferentName)
        );

        assertThat(actualAnimalMap).isEqualTo(expectedAnimalMap);
    }

    @Test
    @DisplayName("Положительный тест. Возвращен средний возраст животных")
    public void successGetAverageYear(){
        Animal wolf = Wolf.getRandomWolf();
        wolf.setBirthDate(LocalDate.now().minusYears(3));

        Animal dog = Dog.getRandomDog();
        dog.setBirthDate(LocalDate.now().minusYears(4));

        Animal shark = Shark.getRandomShark();
        shark.setBirthDate(LocalDate.now().minusYears(6));

        List<Animal> animalList = List.of(wolf, dog, shark);
        int actualAverageAge = animalsRepositoryImpl.findAverageAge(animalList);
        int expectedAverageAge = (wolf.getAge() + dog.getAge() + shark.getAge())/animalList.size();
        assertThat(actualAverageAge).isEqualTo(expectedAverageAge);
    }

    @Test
    @DisplayName("Положительный тест. Возвращен средний возраст животных")
    public void successFindOldAndExpensive(){
        Animal wolf = Wolf.getRandomWolf();
        wolf.setBirthDate(LocalDate.now().minusYears(3));
        wolf.setCost(1500.0);

        Animal cat = Cat.getRandomCat();
        cat.setBirthDate(LocalDate.now().minusYears(7));
        cat.setCost(3500.0);

        Animal dog = Dog.getRandomDog();
        dog.setBirthDate(LocalDate.now().minusYears(6));
        dog.setCost(1000.0);

        Animal shark = Shark.getRandomShark();
        shark.setBirthDate(LocalDate.now().minusYears(10));
        shark.setCost(5000.0);

        List<Animal> animalList = List.of(wolf, cat, dog, shark);
        List<Animal> actualAnimalList = animalsRepositoryImpl.findOldAndExpensive(animalList, 5);
        List<Animal> expectedAnimalList = List.of(shark, cat);
        assertThat(actualAnimalList).isEqualTo(expectedAnimalList);
    }

    @Test
    @DisplayName("Положительный тест. Возвращен список животных с наименьшей стоимостью")
    public void successFindMinCostAnimals(){
        Animal wolf = Wolf.getRandomWolf();
        wolf.setCost(1500.0);
        wolf.setName("Абоба");

        Animal cat = Cat.getRandomCat();
        cat.setCost(3500.0);
        cat.setName("Буба");


        Animal dog = Dog.getRandomDog();
        dog.setCost(1000.0);
        dog.setName("Вива");

        Animal shark = Shark.getRandomShark();
        shark.setCost(5000.0);
        shark.setName("Гена");

        List<Animal> animalList = List.of(wolf, cat, dog, shark);
        List<String> actualAnimalList = animalsRepositoryImpl.findMinCostAnimals(animalList);
        List<String> expectedAnimalList = List.of(dog.getName(), cat.getName(), wolf.getName());
        assertThat(actualAnimalList).isEqualTo(expectedAnimalList);
    }
}