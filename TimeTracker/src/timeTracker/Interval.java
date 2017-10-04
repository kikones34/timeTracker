package timeTracker;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;


public class Interval implements Observer {
	
	
		/**
		 * @uml.property  name="timer"
		 */
		public static Timer timer = new Timer();
		
		public Interval(Task task) {
			this.task = task;
		}

		/**
		 */
		public long getTotalTime() {
			// TODO
			return 0;
		}

		/**
		 */
		public void start() {
			Interval.timer.addObserver(this);
		}

		
		/**
		 */
		public void stop() {
			Interval.timer.deleteObserver(this);
		}

		/** 
		 * @uml.property name="task"
		 * @uml.associationEnd inverse="intervals:timeTracker.Task"
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
			// XXX: is this cast acceptable?
			Calendar calendar = (Calendar)calendarObj;
			endTime = calendar;
			System.out.println("Interval: " + calendar.getTime());
			task.update(calendar);
		}

}
