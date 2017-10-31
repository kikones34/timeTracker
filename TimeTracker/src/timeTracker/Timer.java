package timeTracker;

import java.util.GregorianCalendar;
import java.util.Observable;

public class Timer extends Observable {

	/**
	 * @uml.property  name="timeUnit"
	 * Minimum time unit in milliseconds.
	 */
	private static long timeUnit = 1000;
	
	/**
	 * @uml.property  name="timer"
	 * Singleton instance.
	 */
	private static Timer timer = null;
	
	/**
	 * @uml.property  name="thread"
	 */
	private Thread thread = null;
	
	/**
	 * Default constructor is made private
	 */
	private Timer() {
	}
	
	/**
	 * Singleton getter
	 * @return the singleton instance of the class
	 */
	public static Timer getInstance() {
		if (timer == null) {
			timer = new Timer();
		}
		return timer;
	}

	/**
	 * Getter of the property <tt>timeUnit</tt>
	 * @return  Returns the timeUnit.
	 * @uml.property  name="timeUnit"
	 */
	public static long getTimeUnit() {
		return Timer.timeUnit;
	}

	/**
	 * Setter of the property <tt>timeUnit</tt>
	 * @param timeUnit  The timeUnit to set.
	 * @uml.property  name="timeUnit"
	 */
	public static void setTimeUnit(long timeUnit) {
		Timer.timeUnit = timeUnit;
	}

	public void start() {
		thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(Timer.timeUnit);
						setChanged();
						notifyObservers(new GregorianCalendar());
					} catch (InterruptedException e) {
						break;
					}
				}
				System.out.println("Timer stopped.");
			}
		};
		thread.start();
	}

	public void stop() {
		thread.interrupt();
	}


}
