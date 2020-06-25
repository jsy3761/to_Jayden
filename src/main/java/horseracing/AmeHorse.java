package horseracing;

import java.util.Random;

public class AmeHorse implements Horse {

    private Runnable runnable;
    private Random random;
    private String location;
    private String name;
    private HorseScreen screen;

    public AmeHorse(String type) {
        location = type;
        name = type;
        random = new Random();
        screen = new HorseScreen();
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void run() {

        new Thread(runnable = () -> {
            while (location.length() < 110) {
                screen.prinfInfo(this);
                for (int i = 0; i < random.nextInt(14); i++) {
                    location = " " + location;
                }
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            screen.lastspurt(this);

            while (location.length() > 110 && location.length() < 150) {
                screen.prinfInfo(this);
                for (int i = 0; i < 1; i++) {
                    location = " " + location;
                }
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            screen.endprint(this);
        }).start();

    }

}
