package timeTracker;


public class Client {
	public static void main(String[] args) {
		Project project1 = new Project("Pràctiques DS");
		Project subproject1 = new Project("Sessió 1");
		project1.addSubproject(subproject1);
		
	}
}
