package horseracing;

import horseracing.horse.AmerHorse;
import horseracing.horse.ChinHorse;
import horseracing.horse.Horse;
import horseracing.horse.JejuHorse;

public class HorseFactory {

    private Horse horse;

    public Horse createHorse(String type){
        switch (type){
            case "jeju":
                horse = new JejuHorse(type);
                break;
            case "amer":
                horse = new AmerHorse(type);
                break;
            case "chin":
                horse = new ChinHorse(type);
                break;
            default:
                horse = null;
        }
        return horse;
    }


}
