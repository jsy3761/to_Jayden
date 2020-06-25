package horseracing.runnable;


public interface HorseRunnable extends Runnable {

    boolean endCheck();

    String getLocation();
}
