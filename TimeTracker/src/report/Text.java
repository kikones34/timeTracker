package report;

public class Text extends Element {
	private String string;
	public String getString() {
		return string;
	}
	public Text(final String string) {
		this.string = string;
	}
	public void acceptVisitor(final ReportVisitor visitor) {
		visitor.visit(this);
	}
}
