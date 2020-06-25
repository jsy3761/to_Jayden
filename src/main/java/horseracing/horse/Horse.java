package horseracing.horse;

import horseracing.runnable.HorseRunnable;

public interface Horse {

    void start();

    String getName();

    HorseRunnable getHorseRunnable();

}
