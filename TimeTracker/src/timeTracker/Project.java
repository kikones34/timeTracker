package timeTracker;
import java.util.ArrayList;
import java.util.List;

public class Project extends Work implements Visitable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visit(this);
	}
	
	public Project(String name, String description) {
		super(name, description);
	}
	
	public Project(String name) {
		super(name);
	}
	
	public Project() {
		super();
	}

	/** 
	 * @uml.property name="works"
	 * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite" inverse="project:timeTracker.Work"
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
	public void addWork(Work work) {
		works.add(work);
		work.setParentProject(this);
	}

	@Override
	protected void initialize() {
		works = new ArrayList<Work>();
	}
	
	@Override
	public void updateDuration() {
		long duration = 0L;
		for (Work work: works) {
			duration += work.getDuration();
		}
		setDuration(duration);
	}
	
}
