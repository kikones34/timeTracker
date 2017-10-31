package timeTracker;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
 * Abstract class which represents a Task or a Project
 */
public abstract class Work implements Visitable, Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * @uml.property  name="startDate"
		 */
		private Calendar startDate;

		/**
		 * Getter of the property <tt>startDate</tt>
		 * @return  Returns the startDate.
		 * @uml.property  name="startDate"
		 */
		public Calendar getStartDate() {
			return startDate;
		}

		/**
		 * Setter of the property <tt>startDate</tt>
		 * @param startDate  The startDate to set.
		 * @uml.property  name="startDate"
		 */
		public void setStartDate(Calendar startDate) {
			this.startDate = startDate;
		}

		/**
		 * @uml.property  name="endDate"
		 */
		private Calendar endDate;

		/**
		 * Getter of the property <tt>endDate</tt>
		 * @return  Returns the endDate.
		 * @uml.property  name="endDate"
		 */
		public Calendar getEndDate() {
			return endDate;
		}

		/**
		 * Setter of the property <tt>endDate</tt>
		 * @param endDate  The endDate to set.
		 * @uml.property  name="endDate"
		 */
		public void setEndDate(Calendar endDate) {
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
		private Project parentProject;

		/** 
		 * Getter of the property <tt>project</tt>
		 * @return  Returns the project.
		 * @uml.property  name="project"
		 */
		public Project getParentProject() {
			return parentProject;
		}

		/** 
		 * Setter of the property <tt>project</tt>
		 * @param project  The project to set.
		 * @uml.property  name="project"
		 */
		public void setParentProject(Project project) {
			this.parentProject = project;
		}
		
		public Work(String name, String description) {
			this.name = name;
			this.description = description;
			this.startDate = new GregorianCalendar();
			this.endDate = new GregorianCalendar();
			initialize();
		}
		
		public Work(String name) {
			this(name, "<no description>");
		}
		
		public Work() {
			this("<no name>");
		}

			
		/**
		 */
		protected abstract void initialize();
		
		/**
		 */
		public void display() {
			if (parentProject != null) {
				System.out.println(
					MessageFormat.format(
						"{0}\t{1}\t{2}\t{3}",
						getName(),
						DateFormatting.getFormatter().format(getStartDate().getTime()),
						DateFormatting.getFormatter().format(getEndDate().getTime()),
						TimeFormatter.format(getDuration())
					)
				);
			}
		}
		
		/**
		 * 
		 */
		public abstract void updateDuration();
		
		/**
		 */
		public void update(Calendar endDate) {
			setEndDate(endDate);
			updateDuration();
			updateParent(endDate);
		}

		/**
		 */
		public void updateParent(Calendar endDate) {
			if (this.getParentProject().getParentProject() != null) {
				parentProject.update(endDate);
			} else {
				Logging.getLogger().info("root reached by " + getName());
				parentProject.acceptVisitor(new DataPrinterVisitor());
				System.out.println();
			}
		}

		/**
		 * @uml.property  name="duration"
		 */
		private long duration;

		/**
		 * Getter of the property <tt>duration</tt>
		 * @return  Returns the duration.
		 * @uml.property  name="duration"
		 */
		public long getDuration() {
			return duration;
		}

		/**
		 * Setter of the property <tt>duration</tt>
		 * @param duration  The duration to set.
		 * @uml.property  name="duration"
		 */
		public void setDuration(long duration) {
			this.duration = duration;
		}

}
