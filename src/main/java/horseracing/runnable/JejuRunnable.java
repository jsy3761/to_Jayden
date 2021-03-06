package horseracing.runnable;

import horseracing.horse.SleepTime;

public class JejuRunnable implements HorseRunnable {

    private String location;
    private int randomCnt;
    private boolean end;

    public JejuRunnable(String type) {
        location = type;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void run() {
        while (location.length() <= 120) {
            randomCnt = (int) (Math.random() * 12);
            for (int i = 0; i < randomCnt; i++) {
                location = " " + location;
            }
            try {
                Thread.sleep(SleepTime.TIME.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (location.length() >= 120 && location.length() <= 150) {
            for (int i = 0; i < 2; i++) {
                location = " " + location;
            }
            try {
                Thread.sleep(SleepTime.TIME.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean endCheck() {
        if (location.length() <= 150) {
            end = false;
        } else {
            end = true;
        }
        return end;
    }

}

