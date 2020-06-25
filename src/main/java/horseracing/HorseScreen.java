package horseracing;

public class HorseScreen {

    public void startPrint(Horse horse){
        System.out.println("=======" + horse.getLocation() + "출발 =======");
    }

    public void prinfInfo(Horse horse){
        System.out.println(horse.getLocation());
    }

    public void endprint(Horse horse){
        System.out.println(horse.getName() + " 도착 !!!!");
    }
    
    public void lastspurt(Horse horse){
        System.out.println(horse.getName() + "의 마지막 스퍼트");
    }
}
