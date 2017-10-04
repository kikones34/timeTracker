package timeTracker;

import java.util.ArrayList;
import java.util.List;

public class Task extends Work {
	
	/** 
	 * @uml.property name="intervals"
	 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" aggregation="composite" inverse="task:timeTracker.Interval"
	 */
	private List<Interval> intervals;

	/** 
	 * Getter of the property <tt>intervals</tt>
	 * @return  Returns the intervals.
	 * @uml.property  name="intervals"
	 */
	public List<Interval> getIntervals() {
		return intervals;
	}

	@Override
	public long getTotalTime() {
		long totalTime = 0L;
		for (Interval interval : intervals) {
			totalTime += interval.getTotalTime();
		}
		return totalTime;
	}
	
	public Task(String name, String description) {
		super(name, description);
	}
	
	public Task(String name) {
		super(name);
	}
	
	public Task() {
		super();
	}

		
	/**
	 */
	public void start() {
		Interval interval = new Interval(this);
		intervals.add(interval);
		interval.start();
	}

			
	/**
	 */
	public void stop() {
		intervals.get(intervals.size()-1).stop();
	}
		
	/**
	 */
	@Override
	protected void initialize() {
		intervals = new ArrayList<Interval>();
	}

	@Override
	public void display() {
		System.out.println("Task: " + getEndDate().getTime());
	}

}
