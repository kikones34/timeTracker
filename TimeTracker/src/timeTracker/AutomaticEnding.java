package timeTracker;

import java.util.Calendar;

public class AutomaticEnding extends TaskDecorator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private long maxRunningTime;
	
	public AutomaticEnding(Task task, long maxRunningTime) {
		super(task);
		assert(this.task != null);
		this.maxRunningTime = maxRunningTime;
	}
	
	@Override
	public void update(Calendar endDate) {
		super.update(endDate);
		Logging.getLogger().trace("getActiveInterval() != null = " + (getActiveInterval() != null));
		if (getActiveInterval() != null) {
			Logging.getLogger().trace("Automatic ending check");
			Logging.getLogger().trace(Long.toString(getDuration()));
			if (getDuration() >= maxRunningTime) {
				Logging.getLogger().trace("Task ended automatically");
				stop();
			}
		}
	}
	
}
