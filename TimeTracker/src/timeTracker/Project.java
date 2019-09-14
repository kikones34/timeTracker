package timetracker;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

// Represents a collection of tasks and/or sub-projects.
public class Project extends Work {
	
	protected boolean invariant() {		
		return super.invariant() && works != null;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void acceptVisitor(final WorkTreeVisitor visitor) {
		assert invariant();
		visitor.visit(this);
		assert invariant();
	}
	
	public Project(final String name, final String description) {
		super(name, description);
	}
	
	public Project(final String name) {
		super(name);
	}
	
	public Project() {
		super();
	}

	/** 
	 * @uml.property name="works"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="project1:timetracker.Work"
	 */
	private List<Work> works;

	/** 
	 * Getter of the property <tt>works</tt>
	 * @return  Returns the works.
	 * @uml.property  name="works"
	 */
	public List<Work> getWorks() {
		return works;
	}

		
	/**
	 */
	public void addWork(final Work work) {
		assert invariant();
		works.add(work);
		work.setParentProject(this);
		assert invariant();
	}

	@Override
	protected void initialise() {
		works = new ArrayList<Work>();
	}
	
	// Calculates the duration of a project.
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
		for (Work work: works) {
			duration += work.calculateDuration(minDate, maxDate);
		}
		//postcondition
		assert duration >= 0L;
		
		assert invariant();
		
		return duration;
	}
	
	public boolean hasBeenStarted() {
		assert invariant();
		for (Work work: works) {
			if (work.hasBeenStarted()) {
				return true;
			}
		}
		assert invariant();
		return false;
	}

	@Override
	public void print() {
		display();
		for (Work work: works) {
			work.print();
		}
	}

	
}
