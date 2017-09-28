package timeTracker;

import java.util.Date;


public abstract class Work {

		
		/**
		 * @uml.property  name="startDate"
		 */
		private Date startDate;

		/**
		 * Getter of the property <tt>startDate</tt>
		 * @return  Returns the startDate.
		 * @uml.property  name="startDate"
		 */
		public Date getStartDate() {
			return startDate;
		}

		/**
		 * Setter of the property <tt>startDate</tt>
		 * @param startDate  The startDate to set.
		 * @uml.property  name="startDate"
		 */
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

		/**
		 * @uml.property  name="endDate"
		 */
		private Date endDate;

		/**
		 * Getter of the property <tt>endDate</tt>
		 * @return  Returns the endDate.
		 * @uml.property  name="endDate"
		 */
		public Date getEndDate() {
			return endDate;
		}

		/**
		 * Setter of the property <tt>endDate</tt>
		 * @param endDate  The endDate to set.
		 * @uml.property  name="endDate"
		 */
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
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
		 * @uml.property  name="description"
		 */
		private String description;

		/**
		 * Getter of the property <tt>description</tt>
		 * @return  Returns the description.
		 * @uml.property  name="description"
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * Setter of the property <tt>description</tt>
		 * @param description  The description to set.
		 * @uml.property  name="description"
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/** 
		 * @uml.property name="project"
		 * @uml.associationEnd inverse="works:timeTracker.Project"
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

			
		/**
		 */
		public abstract long getTotalTime();

		
		public Work(String name, String description) {
			this.name = name;
			this.description = description;
			this.startDate = new Date();
			this.endDate = new Date();
		}
		
		public Work(String name) {
			this(name, "<no description>");
		}
		
		public Work() {
			this("<no name>");
		}


}
