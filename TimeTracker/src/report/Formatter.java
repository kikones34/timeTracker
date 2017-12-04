package report;

// Abstract class representing a Report formatter.
// It is part of a Visitor pattern, being the visitor of all the
// elements of which the Report is composed, to format them.
// It is part of a Strategy pattern, being the strategy used to
// format the Report.
public abstract class Formatter implements ReportVisitor {
	
	private String fileExtension;
	
	public String getFileExtension() {
		return fileExtension;
	}
	
	protected void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public abstract String getString();

}
