package timeTracker;

import java.util.List;


public class Task {

	/**
	 * @uml.property  name="intervals"
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

	/**
	 * Setter of the property <tt>intervals</tt>
	 * @param intervals  The intervals to set.
	 * @uml.property  name="intervals"
	 */
	public void setIntervals(List<Interval> intervals) {
		this.intervals = intervals;
	}

		
		/**
		 */
		public long getTotalTime(){
			return 0;
		}


		/**
		 * @uml.property  name="name"
		 */
		private String name;

		/**
		 * Getter of the property <tt>name</tt>
		 * @return  Returns the name.
		 * @uml.property  name="name"
		 */
		public String getName() {
			return name;
		}

		/**
		 * Setter of the property <tt>name</tt>
		 * @param name  The name to set.
		 * @uml.property  name="name"
		 */
		public void setName(String name) {
			this.name = name;
		}


		/**
		 * @uml.property  name="maxTime"
		 */
		private long maxTime;

		/**
		 * Getter of the property <tt>maxTime</tt>
		 * @return  Returns the maxTime.
		 * @uml.property  name="maxTime"
		 */
		public long getMaxTime() {
			return maxTime;
		}

		/**
		 * Setter of the property <tt>maxTime</tt>
		 * @param maxTime  The maxTime to set.
		 * @uml.property  name="maxTime"
		 */
		public void setMaxTime(long maxTime) {
			this.maxTime = maxTime;
		}


		/**
		 * @uml.property  name="automaticStartTime"
		 */
		private long automaticStartTime;

		/**
		 * Getter of the property <tt>automaticStartTime</tt>
		 * @return  Returns the automaticStartTime.
		 * @uml.property  name="automaticStartTime"
		 */
		public long getAutomaticStartTime() {
			return automaticStartTime;
		}

		/**
		 * Setter of the property <tt>automaticStartTime</tt>
		 * @param automaticStartTime  The automaticStartTime to set.
		 * @uml.property  name="automaticStartTime"
		 */
		public void setAutomaticStartTime(long automaticStartTime) {
			this.automaticStartTime = automaticStartTime;
		}


		/**
		 * @uml.property  name="project"
		 * @uml.associationEnd  inverse="task:timeTracker.Project"
		 */
		private Project project;

		/**
		 * Getter of the property <tt>project</tt>
		 * @return  Returns the project.
		 * @uml.property  name="project"
		 */
		public Project getProject() {
			return project;
		}

		/**
		 * Setter of the property <tt>project</tt>
		 * @param project  The project to set.
		 * @uml.property  name="project"
		 */
		public void setProject(Project project) {
			this.project = project;
		}

}
