package timeTracker;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

/**
 * @uml.dependency   supplier="timeTracker.Timer"
 */
public class AutomaticStarting extends TaskDecorator implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	private Calendar startingDate;
	
	public AutomaticStarting(Task task, Calendar startingDate) {
		super(task);
		assert(this.task != null);
		this.startingDate = startingDate;
		Timer.getInstance().addObserver(this);
	}

	@Override
	public void update(Observable o, Object calendarObj) {
		if (getActiveInterval() == null) {
			Calendar calendar = (Calendar)calendarObj;
			if (calendar.after(startingDate)) {
				Logging.getLogger().debug("Date is after starting date.");
				start();
			}
		} else {
			Timer.getInstance().deleteObserver(this);
		}
	}
	
}
