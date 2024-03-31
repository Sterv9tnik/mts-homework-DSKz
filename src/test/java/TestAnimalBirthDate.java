import entities.Animal;
import exceptions.InvalidAnimalBirthDateException;
import exceptions.InvalidAnimalException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import services.impl.CreateAnimalServiceImpl;
import services.impl.SearchServiceImpl;

import java.time.LocalDate;

import static entities.AbstractAnimal.getLeapYearBirthDate;
import static entities.AbstractAnimal.getNotLeapYearBirthDate;

public class TestAnimalBirthDate {

    SearchServiceImpl searchService = new SearchServiceImpl();

    CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();

    @Test
    @DisplayName("Положительный тест. Животное рождено в високосный год")
    public void successAnimalWithLeapYearBirthDate() throws InvalidAnimalBirthDateException {
        Animal animal = createAnimalServiceImpl.createRandomAnimal();
        animal.setBirthDate(getLeapYearBirthDate());

        searchService.checkLeapYearAnimal(animal);
    }

    @Test
    @DisplayName("Положительный тест. Животное не рождено в високосный год")
    public void successAnimalWithoutLeapYearBirthDate() throws InvalidAnimalBirthDateException {
        Animal animal = createAnimalServiceImpl.createRandomAnimal();
        animal.setBirthDate(getNotLeapYearBirthDate());

        searchService.checkLeapYearAnimal(animal);
    }

    @ParameterizedTest
    @DisplayName("Отрицательный тест. Поле birthDate = null")
    @NullSource
    public void failureAnimalWithBadBirthDate(LocalDate birthDate) {
        Animal animal = createAnimalServiceImpl.createRandomAnimal();
        animal.setBirthDate(birthDate);
        try {
            searchService.checkLeapYearAnimal(animal);
        } catch (InvalidAnimalBirthDateException exception) {
            System.out.println(String.format("Работа метода завершилась ошибкой: %s", exception));
        }
    }

    @Test
    @DisplayName("Отрицательный тест. Передать null вместо животного")
    public void failureAnimalWithBadAnimal() {
        try {
            searchService.checkLeapYearAnimal(null);
        } catch (InvalidAnimalBirthDateException | InvalidAnimalException exception) {
            System.out.println(String.format("Работа метода завершилась ошибкой: %s", exception));
        }
    }
}