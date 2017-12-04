package report;

import java.util.ArrayList;
import java.util.List;

// Represents a table in the Report.
// It can be expanded dynamically.
public class Table extends Element {

	private boolean firstRowIsHeader;
	private boolean firstColumnIsHeader;

	public boolean isFirstRowHeader() {
		return firstRowIsHeader;
	}

	public boolean isFirstColumnHeader() {
		return firstRowIsHeader;
	}

	private int rows;

	public int getRows() {
		return rows;
	}

	private int columns;

	public int getColumns() {
		return columns;
	}

	private List<List<String>> table = null;

	public List<List<String>> getTable() {
		return table;
	}

	public void setTable(final List<List<String>> table) {
		this.table = table;
	}

	public Table(final int rows, final int columns,
			final boolean firstRowIsHeader, final boolean firstColumnIsHeader) {
		this.rows = rows;
		this.columns = columns;
		this.firstRowIsHeader = firstRowIsHeader;
		this.firstColumnIsHeader = firstColumnIsHeader;
		this.table = new ArrayList<>();
		for (int i = 0; i < rows; i++) {
			List<String> row = new ArrayList<>();
			for (int j = 0; j < columns; j++) {
				row.add("");
			}
			table.add(row);
		}
	}

	public void addRow() {
		List<String> fila = new ArrayList<>();
		for (int j = 0; j < columns; j++) {
			fila.add(null);
		}
		table.add(fila);
		rows++;
	}

	public void addRow(final List<String> stringList) {
		table.add(stringList);
		rows++;
	}

	public void setPosition(final int row, final int column, final String str) {
		table.get(row).set(column, str);
	}

	public String getPosition(final int row, final int column) {
		return table.get(row).get(column);
	}

	@Override
	public String toString() {
		return table.toString();
	}

	public void acceptVisitor(final ReportVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Table clone() {
		Table tableClone = new Table(rows, columns, firstRowIsHeader,
				firstColumnIsHeader);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tableClone.setPosition(i, j, this.getPosition(i, j));
			}
		}
		return tableClone;
	}
}
