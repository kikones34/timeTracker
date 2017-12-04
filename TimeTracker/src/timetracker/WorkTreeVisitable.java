package timetracker;

public interface WorkTreeVisitable {
	void acceptVisitor(WorkTreeVisitor visitor);
}
