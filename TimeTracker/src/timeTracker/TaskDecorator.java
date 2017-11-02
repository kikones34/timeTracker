package timeTracker;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

// Serves as the base class of any task decorator.
public abstract class TaskDecorator extends Task {

	private static final long serialVersionUID = 1L;
	protected Task task;
	
	public TaskDecorator(Task task) {
		Logging.getLogger().trace("Inside TaskDecorator");
		this.task = task;
	}

	/* ===========================================================
	 * ===================== Task methods ========================
	 * =========================================================== */
	
	@Override
	public void acceptVisitor(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public List<Interval> getIntervals() {
		return task.getIntervals();
	}
	
	@Override
	public boolean isActive() {
		return task.isActive();
	}
		
	@Override
	public void start() {
		// XXX: we don't know how to avoid reimplementing this method
		if (!task.isActive()) {
			if (task.getStartDate() == null) {
				task.initialiseDates(new GregorianCalendar());
			}
			Interval activeInterval = new Interval(this);
			task.setActiveInterval(activeInterval);
			task.getIntervals().add(activeInterval);
			Timer.getInstance().addObserver(activeInterval);
		}
	}

	@Override
	public void stop() {
		task.stop();
	}
	
	// this method must be overridden with an empty method because
	// the TaskDecorator can't be initialised as if it were a Task.
	@Override
	protected void initialise() {
	}
	
	@Override
	public void updateDuration() {
		task.updateDuration();
	}
	
	@Override
	public Interval getActiveInterval() {
		return task.getActiveInterval();
	}
	
	@Override
	public void setActiveInterval(Interval activeInterval) {
		task.setActiveInterval(activeInterval);
	}

	
	/* ===========================================================
	 * ===================== Work methods ========================
	 * =========================================================== */
	
	@Override
	public Calendar getStartDate() {
		return task.getStartDate();
	}

	@Override
	public void setStartDate(Calendar startDate) {
		task.setStartDate(startDate);
	}

	@Override
	public Calendar getEndDate() {
		return task.getEndDate();
	}

	@Override
	public void setEndDate(Calendar endDate) {
		task.setEndDate(endDate);
	}

	@Override
	public String getName() {
		return task.getName();
	}

	@Override
	public void setName(String name) {
		task.setName(name);
	}

	@Override
	public String getDescription() {
		return task.getDescription();
	}

	@Override
	public void setDescription(String description) {
		task.setDescription(description);
	}

	@Override
	public Project getParentProject() {
		return task.getParentProject();
	}

	@Override
	public void setParentProject(Project project) {
		task.setParentProject(project);
	}
	
	@Override
	public boolean isRoot() {
		return task.isRoot();
	}
	
	@Override
	public void display() {
		task.display();
	}
	
	@Override
	public void initialiseDates(Calendar currentDate) {
		task.initialiseDates(currentDate);
	}
	
	@Override
	public void update(Calendar endDate) {
		Logging.getLogger().trace("Update task duration.");
		task.update(endDate);
	}

	@Override
	public void updateParent(Calendar endDate) {
		task.updateParent(endDate);
	}

	@Override
	public long getDuration() {
		return task.getDuration();
	}

	@Override
	public void setDuration(long duration) {
		task.setDuration(duration);
	}

}
