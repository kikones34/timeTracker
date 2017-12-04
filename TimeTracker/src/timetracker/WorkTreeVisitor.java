package timetracker;

public interface WorkTreeVisitor {
	void visit(Project project);
	void visit(Task task);
}
