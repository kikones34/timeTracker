package timeTracker;


public class Interval {

		
		/**
		 */
		public long getTotalTime() {
			return endTime - startTime;
		}

		/**
		 */
		public void startTimer(){
			timer.start();
		}

				
		/**
		 */
		public void stopTimer(){
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
		private long startTime;

		/** 
		 * Getter of the property <tt>start</tt>
		 * @return  Returns the start.
		 * @uml.property  name="startTime"
		 */
		public long getStartTime() {
			return startTime;
		}

		/** 
		 * Setter of the property <tt>start</tt>
		 * @param start  The start to set.
		 * @uml.property  name="startTime"
		 */
		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}

		/**
		 * @uml.property  name="endTime"
		 */
		private long endTime;

		/** 
		 * Getter of the property <tt>finish</tt>
		 * @return  Returns the finish.
		 * @uml.property  name="endTime"
		 */
		public long getEndTime() {
			return endTime;
		}

		/** 
		 * Setter of the property <tt>finish</tt>
		 * @param finish  The finish to set.
		 * @uml.property  name="endTime"
		 */
		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}

		/**
		 * @uml.property  name="timer"
		 */
		private Timer timer;

		/**
		 * Getter of the property <tt>timer</tt>
		 * @return  Returns the timer.
		 * @uml.property  name="timer"
		 */
		public Timer getTimer() {
			return timer;
		}

		/**
		 * Setter of the property <tt>timer</tt>
		 * @param timer  The timer to set.
		 * @uml.property  name="timer"
		 */
		public void setTimer(Timer timer) {
			this.timer = timer;
		}

}
