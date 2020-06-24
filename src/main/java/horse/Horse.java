package horse;

import java.util.Random;

public class Horse extends Thread {

    private Random r;
    private String num;

    public Horse(int n) {
        num = String.valueOf(n);
        r = new Random();
    }

    @Override
    public void run() {
        while (num.length() < 100) {
            System.out.println(num);

            for (int i = 0; i < r.nextInt(10); i++) {
                num = " " + num;
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        num = num.substring(num.length() - 1);
        System.out.println(num + "번 말 도착!!!!!");
    }

}
