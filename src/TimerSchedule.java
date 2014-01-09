import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Simple test of a {@link Timer}.
 *
 * @see #executeTest()
 */
public class TimerSchedule extends Observable {
  private Timer timer;

  public Timer startTimer() {
    cancelTimer();  // if already running

    timer = new Timer();
    long delayMs = 1000;
    long periodMs = 1000;
    timer.schedule(createTask(), delayMs, periodMs);

    return timer;
  }

  public void cancelTimer() {
    if (timer == null) return;

    timer.cancel();
    timer = null;

    notifyOfChangeWithMessage("Task Cancelled");
  }

  private TimerTask createTask() {
    return new TimerTask() {
      public void run() {
        // do some important task
        // then we notify of change
        notifyOfChangeWithMessage("Task Running");
      }
    };
  }

  private void notifyOfChangeWithMessage(String message) {
    setChanged();
    notifyObservers(message);
  }

  /**
   * This is a standalone test method that outputs to the console the status of the messages.
   * Create a timer that executes a task at a fixed schedule (every 1 second).
   * Then make current thread sleep for 4 seconds.
   * Once 4 seconds has elapsed, cancel the timer and wait under 3 seconds to prove that timer has been cancelled.
   * <br/>
   * <pre>Expect output as below:
   * Task Running
   * Task Running
   * Task Running
   * Task Cancelled </pre>
   *
   * @throws Exception
   */
  public static void executeTest() throws Exception {
    TimerSchedule timerSchedule = new TimerSchedule();

    timerSchedule.addObserver(new Observer() {

      public void update(Observable o, Object arg) {
        System.out.println(arg);
      }
    });

    timerSchedule.startTimer();
    // wait 4 seconds
    Thread.sleep(4000);
    // once timer has been cancelled we expect the 'Running' output to stop
    timerSchedule.cancelTimer();
    // wait 3 seconds during which there should not be anymore 'Running' output to console.
    Thread.sleep(3000);
  }

  public static void main(String[] args) throws Exception {
    TimerSchedule.executeTest();
  }
}
