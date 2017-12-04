package report;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;

import timetracker.Interval;
import timetracker.Project;
import timetracker.Task;
import timetracker.Work;
import utilities.DateUtilities;
import utilities.TimeFormatter;

// Specific implementation of a Report, which traverses the whole work tree
// and includes all projects, subprojects, tasks and intervals which fall inside
// the report period, ONLY IF the overlap is greater than the minimum time unit.
public class FullReport extends Report {

	private Table projectsTable;
	private Table subprojectsTable;
	private Table tasksTable;
	private Table intervalsTable;

	// These fields are used to keep track of the level of the tree
	// the Report is currently on. It is needed to know all the parents
	// of any subproject, task and interval.
	// It is also needed to assign a unique ID to each project and subproject.
	private LinkedList<Integer> currentLevel;
	private int count;

	public FullReport(final Calendar startDate, final Calendar endDate,
			final Formatter formatter, final Project workTreeRoot) {
		super(startDate, endDate, formatter, workTreeRoot);

		currentLevel = new LinkedList<>();
		count = 1;

		// table initialization
		projectsTable = new Table(0, 5, true, false);
		projectsTable.addRow(Arrays.asList(
				"#", "Project", "Start date", "End date", "Total time"
				));

		subprojectsTable = projectsTable.clone();

		tasksTable = projectsTable.clone();
		tasksTable.setPosition(0, 0, "Project");
		tasksTable.setPosition(0, 1, "Task");

		intervalsTable = new Table(0, 6, true, false);
		intervalsTable.addRow(Arrays.asList(
				"Project", "Task", "Interval",
				"Start date", "End date", "Duration"
				));
	}

	private String getCurrentLevelString() {
		if (currentLevel.isEmpty()) {
			return "None";
		}
		String str = "";
		for (int n : currentLevel) {
			str += Integer.toString(n);
			str += ".";
		}
		return str.substring(0, str.length() - 1);
	}

	@Override
	protected void addTitle() {
		getElements().add(new Title("Full Report"));
	}

	@Override
	protected void addWorkTreeInformation(final Project root) {
		assert root != null;
		assert root.getWorks() != null;
		// fills in all the necessary tables
		for (Work work : root.getWorks()) {
			work.acceptVisitor(this);
		}
		// adds the tables to the report, together with
		// a description of each one
		addElement(new Subtitle("Root projects"));
		addElement(projectsTable);
		addElement(new Separator());

		addElement(new Subtitle("Subprojects"));
		addElement(new Text(
				"Only subprojects which contain tasks with an interval inside"
				+ " the period are included."));
		addElement(subprojectsTable);
		addElement(new Separator());

		addElement(new Subtitle("Tasks"));
		addElement(new Text(
				"All tasks with an interval inside the period are shown,"
				+ " together with their parent project."));
		addElement(tasksTable);
		addElement(new Separator());

		addElement(new Subtitle("Intervals"));
		addElement(new Text(
				"Includes the start date, end date and duration of all"
				+ " intervals which fall inside the period (completely or"
				+ " partially), together with their parent task and project."));
		addElement(intervalsTable);
	}

	@Override
	public void visit(final Project project) {
		if (project.hasBeenStarted() && isWorkInsidePeriod(project)) {
			// calculates the start and end date of the project while
			// intersecting them with the bounds of the report
			Calendar startDate = DateUtilities.maxDate(getStartDate(),
					project.getStartDate());
			Calendar endDate = DateUtilities.minDate(getEndDate(),
					project.getEndDate());
			long duration = project.calculateDuration(getStartDate(),
					getEndDate());
			String number;
			Table table;
			if (project.getParentProject().isRoot()) {
				number = Integer.toString(count);
				table = projectsTable;
			} else {
				number = getCurrentLevelString() + "." + count;
				table = subprojectsTable;
			}
			
			// fills in the details of the project
			table.addRow(Arrays.asList(
					number,
					project.getName(),
					DateUtilities.getFormatter().format(startDate.getTime()),
					DateUtilities.getFormatter().format(endDate.getTime()),
					TimeFormatter.format(duration)
					));

			// adds a new level to the current tree level so that the project's
			// children know their parents.
			currentLevel.addLast(count);
			// resets the index counter to 1, so that any subproject starts
			// being indexed correctly
			count = 1;
			for (Work work : project.getWorks()) {
				work.acceptVisitor(this);
			}
			// removes the previously added level when all children have been
			// visited, and increases the counter to properly index the
			// next project
			count = currentLevel.removeLast() + 1;
		}
	}

	@Override
	public void visit(final Task task) {
		if (task.hasBeenStarted() && isWorkInsidePeriod(task)) {
			// calculates the start and end date of the task while
			// intersecting them with the bounds of the report
			Calendar startDate = DateUtilities.maxDate(getStartDate(),
					task.getStartDate());
			Calendar endDate = DateUtilities.minDate(getEndDate(),
					task.getEndDate());
			long duration = task
					.calculateDuration(getStartDate(), getEndDate());
			
			// fills in the details of the project
			tasksTable.addRow(Arrays.asList(
					getCurrentLevelString(),
					task.getName(),
					DateUtilities.getFormatter().format(startDate.getTime()),
					DateUtilities.getFormatter().format(endDate.getTime()),
					TimeFormatter.format(duration)
					));
			
			// visits every interval pertaining to the task while numbering
			// them, starting from 1
			int intervalCount = 1;
			for (Interval interval : task.getIntervals()) {
				if (isIntervalInsidePeriod(interval)) {
					// calculates the start and end date of the interval while
					// intersecting them with the bounds of the report
					startDate = DateUtilities.maxDate(getStartDate(),
							interval.getStartTime());
					endDate = DateUtilities.minDate(getEndDate(),
							interval.getEndTime());
					duration = interval.getDuration(getStartDate(),
							getEndDate());
					
					// fills in the details of the interval
					intervalsTable.addRow(Arrays.asList(
							getCurrentLevelString(),
							task.getName(),
							Integer.toString(intervalCount),
							DateUtilities.getFormatter().format(
									startDate.getTime()),
							DateUtilities.getFormatter().format(
									endDate.getTime()),
							TimeFormatter.format(duration)
							));
					intervalCount++;
				}
			}
		}
	}
	
}
