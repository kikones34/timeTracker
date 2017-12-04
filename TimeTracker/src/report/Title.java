package report;

public class Title extends Element {
	private String string;
	public String getString() {
		return string;
	}
	public Title(final String string) {
		this.string = string;
	}
	public void acceptVisitor(final ReportVisitor visitor) {
		visitor.visit(this);
	}
}
