package horseracing;

public class HorseMain {
    public static void main(String[] args) {
        HorseFactory horseFactory = new HorseFactory();
        HorseScreen horseScreen = new HorseScreen();

        Horse jeju = horseFactory.createHorse("jeju");
        Horse america = horseFactory.createHorse("amer");
        Horse china = horseFactory.createHorse("chin");

        horseScreen.startPrint(jeju);
        horseScreen.startPrint(america);
        horseScreen.startPrint(china);

        jeju.run();
        america.run();
        china.run();

    }
}
