package report;

public class Subtitle extends Element {
	private String string;
	public String getString() {
		return string;
	}
	public Subtitle(final String string) {
		this.string = string;
	}
	public void acceptVisitor(final ReportVisitor visitor) {
		visitor.visit(this);
	}
}
