package timeTracker;

public class DataPrinterVisitor implements Visitor {	

	@Override
	public void visit(Project project) {
		project.display();
		for (Work work: project.getWorks()) {
			work.acceptVisitor(this);
		}
	}

	@Override
	public void visit(Task task) {
		task.display();
	}
	
}
