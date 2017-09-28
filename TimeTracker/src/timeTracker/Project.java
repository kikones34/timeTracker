package timeTracker;
import java.util.List;
import java.util.Collection;


public class Project extends Work {

	@Override
	public long getTotalTime() {
		long totalTime = 0L;
		for (Work work : works) {
			totalTime += work.getTotalTime();
		}
		return totalTime;
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
	}
	
}
