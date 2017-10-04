package timeTracker;

import java.util.GregorianCalendar;
import java.util.Observable;

public class Timer extends Observable {

	/**
	 * @uml.property  name="timeUnit"
	 */
	private long timeUnit = 1000;

	/**
	 * Getter of the property <tt>timeUnit</tt>
	 * @return  Returns the timeUnit.
	 * @uml.property  name="timeUnit"
	 */
	public long getTimeUnit() {
		return timeUnit;
	}

	/**
	 * Setter of the property <tt>timeUnit</tt>
	 * @param timeUnit  The timeUnit to set.
	 * @uml.property  name="timeUnit"
	 */
	public void setTimeUnit(long timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Timer() {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(timeUnit);
						setChanged();
						notifyObservers(new GregorianCalendar());
					} catch (InterruptedException e) {
						System.out.println("Timer thread was interrupted.");
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}

}
