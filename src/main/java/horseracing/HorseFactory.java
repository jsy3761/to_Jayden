package horseracing;

public class HorseFactory {

    public Horse createHorse(String type){
        Horse horse;
        switch (type){
            case "jeju":
                horse = new JejuHorse(type);
                break;
            case "amer":
                horse = new AmeHorse(type);
                break;
            case "chin":
                horse = new ChiHorse(type);
                break;
            default:
                horse = null;
        }

        return horse;
    }


}
