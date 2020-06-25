package horseracing.horse;

public enum SleepTime {

    TIME(700);

    SleepTime(long time){
        this.time = time;
    }

    private long time;

    public long getTime() {
        return time;
    }
}
