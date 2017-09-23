package timeTracker;

import java.util.ArrayList;
import java.util.List;

public class Project {

	/**
	 * @uml.property  name="tasks"
	 */
	private List<Task> tasks;
	
	/**
	 */
	public Project() {
		this.tasks = new ArrayList<Task>();
		this.name = "(no name)";
	}
	
	/**
	 */
	public Project(String name) {
		this();
		this.name = name;
	}

	/**
	 * Getter of the property <tt>tasks</tt>
	 * @return  Returns the tasks.
	 * @uml.property  name="tasks"
	 */
	public List<Task> getTasks() {
		return tasks;
	}

	/**
	 * @uml.property  name="subprojects"
	 */
	private List<Project> subprojects;

	/**
	 * Getter of the property <tt>subprojects</tt>
	 * @return  Returns the subprojects.
	 * @uml.property  name="subprojects"
	 */
	public List<Project> getSubprojects() {
		return subprojects;
	}

		
	/**
	 */
	public long getTotalTime() {
		return 0;
	}

	/**
	 * @uml.property  name="name"
	 */
	private String name;

	/**
	 * Getter of the property <tt>name</tt>
	 * @return  Returns the name.
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the property <tt>name</tt>
	 * @param name  The name to set.
	 * @uml.property  name="name"
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 */
	public void addTask(Task task) {
		this.tasks.add(task);
	}

			
	/**
	 */
	public void addSubproject(Project project) {
		this.subprojects.add(project);
	}

	/**
	 * @uml.property  name="task"
	 * @uml.associationEnd  inverse="project:timeTracker.Task"
	 */
	private Task task;

	/**
	 * Getter of the property <tt>task</tt>
	 * @return  Returns the task.
	 * @uml.property  name="task"
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * Setter of the property <tt>task</tt>
	 * @param task  The task to set.
	 * @uml.property  name="task"
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * @uml.property  name="task1"
	 * @uml.associationEnd  inverse="project1:timeTracker.Task"
	 */
	private Task task1;

	/**
	 * Getter of the property <tt>task1</tt>
	 * @return  Returns the task1.
	 * @uml.property  name="task1"
	 */
	public Task getTask1() {
		return task1;
	}

	/**
	 * Setter of the property <tt>task1</tt>
	 * @param task1  The task1 to set.
	 * @uml.property  name="task1"
	 */
	public void setTask1(Task task1) {
		this.task1 = task1;
	}

}
