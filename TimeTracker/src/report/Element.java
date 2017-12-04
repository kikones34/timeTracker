package report;

// Abstract class which represents an element of the Report.
// It can be a title, a subtitle, a table, etc.
// It is part of a Visitor pattern, being a visitable object
// by formatter visitors.
public abstract class Element implements ReportVisitable {

}
