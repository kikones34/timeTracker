package report;

public class Footer extends Element {
	private String string;
	public String getString() {
		return string;
	}
	public Footer() {
		string = "Time Tracker v1.0";
	}
	public void acceptVisitor(final ReportVisitor visitor) {
		visitor.visit(this);
	}
}
