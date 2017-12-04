package timetracker;

import java.util.GregorianCalendar;
import java.util.Observable;

import utilities.Logging;

// Singleton which creates a new thread and ticks (notifies all its observers)
// periodically, with the period length indicated by timeUnit.
public final class Timer extends Observable {
	
	private static boolean invariant() {
		return timeUnit > 0L;
	}

	/**
	 * @uml.property  name="timeUnit"
	 * Minimum time unit in milliseconds.
	 */
	private static long timeUnit = 1000L;
	
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
	public static void setTimeUnit(final long timeUnit) {
		assert invariant();
		Timer.timeUnit = timeUnit;
		assert invariant();
	}


	public void start() {
		assert invariant();
				
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
				Logging.getLogger().info("Timer stopped.");
			}
		};
		thread.start();
		
		assert invariant();
	}

	public void stop() {
		assert invariant();
		thread.interrupt();
		assert invariant();
	}


}
