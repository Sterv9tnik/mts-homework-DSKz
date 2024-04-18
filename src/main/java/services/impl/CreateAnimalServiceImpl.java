package services.impl;

import entities.Animal;
import services.CreateAnimalService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    public List<Animal> createAnimals(int n) {
        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int rand = new Random().nextInt(0, 4);
            Animal animal = CreateAnimalService.getRandomAnimal(rand);
            animals.add(animal);
            System.out.println(animal);
        }
        return animals;
    }

    @Override
    public void createAnimals() {
        int animalCount = 0;
        do {
            int rand = new Random().nextInt(0, 4);
            Animal animal = CreateAnimalService.getRandomAnimal(rand);
            System.out.println(animal);
            animalCount++;
        }
        while (animalCount < 10);
    }

    public Animal createRandomAnimal() {
        int rand = new Random().nextInt(0, 4);
        return CreateAnimalService.getRandomAnimal(rand);
    }

    public void createAnimalsToFile() {
        List<Animal> animals = createAnimals(5);

        try (FileOutputStream fos = new FileOutputStream("src/main/resources/animals.txt")) {
            byte[] bytes = animalListToString(animals).getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String animalListToString(List<Animal> animals){
        StringBuilder stringBuilder = new StringBuilder();
        for (Animal animal : animals) {
            stringBuilder
                    .append(animal.takeAllInformation())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public void createAnimalsToFileNIO() {
        List<Animal> animals = createAnimals(5);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/animalsNIO.txt"))) {
            for (Animal animal : animals) {
                oos.writeObject(animal);
            }
            System.out.println("Данные о животных успешно записаны в файл.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFileNIO(){
        List<Animal> animals = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/animalsNIO.txt"))) {
            while (true) {
                try {
                    Animal animal = (Animal) ois.readObject();
                    animals.add(animal);
                } catch (EOFException e) {
                    break;
                }
            }

            for (Animal animal : animals) {
                System.out.println(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
