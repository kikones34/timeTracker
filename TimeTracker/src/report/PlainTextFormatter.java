package report;

import utilities.Logging;

// Specific type of Formatter. It transforms each element of the Report
// to plain text, while creating a raw text report.
public class PlainTextFormatter extends Formatter {

	private final String lineJump = System.getProperty("line.separator");
	private final String separator = repeatString("-", 100);

	private StringBuilder stringBuilder;

	public PlainTextFormatter() {
		setFileExtension(".txt");
		stringBuilder = new StringBuilder();
	}

	private String repeatString(final String string, final int n) {
		return new String(new char[n]).replace("\0", string);
	}

	private void appendLine(final String line) {
		stringBuilder.append(line);
		stringBuilder.append(lineJump);
	}

	@Override
	public String getString() {
		return stringBuilder.toString();
	}

	@Override
	public void visit(final Title title) {
		appendLine(title.getString());
	}

	@Override
	public void visit(final Subtitle subtitle) {
		appendLine(subtitle.getString());
	}

	@Override
	public void visit(final Table table) {
		// indicates the separation between columns of the table (in characters)
		final int columnSeparation = 2;

		final int rows = table.getRows();
		final int columns = table.getColumns();

		Logging.getLogger().debug("r: " + rows + " c: " + columns);
		Logging.getLogger().debug(table.toString());

		// determines the width of each column.
		// the width of a column is the maximum with amongst each
		// of its rows plus the column separation value.
		int[] lengths = new int[columns];
		for (int c = 0; c < columns; c++) {
			lengths[c] = 0;
			for (int r = 0; r < rows; r++) {
				String str = table.getPosition(r, c);
				int length = str.length();
				if (length > lengths[c]) {
					lengths[c] = length;
				}
			}
		}

		// generates the table adding the corresponding padding to each
		// row so it matches the previously calculated column width
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				String string = table.getPosition(r, c);
				int paddingLength = lengths[c] - string.length()
						+ columnSeparation;
				string += repeatString(" ", paddingLength);
				stringBuilder.append(string);
			}
			stringBuilder.append("\n");
		}
	}

	@Override
	public void visit(final Separator separator) {
		appendLine(this.separator);
	}

	@Override
	public void visit(final Footer footer) {
		appendLine(footer.getString());
	}

	@Override
	public void visit(final Text text) {
		appendLine(text.getString());
	}

}
