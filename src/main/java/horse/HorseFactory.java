package horse;

public class HorseFactory {

    public Horse createHorse(int num){
        Horse horse = new Horse(num);

        return horse;
    }

}
