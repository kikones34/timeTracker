package report;

// Specific type of Formatter. It transforms each element of the Report
// to an html element, while composing a whole webpage.
// Relies on the provided Webpage class.
public class HtmlFormatter extends Formatter {

	/**
	 * @uml.property  name="webpage"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="htmlFormatter:report.Webpage"
	 */
	private Webpage webpage;

	public HtmlFormatter() {
		setFileExtension(".htm");
		webpage = new Webpage();
	}

	@Override
	public String getString() {
		return webpage.toString();
	}

	@Override
	public void visit(final Title title) {
		webpage.addHeader(title.getString(), 1, true);
	}

	@Override
	public void visit(final Subtitle subtitle) {
		webpage.addHeader(subtitle.getString(), 2, false);
	}

	@Override
	public void visit(final Table table) {
		webpage.addTable(table.getTable(), table.isFirstRowHeader(),
				table.isFirstColumnHeader());
	}

	@Override
	public void visit(final Separator separator) {
		webpage.addSeparatorLine();
	}

	@Override
	public void visit(final Footer footer) {
		webpage.addItalicsText(footer.getString());
	}

	@Override
	public void visit(final Text text) {
		webpage.addPlainText(text.getString());
		webpage.addLineJump();
		webpage.addLineJump();
	}

}
