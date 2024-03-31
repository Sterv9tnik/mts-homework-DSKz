package services.impl;

import entities.Animal;
import exceptions.InvalidAnimalBirthDateException;
import exceptions.InvalidAnimalException;
import services.SearchService;

import java.time.LocalDate;
import java.util.Objects;

public class SearchServiceImpl implements SearchService {

    @Override
    public void checkLeapYearAnimal(Animal animal) throws InvalidAnimalBirthDateException {

        if (Objects.isNull(animal)) {
            throw new InvalidAnimalException("На вход пришел некорректный объект животного");
        }

        LocalDate animalBirthDate = animal.getBirthDate();

        if (Objects.isNull(animalBirthDate)) {
            String message = String.format("У животного %s не указана дата его рождения", animal.getClass());
            throw new InvalidAnimalBirthDateException(message);
        } else if (animalBirthDate.isLeapYear()) {
            String message = String.format("%s был рожден в високосный год", animal.getName());
            System.out.println(message);
        } else {
            String message = String.format("%s не был рожден в високосный год", animal.getName());
            System.out.println(message);
        }
    }
}
