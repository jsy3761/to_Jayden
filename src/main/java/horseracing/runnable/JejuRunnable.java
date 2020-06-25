package horseracing.runnable;

import horseracing.horse.SleepTime;
import horseracing.runnable.HorseRunnable;

public class JejuRunnable implements HorseRunnable {

    public JejuRunnable(String type) {
        location = type;
    }

    private String location;

    private int randomCnt;

    private boolean end;

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
        if (location.length() <= 150){
            end = false;
        }else {
            end = true;
        }
        return end;
    }

    @Override
    public String getLocation() {
        return location;
    }
}

