package timeTracker;

public interface Visitor {
	public void visit(Project project);
	public void visit(Task task);
}
