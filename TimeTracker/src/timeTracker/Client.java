package timeTracker;


public class Client {
	public static void main(String[] args) {
		Project project1 = new Project("Pr�ctiques DS");
		Project subproject1 = new Project("Sessi� 1");
		project1.addSubproject(subproject1);
		
	}
}
