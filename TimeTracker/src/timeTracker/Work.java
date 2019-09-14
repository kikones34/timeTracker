package timetracker;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Calendar;

import utilities.DateUtilities;
import utilities.Logging;
import utilities.TimeFormatter;

// Represents a node in the project/task tree.
// Serves as the base class for Project and Task.
public abstract class Work implements WorkTreeVisitable, Serializable {

	protected boolean invariant() {
		return name != null && description != null && duration >= 0;
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @uml.property name="startDate"
	 */
	private Calendar startDate = null;

	/**
	 * Getter of the property <tt>startDate</tt>
	 * 
	 * @return Returns the startDate.
	 * @uml.property name="startDate"
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * Setter of the property <tt>startDate</tt>
	 * 
	 * @param startDate
	 *            The startDate to set.
	 * @uml.property name="startDate"
	 */
	public void setStartDate(final Calendar startDate) {
		assert invariant();
		this.startDate = startDate;
		assert invariant();
	}

	/**
	 * @uml.property name="endDate"
	 */
	private Calendar endDate = null;

	/**
	 * Getter of the property <tt>endDate</tt>
	 * 
	 * @return Returns the endDate.
	 * @uml.property name="endDate"
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * Setter of the property <tt>endDate</tt>
	 * 
	 * @param endDate
	 *            The endDate to set.
	 * @uml.property name="endDate"
	 */
	public void setEndDate(final Calendar endDate) {
		assert invariant();
		this.endDate = endDate;
		assert invariant();
	}

	/**
	 * @uml.property name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * 
	 * @return Returns the name.
	 * @uml.property name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * 
	 * @param name
	 *            The name to set.
	 * @uml.property name="name"
	 */
	public void setName(final String name) {
		assert invariant();
		this.name = name;
		assert invariant();
	}

	/**
	 * @uml.property name="description"
	 */
	private String description;

	/**
	 * Getter of the property <tt>description</tt>
	 * 
	 * @return Returns the description.
	 * @uml.property name="description"
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter of the property <tt>description</tt>
	 * 
	 * @param description
	 *            The description to set.
	 * @uml.property name="description"
	 */
	public void setDescription(final String description) {
		assert invariant();
		this.description = description;
		assert invariant();
	}

	/**
	 * @uml.property name="parentProject"
	 * @uml.associationEnd multiplicity="(1 1)"
	 *                     inverse="work:timetracker.Project"
	 */
	private Project parentProject;

	/**
	 * Getter of the property <tt>project</tt>
	 * 
	 * @return Returns the project.
	 * @uml.property name="project"
	 */
	public Project getParentProject() {
		return parentProject;
	}

	/**
	 * Setter of the property <tt>project</tt>
	 * 
	 * @param project
	 *            The project to set.
	 * @uml.property name="project"
	 */
	public void setParentProject(final Project project) {
		assert invariant();
		this.parentProject = project;
		assert invariant();
	}

	public Work(final String name, final String description) {
		this.name = name;
		this.description = description;
		this.duration = 0L;
		initialise();
		assert invariant();
	}

	public Work(final String name) {
		this(name, "<no description>");
	}

	public Work() {
		this("<no name>");
	}

	// gets called when constructing a new node
	// must properly initialise all attributes specific to the node type
	protected abstract void initialise();

	public boolean isRoot() {
		return parentProject == null;
	}

	public void initialiseDates(final Calendar currentDate) {
		assert invariant();
		if (!isRoot()) {
			setStartDate(currentDate);
			setEndDate(currentDate);
			if (getParentProject().getStartDate() == null) {
				getParentProject().initialiseDates(currentDate);
			}
		}
		assert invariant();
	}

	public void updateDuration() {
		assert invariant();
		setDuration(calculateDuration(null, null));
		assert invariant();
	}

	public abstract long calculateDuration(Calendar minDate, Calendar maxDate);

	// template method for updating a node
	public void update(final Calendar endDate) {
		assert invariant();
		setEndDate(endDate);
		updateDuration();
		updateParent(endDate);
		assert invariant();
	}

	public void updateParent(final Calendar endDate) {
		assert invariant();
		if (!this.getParentProject().isRoot()) {
			parentProject.update(endDate);
		} else {
			// when the update chain reaches the root,
			// the entire tree is printed
			Logging.getLogger().info("root reached by " + getName());
			parentProject.print();
			//parentProject.acceptVisitor(new DataPrinterVisitor());
			System.out.println();
		}
		assert invariant();
	}
	
	public void display() {
		if (!isRoot()) {
			if (getStartDate() != null) {
				System.out.println(
					MessageFormat.format(
						"{0}\t{1}\t{2}\t{3}",
						getName(),
						DateUtilities.getFormatter().format(
								getStartDate().getTime()),
						DateUtilities.getFormatter().format(
								getEndDate().getTime()),
						TimeFormatter.format(getDuration())
					)
				);
			} else {
				System.out.println(getName());
			}
		}
	}
	
	public abstract void print();

	/**
	 * @uml.property name="duration"
	 */
	private long duration;

	/**
	 * Getter of the property <tt>duration</tt>
	 * 
	 * @return Returns the duration.
	 * @uml.property name="duration"
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Setter of the property <tt>duration</tt>
	 * 
	 * @param duration
	 *            The duration to set.
	 * @uml.property name="duration"
	 */
	public void setDuration(final long duration) {
		assert invariant();
		this.duration = duration;
		assert invariant();
	}

	public abstract boolean hasBeenStarted();

}
