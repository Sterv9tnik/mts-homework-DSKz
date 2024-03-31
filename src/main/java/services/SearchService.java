package services;

import entities.Animal;
import exceptions.InvalidAnimalBirthDateException;

public interface SearchService {

    void checkLeapYearAnimal(Animal animal) throws InvalidAnimalBirthDateException;
}
