package horseracing;

import horseracing.horse.Horse;

public class HorseMain {
    public static void main(String[] args){
        HorseFactory horseFactory = new HorseFactory();
        Horse jeju = horseFactory.createHorse("jeju");
        Horse amer = horseFactory.createHorse("amer");
        Horse chin = horseFactory.createHorse("chin");

        HorseSimulator simulator = new HorseSimulator();
        simulator.resistHorse(jeju);
        simulator.resistHorse(amer);
        simulator.resistHorse(chin);

        simulator.startRacing();

    }
}
