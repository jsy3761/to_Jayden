package horseracing.horse;

import horseracing.runnable.HorseRunnable;
import horseracing.runnable.JejuRunnable;

public class JejuHorse implements Horse {

    private HorseRunnable horseRunnable;
    private String name;

    public JejuHorse(String type) {
        horseRunnable = new JejuRunnable(type);
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
