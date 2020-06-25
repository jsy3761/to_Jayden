package horseracing.runnable;

import horseracing.horse.SleepTime;

public class AmerRunnable implements HorseRunnable {

    public AmerRunnable(String type) {
        location = type;
    }

    private String location;

    private int randomCnt;

    private boolean end;

    @Override
    public void run() {
        while (location.length() <= 125) {
            randomCnt = (int) (Math.random() * 14);
            for (int i = 0; i < randomCnt; i++) {
                location = " " + location;
            }
            try {
                Thread.sleep(SleepTime.TIME.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while (location.length() >= 125 && location.length() <= 150) {
            for (int i = 0; i < 1; i++) {
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

