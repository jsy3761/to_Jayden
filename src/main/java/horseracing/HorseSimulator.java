package horseracing;

import horseracing.horse.Horse;
import horseracing.horse.SleepTime;

import java.util.ArrayList;
import java.util.List;

public class HorseSimulator {

    private List<Horse> horseList;
    private List<String> score;
    private int endCnt;

    public HorseSimulator() {
        horseList = new ArrayList<>();
        score = new ArrayList<>();
    }

    public void resistHorse(Horse horse) {
        System.out.println("========" + horse.getName() + "이 참가로 등록되었습니다.=========");
        horseList.add(horse);
    }

    public void startRacing() {
        System.out.println("===========경기 시작===========");
        for (Horse h : horseList) {
            h.start();
            new Thread(() -> {
                while (!h.getHorseRunnable().endCheck()) {

                    System.out.println(h.getHorseRunnable().getLocation());
                    try {
                        Thread.sleep(SleepTime.TIME.getTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(h.getName() + "도착!!");
                endCnt++;
                score.add(h.getName());

            }).start();
        }

        while (true) {
            if (endCnt == horseList.size()) {
                endGame();
                break;
            }
            try {
                Thread.sleep(SleepTime.TIME.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void endGame() {
        System.out.println("==========경기가 종료되었습니다.==========");
        for (int i = 0; i < endCnt; i++) {
            System.out.println(i + 1 + "등 : " + score.get(i));
        }
        System.out.println("안녕히 가십시오.");
    }
}
