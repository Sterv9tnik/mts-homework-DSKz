package services;

import entities.Animal;
import exceptions.InvalidAnimalBirthDateException;

public interface SearchService {

    boolean checkLeapYearAnimal(Animal animal) throws InvalidAnimalBirthDateException;
}
