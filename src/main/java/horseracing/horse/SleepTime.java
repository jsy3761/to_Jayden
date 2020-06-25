package horseracing.horse;

public enum SleepTime {

    TIME(700);

    private long time;

    SleepTime(long time){
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
