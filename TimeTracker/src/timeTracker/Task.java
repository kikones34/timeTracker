package timeTracker;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @uml.dependency   supplier="timeTracker.Timer"
 */
public class Task extends Work implements Visitable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private Interval activeInterval = null;

	public Interval getActiveInterval() {
		return activeInterval;
	}
	
	public void setActiveInterval(Interval activeInterval) {
		this.activeInterval = activeInterval;
	}

	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * @uml.property   name="intervals"
	 * @uml.associationEnd   multiplicity="(0 -1)" ordering="true" aggregation="composite" inverse="task:timeTracker.Interval"
	 */
	private List<Interval> intervals;

	/** 
	 * Getter of the property <tt>intervals</tt>
	 * @return  Returns the intervals.
	 * @uml.property  name="intervals"
	 */
	public List<Interval> getIntervals() {
		return intervals;
	}
	
	public Task(String name, String description) {
		super(name, description);
	}
	
	public Task(String name) {
		super(name);
	}
	
	public Task() {
		super();
	}

		
	/**
	 */
	public void start() {
		if (activeInterval == null) {
			activeInterval = new Interval(this);
			intervals.add(activeInterval);
			Timer.getInstance().addObserver(activeInterval);
		}
	}

			
	/**
	 */
	public void stop() {
		if (activeInterval != null) {
			Timer.getInstance().deleteObserver(activeInterval);
			Interval prevActiveInterval = activeInterval;
			activeInterval = null;
			prevActiveInterval.update(null, new GregorianCalendar());
		}
	}
		
	/**
	 */
	@Override
	protected void initialize() {
		Logging.getLogger().trace("" + (intervals == null));
		intervals = new ArrayList<Interval>();
	}
	
	@Override
	public void updateDuration() {
		long duration = 0L;
		for (Interval interval: intervals) {
			duration += interval.getDuration();
		}
		Logging.getLogger().debug("" + duration);
		setDuration(duration);
	}

}
