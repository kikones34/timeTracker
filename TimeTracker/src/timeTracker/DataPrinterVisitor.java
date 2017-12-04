package timetracker;

import java.text.MessageFormat;

import utilities.DateUtilities;
import utilities.TimeFormatter;

// A visitor which traverses the tree, printing each node in preorder
public class DataPrinterVisitor implements WorkTreeVisitor {

	private void displayWork(final Work work) {
		if (!work.isRoot()) {
			if (work.getStartDate() != null) {
				System.out.println(
					MessageFormat.format(
						"{0}\t{1}\t{2}\t{3}",
						work.getName(),
						DateUtilities.getFormatter().format(
								work.getStartDate().getTime()),
						DateUtilities.getFormatter().format(
								work.getEndDate().getTime()),
						TimeFormatter.format(work.getDuration())
					)
				);
			} else {
				System.out.println(work.getName());
			}
		}
	}
	
	@Override
	public void visit(final Project project) {
		displayWork(project);
		for (Work work: project.getWorks()) {
			work.acceptVisitor(this);
		}
	}

	@Override
	public void visit(final Task task) {
		displayWork(task);
	}
	
}
