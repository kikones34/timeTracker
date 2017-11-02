package timeTracker;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

// Represents a task. Must pertain to a project (although, if it pertains to
// the root project, the user will see it as not pertaining to any project).
// It consists of a series of intervals, which indicate when it has been active.
public class Task extends Work implements Visitable {
	
	private static final long serialVersionUID = 1L;
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
	
	public boolean isActive() {
		return activeInterval != null;
	}
	
	public void start() {
		if (!isActive()) {
			if (getStartDate() == null) {
				Logging.getLogger().debug("dates initialised");
				initialiseDates(new GregorianCalendar());
			}
			activeInterval = new Interval(this);
			intervals.add(activeInterval);
			Timer.getInstance().addObserver(activeInterval);
		}
	}

	public void stop() {
		if (isActive()) {
			// we must set the active interval to null as soon as possible when
			// stopping the task, because other functions which might be called
			// in this method need to know that the task is stopped
			Interval prevActiveInterval = activeInterval;
			activeInterval = null;
			
			Timer.getInstance().deleteObserver(prevActiveInterval);
		}
	}
	
	@Override
	protected void initialise() {
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
