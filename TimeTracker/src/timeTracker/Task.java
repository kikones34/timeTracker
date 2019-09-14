package timetracker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import utilities.Logging;

// Represents a task. Must pertain to a project (although, if it pertains to
// the root project, the user will see it as not pertaining to any project).
// It consists of a series of intervals, which indicate when it has been active.
public class Task extends Work {
	
	private static final long serialVersionUID = 1L;
	private Interval activeInterval = null;
	
	protected boolean invariant() {
		return super.invariant() && intervals != null;
	}

	public Interval getActiveInterval() {
		return activeInterval;
	}
	
	public void setActiveInterval(final Interval activeInterval) {
		assert invariant();
		this.activeInterval = activeInterval;
		assert invariant();
	}

	@Override
	public void acceptVisitor(final WorkTreeVisitor visitor) {
		assert invariant();
		visitor.visit(this);
		assert invariant();
	}
	
	/**
	 * @uml.property  name="intervals"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="task:timetracker.Interval"
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
	
	public Task(final String name, final String description) {
		super(name, description);
	}
	
	public Task(final String name) {
		super(name);
	}
	
	public Task() {
		super();
	}
	
	public boolean isActive() {
		return activeInterval != null;
	}
	
	public void start() {
		assert invariant();
		if (!isActive()) {
			// if the start date is null, it means it's the first time
			// an interval has been created for this task, thus, the
			// start and end date are initialised.
			if (getStartDate() == null) {
				Logging.getLogger().debug("dates initialised");
				initialiseDates(new GregorianCalendar());
			}
			activeInterval = new Interval(this);
			intervals.add(activeInterval);
			Timer.getInstance().addObserver(activeInterval);
		}
		assert invariant();
	}

	public void stop() {
		assert invariant();
		if (isActive()) {
			// we must set the active interval to null as soon as possible when
			// stopping the task, because other functions which might be called
			// in this method need to know that the task is stopped
			Interval prevActiveInterval = activeInterval;
			activeInterval = null;
			
			Timer.getInstance().deleteObserver(prevActiveInterval);
		}
		assert invariant();
	}
	
	@Override
	protected void initialise() {
		Logging.getLogger().trace("" + (intervals == null));
		intervals = new ArrayList<Interval>();
	}
	
	// Calculates the duration of a task.
	// A lower and upper bound for the dates can be specified.
	// A null value represents the date is not bounded.
	@Override
	public long calculateDuration(final Calendar minDate,
			final Calendar maxDate) {
		assert invariant();
		// precondition
		if (minDate != null && maxDate != null) {
			assert maxDate.after(minDate);
		}
		long duration = 0L;
		for (Interval interval: intervals) {
			duration += interval.getDuration(minDate, maxDate);
		}
		//postcondition
		assert duration >= 0L;
		
		assert invariant();
		
		return duration;
	}

	@Override
	public boolean hasBeenStarted() {
		return intervals.size() > 0;
	}

	@Override
	public void print() {
		display();
	}

}
