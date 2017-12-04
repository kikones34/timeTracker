package report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import timetracker.Interval;
import timetracker.Project;
import timetracker.Timer;
import timetracker.Work;
import timetracker.WorkTreeVisitor;
import utilities.DateUtilities;
import utilities.Logging;

// Abstract class representing a Report.
// It is part of a Strategy pattern: you need to indicate a way to
// format it by providing a Formatter object.
// It is part of a Visitor pattern: you visit the work tree to
// generate a list of all the elements which the report should contain.
public abstract class Report implements WorkTreeVisitor {

	/**
	 * @uml.property  name="elements"
	 * @uml.associationEnd  multiplicity="(1 -1)" inverse="report:report.Element"
	 */
	private List<Element> elements;
	
	/**
	 * @uml.property  name="formatter"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="report:report.Formatter"
	 */
	private Formatter formatter;
	
	private Calendar startDate;
	private Calendar endDate;
	private Project workTreeRoot;
	private String reportString;

	protected List<Element> getElements() {
		return elements;
	}

	protected Formatter getFormatter() {
		return formatter;
	}

	protected Calendar getStartDate() {
		return startDate;
	}

	protected Calendar getEndDate() {
		return endDate;
	}

	public Report(final Calendar startDate, final Calendar endDate,
			final Formatter formatter, final Project workTreeRoot) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.formatter = formatter;
		this.workTreeRoot = workTreeRoot;
		this.elements = new ArrayList<Element>();
	}

	protected void addElement(final Element element) {
		elements.add(element);
	}

	protected abstract void addTitle();

	protected void addSeparator() {
		elements.add(new Separator());
	}

	protected void addPeriodInformation() {
		elements.add(new Subtitle("Period"));
		Table table = new Table(4, 2, true, true);
		table.setPosition(0, 1, "Date");
		table.setPosition(1, 0, "From");
		table.setPosition(1, 1,
				DateUtilities.getFormatter().format(startDate.getTime()));
		table.setPosition(2, 0, "To");
		table.setPosition(2, 1,
				DateUtilities.getFormatter().format(endDate.getTime()));
		table.setPosition(3, 0, "Report generation date");
		table.setPosition(
				3,
				1,
				DateUtilities.getFormatter().format(
						new GregorianCalendar().getTime()));
		elements.add(table);
	}

	protected void addFooter() {
		elements.add(new Footer());
	}

	protected abstract void addWorkTreeInformation(Project root);

	protected void formatElements() {
		for (Element e : elements) {
			e.acceptVisitor(formatter);
		}
		reportString = formatter.getString();
	}

	// calculates if a given work is inside the period of the report
	// if the work intersects with the period, but the intersection is
	// smaller than the lowest time unit, it is considered to be outside
	// the period.
	protected boolean isWorkInsidePeriod(final Work work) {
		if (work.getStartDate().after(getEndDate())
				|| work.getEndDate().before(getStartDate())) {
			return false;
		} else {
			Calendar startDate = DateUtilities.maxDate(getStartDate(),
					work.getStartDate());
			Calendar endDate = DateUtilities.minDate(getEndDate(),
					work.getEndDate());
			return endDate.getTimeInMillis() - startDate.getTimeInMillis()
					>= Timer.getTimeUnit();
		}
	}

	// works the same way as the method "isWorkInsidePeriod", but
	// it's adapted for intervals.
	protected boolean isIntervalInsidePeriod(final Interval interval) {
		if (interval.getStartTime().after(getEndDate())
				|| interval.getEndTime().before(getStartDate())) {
			return false;
		} else {
			Calendar startDate = DateUtilities.maxDate(getStartDate(),
					interval.getStartTime());
			Calendar endDate = DateUtilities.minDate(getEndDate(),
					interval.getEndTime());
			return endDate.getTimeInMillis() - startDate.getTimeInMillis()
					>= Timer.getTimeUnit();
		}
	}

	// template method to construct the report
	public String generateReport() {
		addSeparator();
		addTitle();
		addSeparator();
		addPeriodInformation();
		addSeparator();
		addWorkTreeInformation(workTreeRoot);
		addSeparator();
		addFooter();
		formatElements();
		return reportString;
	}

	public boolean saveReport(final String filename) {
		final String fullFilename = filename + formatter.getFileExtension();
		Logging.getLogger().info("File filename: " + fullFilename);

		try {
			Files.write(Paths.get(fullFilename), formatter.getString()
					.getBytes());
		} catch (IOException e) {
			Logging.getLogger().error("Error while saving report.");
			return false;
		}

		return true;
	}

}
