package report;

// Interface for any object which can be visited by a Formatter.
// All subtypes of Element must implement this interface.
public interface ReportVisitable {
	void acceptVisitor(ReportVisitor visitor);
}
