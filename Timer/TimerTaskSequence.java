package Game.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerTaskSequence {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> futureTask;
    private final Runnable taskSequence;

    public TimerTaskSequence(Runnable taskSequence) {
        this.taskSequence = taskSequence;
    }

    public void start(long initialDelay, long period, TimeUnit unit) {
        futureTask = scheduler.scheduleAtFixedRate(taskSequence, initialDelay, period, unit);
    }

    public void stop() {
        if (futureTask != null && !futureTask.isCancelled()) {
            futureTask.cancel(true);
        }
    }

    public void reset(long initialDelay, long period, TimeUnit unit) {
        stop();
        start(initialDelay, period, unit);
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        Runnable taskSequence = new Runnable() {
            @Override
            public void run() {
                // Deine Aufgaben hier
                System.out.println("Aufgaben werden ausgeführt: " + System.currentTimeMillis());
            }
        };

        TimerTaskSequence timerTaskSequence = new TimerTaskSequence(taskSequence);
        timerTaskSequence.start(0, 1, TimeUnit.SECONDS);

        // Beispiel, um den Timer nach 20 Sekunden anzuhalten und dann zurückzusetzen
        try {
            Thread.sleep(2000);
            timerTaskSequence.stop();
            System.out.println("Timer gestoppt");

            Thread.sleep(5000);
            timerTaskSequence.reset(0, 1, TimeUnit.SECONDS);
            System.out.println("Timer zurückgesetzt");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Den Timer sauber herunterfahren
        Runtime.getRuntime().addShutdownHook(new Thread(timerTaskSequence::shutdown));
    }
}

