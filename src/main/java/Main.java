import services.impl.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalServiceImpl = new CreateAnimalServiceImpl();
        createAnimalServiceImpl.createAnimals();
        System.out.println("-----------");
        createAnimalServiceImpl.createAnimals(4);
    }
}
