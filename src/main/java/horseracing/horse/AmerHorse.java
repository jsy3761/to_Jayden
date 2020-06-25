package horseracing.horse;

import horseracing.runnable.AmerRunnable;
import horseracing.runnable.HorseRunnable;

public class AmerHorse implements Horse {

    private HorseRunnable horseRunnable;

    private String name;

    public AmerHorse(String type) {
        horseRunnable = new AmerRunnable(type);
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
