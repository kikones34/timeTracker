package timeTracker;


public class Client {
	public static void main(String[] args) throws InterruptedException {
		Project root = new Project();
		Project project1 = new Project("Test project");
		Task task1 = new Task("Task of project 1");
		Task task2 = new Task("Another task of project 1");
		Task task3 = new Task("Independent task");
		root.addWork(project1);
		root.addWork(task3);
		project1.addWork(task1);
		project1.addWork(task2);
		
		task3.start();
		Thread.sleep(4000);
		task3.stop();
	}
}
