package report;

public class Separator extends Element {
	public void acceptVisitor(final ReportVisitor visitor) {
		visitor.visit(this);
	}
}
