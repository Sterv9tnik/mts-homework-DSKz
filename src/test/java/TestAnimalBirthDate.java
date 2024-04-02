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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAnimalBirthDate {

    SearchServiceImpl searchService = new SearchServiceImpl();

    CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();

    @Test
    @DisplayName("Положительный тест. Животное рождено в високосный год")
    public void successAnimalWithLeapYearBirthDate() throws InvalidAnimalBirthDateException {
        Animal animal = createAnimalServiceImpl.createRandomAnimal();
        animal.setBirthDate(getLeapYearBirthDate());

        boolean isLeapYear = searchService.checkLeapYearAnimal(animal);
        assertThat(isLeapYear).isTrue();
    }

    @Test
    @DisplayName("Положительный тест. Животное не рождено в високосный год")
    public void successAnimalWithoutLeapYearBirthDate() throws InvalidAnimalBirthDateException {
        Animal animal = createAnimalServiceImpl.createRandomAnimal();
        animal.setBirthDate(getNotLeapYearBirthDate());

        boolean isLeapYear = searchService.checkLeapYearAnimal(animal);
        assertThat(isLeapYear).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Отрицательный тест. Поле birthDate = null")
    @NullSource
    public void failureAnimalWithBadBirthDate(LocalDate birthDate) {
        Animal animal = createAnimalServiceImpl.createRandomAnimal();
        animal.setBirthDate(birthDate);
        assertThrows(InvalidAnimalBirthDateException.class, () -> searchService.checkLeapYearAnimal(animal));
    }

    @Test
    @DisplayName("Отрицательный тест. Передать null вместо животного")
    public void failureAnimalWithBadAnimal() {
        assertThrows(InvalidAnimalException.class, () -> searchService.checkLeapYearAnimal(null));
    }
}