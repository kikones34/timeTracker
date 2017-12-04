package report;

// Interface for any object which can visit the Elements of a Report.
// Used for formatting.
public interface ReportVisitor {
	void visit(Title title);
	void visit(Subtitle subtitle);
	void visit(Table table);
	void visit(Separator separator);
	void visit(Footer footer);
	void visit(Text text);
}
