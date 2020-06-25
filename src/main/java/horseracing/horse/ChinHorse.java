package horseracing.horse;

import horseracing.runnable.ChinRunnable;
import horseracing.runnable.HorseRunnable;

public class ChinHorse implements Horse {

    private HorseRunnable horseRunnable;

    private String name;

    public ChinHorse(String type) {
        horseRunnable = new ChinRunnable(type);
        name = type;
    }

    public String getName() {
        return name;
    }

    @Override
    public HorseRunnable getHorseRunnable() {
        return horseRunnable;
    }

    @Override
    public void start() {
        new Thread(horseRunnable).start();
    }




}
