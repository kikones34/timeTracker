package timeTracker;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

// Represents a time interval. Must pertain to a task.
// It indicates the intervals in which the task has been active.
public class Interval implements Observer, Serializable {
		
		private static final long serialVersionUID = 1L;

		public Interval(Task parentTask) {
			this.parentTask = parentTask;
			startTime = new GregorianCalendar();
		}

		// calculates the interval duration and rounds it to the nearest
		// multiple of the minimum time unit specified by the Timer
		public long getDuration() {
			long duration = this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis();
			long mod = duration % Timer.getTimeUnit();
			if (mod < Timer.getTimeUnit()/2) {
				duration -= mod;
			} else {
				duration += Timer.getTimeUnit() - mod;
			}
			return duration;
		}

		/**
		 * @uml.property   name="parentTask"
		 * @uml.associationEnd   inverse="intervals:timeTracker.Task"
		 */
		private Task parentTask;

		/** 
		 * Getter of the property <tt>parentTask</tt>
		 * @return  Returns the parentTask.
		 * @uml.property  name="parentTask"
		 */
		public Task getParentTask() {
			return parentTask;
		}

		/** 
		 * Setter of the property <tt>parentTask</tt>
		 * @param task  The parentTask to set.
		 * @uml.property  name="parentTask"
		 */
		public void setParentTask(Task parentTask) {
			this.parentTask = parentTask;
		}

		/**
		 * @uml.property  name="startTime"
		 */
		private Calendar startTime;

		/** 
		 * Getter of the property <tt>start</tt>
		 * @return  Returns the start.
		 * @uml.property  name="startTime"
		 */
		public Calendar getStartTime() {
			return startTime;
		}

		/** 
		 * Setter of the property <tt>start</tt>
		 * @param start  The start to set.
		 * @uml.property  name="startTime"
		 */
		public void setStartTime(Calendar startTime) {
			this.startTime = startTime;
		}

		/**
		 * @uml.property  name="endTime"
		 */
		private Calendar endTime;

		/** 
		 * Getter of the property <tt>finish</tt>
		 * @return  Returns the finish.
		 * @uml.property  name="endTime"
		 */
		public Calendar getEndTime() {
			return endTime;
		}

		/** 
		 * Setter of the property <tt>finish</tt>
		 * @param finish  The finish to set.
		 * @uml.property  name="endTime"
		 */
		public void setEndTime(Calendar endTime) {
			this.endTime = endTime;
		}

		@Override
		public void update(Observable o, Object calendarObj) {
			Calendar calendar = (Calendar)calendarObj;
			endTime = calendar;
			Logging.getLogger().trace("Interval: " + calendar.getTime());
			parentTask.update(calendar);
		}

}
