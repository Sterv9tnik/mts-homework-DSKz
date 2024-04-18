package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public interface Animal {

    @JsonProperty("breed")
    String getBreed();

    @JsonProperty("name")
    String getName();

    @JsonProperty("cost")
    Double getCost();

    @JsonProperty("character")
    String getCharacter();

    @JsonProperty("birthDate")
    LocalDate getBirthDate();

    @JsonProperty("age")
    int getAge();

    @JsonProperty("secretInformation")
    String getSecretInformation();

    void setBreed(String breed);

    void setName(String name);

    void setCost(Double cost);

    void setCharacter(String character);

    void setBirthDate(LocalDate birthdate);

    void setSecretInformation(String secretInformation);
}
