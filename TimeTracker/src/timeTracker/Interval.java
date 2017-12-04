package timetracker;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import utilities.DateUtilities;
import utilities.Logging;

// Represents a time interval. Must pertain to a task.
// It indicates the intervals in which the task has been active.
public class Interval implements Observer, Serializable {

	private static final long serialVersionUID = 1L;
	
	protected boolean invariant() {
		return parentTask != null;
	}

	public Interval(final Task parentTask) {
		this.parentTask = parentTask;
		startTime = new GregorianCalendar();
		assert invariant();
	}

	// wrapper function to get the unbounded interval duration
	public long getDuration() {
		return getDuration(null, null);
	}

	// Calculates the duration of an interval, rounded to the nearest
	// multiple of the minimum time unit specified by the Timer.
	// A lower and upper bound for the dates can be specified.
	// A null value represents the date is not bounded.
	// There are some special cases which return a duration of 0:
	//   1. If the interval has no end time
	//   2. If the interval is outside the supplied bounds
	public long getDuration(final Calendar minDate, final Calendar maxDate) {
		assert invariant();
		// precondition
		if (minDate != null && maxDate != null) {
			assert maxDate.after(minDate);
		}

		// this can happen if two tasks are started without waiting enough time
		// between them (less than Timer.timeUnit)
		if (endTime == null) {
			return 0L;
		}

		// determines the intersection between the star and end dates
		// of the interval and those specified in the arguments
		Calendar startDate;
		Calendar endDate;
		if (minDate == null) {
			startDate = startTime;
		} else {
			startDate = DateUtilities.maxDate(minDate, startTime);
		}
		if (maxDate == null) {
			endDate = endTime;
		} else {
			endDate = DateUtilities.minDate(maxDate, endTime);
		}
		
		// this is needed because, when calculating the bounded duration of
		// a task or a project, intervals which fall outside the bounds should
		// not be counted
		if (startDate.after(endDate)) {
			return 0L;
		}

		Logging.getLogger().debug(
				DateUtilities.getFormatter().format(startDate.getTime()));
		Logging.getLogger().debug("" + startDate.getTimeInMillis());
		Logging.getLogger().debug(
				DateUtilities.getFormatter().format(endDate.getTime()));
		Logging.getLogger().debug("" + endDate.getTimeInMillis());

		// calculates the duration of the interval and rounds it
		long duration = endDate.getTimeInMillis() - startDate.getTimeInMillis();
		Logging.getLogger().debug("" + duration);
		long mod = duration % Timer.getTimeUnit();
		if (mod < Timer.getTimeUnit() / 2) {
			duration -= mod;
		} else {
			duration += Timer.getTimeUnit() - mod;
		}
		
		//postcondition
		assert duration >= 0L;
		
		assert invariant();
		
		return duration;
	}

	/**
	 * @uml.property  name="parentTask"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="work:timetracker.Task"
	 */
	private Task parentTask;

	/**
	 * Getter of the property <tt>parentTask</tt>
	 * 
	 * @return Returns the parentTask.
	 * @uml.property name="parentTask"
	 */
	public Task getParentTask() {
		return parentTask;
	}

	/**
	 * Setter of the property <tt>parentTask</tt>
	 * 
	 * @param task
	 *            The parentTask to set.
	 * @uml.property name="parentTask"
	 */
	public void setParentTask(final Task parentTask) {
		assert invariant();
		this.parentTask = parentTask;
		assert invariant();
	}

	/**
	 * @uml.property name="startTime"
	 */
	private Calendar startTime;

	/**
	 * Getter of the property <tt>start</tt>
	 * 
	 * @return Returns the start.
	 * @uml.property name="startTime"
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * Setter of the property <tt>start</tt>
	 * 
	 * @param start
	 *            The start to set.
	 * @uml.property name="startTime"
	 */
	public void setStartTime(final Calendar startTime) {
		assert invariant();
		this.startTime = startTime;
		assert invariant();
	}

	/**
	 * @uml.property name="endTime"
	 */
	private Calendar endTime;

	/**
	 * Getter of the property <tt>finish</tt>
	 * 
	 * @return Returns the finish.
	 * @uml.property name="endTime"
	 */
	public Calendar getEndTime() {
		return endTime;
	}

	/**
	 * Setter of the property <tt>finish</tt>
	 * 
	 * @param finish
	 *            The finish to set.
	 * @uml.property name="endTime"
	 */
	public void setEndTime(final Calendar endTime) {
		assert invariant();
		this.endTime = endTime;
		assert invariant();
	}

	@Override
	public void update(final Observable o, final Object calendarObj) {
		assert invariant();
		Calendar calendar = (Calendar) calendarObj;
		endTime = calendar;
		Logging.getLogger().trace("Interval: " + calendar.getTime());
		parentTask.update(calendar);
		assert invariant();
	}

}
