package timeTracker;

import java.util.Calendar;

// Task decorator which allows to specify a maximum interval duration.
// When an interval reaches the specified duration, it stops automatically.
public class AutomaticEnding extends TaskDecorator {

	private static final long serialVersionUID = 1L;
	private long maxRunningTime;
	
	public AutomaticEnding(Task task, long maxRunningTime) {
		super(task);
		assert(this.task != null);
		this.maxRunningTime = maxRunningTime;
	}
	
	@Override
	public void update(Calendar endDate) {
		super.update(endDate);
		Logging.getLogger().trace("isActive() = " + isActive());
		if (isActive()) {
			Logging.getLogger().trace("Automatic ending check");
			Logging.getLogger().trace(Long.toString(getDuration()));
			if (getActiveInterval().getDuration() >= maxRunningTime) {
				Logging.getLogger().trace("Task ended automatically");
				stop();
				assert(!isActive());
			}
		}
	}
	
}
