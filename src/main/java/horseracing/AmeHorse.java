package horseracing;


public class AmeHorse implements Horse {

    private Runnable runnable;
    private String location;
    private String name;
    private HorseScreen screen;
    private int randomCnt;

    public AmeHorse(String type) {
        location = type;
        name = type;
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
                randomCnt = (int) (Math.random() * 14);

                for (int i = 0; i < randomCnt; i++) {
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
