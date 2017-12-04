package report;

import java.util.Arrays;
import java.util.Calendar;

import timetracker.Project;
import timetracker.Task;
import timetracker.Work;
import utilities.DateUtilities;
import utilities.TimeFormatter;

// Specific implementation of a Report, which iterates only over the root
// projects and includes all which fall inside the report period, ONLY IF
// the overlap is greater than the minimum time unit.
public class BriefReport extends Report {

	private Table projectsTable;

	public BriefReport(final Calendar startDate, final Calendar endDate,
			final Formatter formatter, final Project workTreeRoot) {
		super(startDate, endDate, formatter, workTreeRoot);
		projectsTable = new Table(0, 4, true, false);
		projectsTable.addRow(Arrays.asList(
				"Project",
				"Start date",
				"End date",
				"Total time"
				));
	}

	@Override
	protected void addTitle() {
		addElement(new Title("Brief Report"));
	}

	@Override
	protected void addWorkTreeInformation(final Project root) {
		assert root != null;
		assert root.getWorks() != null;
		// fills in the projects table
		for (Work work : root.getWorks()) {
			work.acceptVisitor(this);
		}
		// adds the table to the report
		addElement(new Subtitle("Root projects"));
		addElement(projectsTable);
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
			
			projectsTable.addRow(Arrays.asList(
					project.getName(),
					DateUtilities.getFormatter().format(startDate.getTime()),
					DateUtilities.getFormatter().format(endDate.getTime()),
					TimeFormatter.format(duration)
					));
		}
	}

	@Override
	public void visit(final Task task) {
		// Nothing shall be done with tasks
	}

}
