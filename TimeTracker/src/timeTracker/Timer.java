package timeTracker;


public class Timer {

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

		
	/**
	 */
	public void start(){
	}

			
	/**
	 */
	public void stop(){
	}

}
