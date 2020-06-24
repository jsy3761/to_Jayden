package horse;

public class HorseMain {
    public static void main(String[] args) {

        HorseFactory horseFactory = new HorseFactory();
        Horse horse1 = horseFactory.createHorse(1);
        Horse horse2 = horseFactory.createHorse(2);
        Horse horse3 = horseFactory.createHorse(3);

        horse1.start();
        horse2.start();
        horse3.start();

    }
}
