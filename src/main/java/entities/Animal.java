package entities;

import java.time.LocalDate;

public interface Animal {

    String getBreed();

    String getName();

    Double getCost();

    String getCharacter();

    LocalDate getBirthDate();

    void setBreed(String breed);

    void setName(String name);

    void setCost(Double cost);

    void setCharacter(String character);

    void setBirthDate(LocalDate birthdate);
}
