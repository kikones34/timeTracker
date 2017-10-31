package timeTracker;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;


/**
 * @uml.dependency   supplier="timeTracker.Timer"
 */
public class Interval implements Observer, Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Interval(Task task) {
			this.task = task;
			startTime = new GregorianCalendar();
		}

		/**
		 */
		public long getDuration() {
			long duration = this.endTime.getTimeInMillis() - this.startTime.getTimeInMillis();
			return duration - duration % Timer.getTimeUnit();
		}

		/**
		 * @uml.property   name="task"
		 * @uml.associationEnd   inverse="intervals:timeTracker.Task"
		 */
		private Task task;

		/** 
		 * Getter of the property <tt>task</tt>
		 * @return  Returns the task.
		 * @uml.property  name="task"
		 */
		public Task getTask() {
			return task;
		}

		/** 
		 * Setter of the property <tt>task</tt>
		 * @param task  The task to set.
		 * @uml.property  name="task"
		 */
		public void setTask(Task task) {
			this.task = task;
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
			task.update(calendar);
		}

}
