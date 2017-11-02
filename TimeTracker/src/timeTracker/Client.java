package timeTracker;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ch.qos.logback.classic.Level;

// TODO
// passar display al DataPrinterVisitor
// millorar comentaris (explicar el que i el per que)
// revisar decorator

// Simulates the interaction of the client with the application.
// Contains the main class and some test functions.
public class Client {
	
	public static void main(String[] args) throws InterruptedException {
		
		// parameter configuration
		Logging.getLogger().setLevel(Level.INFO);
		DateFormatting.setFormat("yyyy/MM/dd hh:mm:ss");
		Timer.setTimeUnit(2000);
		Timer.getInstance().start();
		
		Thread.sleep(100);
		
		// parameter that determines which test will be executed
		int test = 5;
		
		switch (test) {
			case 0: testCronometrarTasca(); break;
			case 1: testCronometrarTascaSimultani(); break;
			case 2: testSerialize(); break;
			case 3: testAutomaticEnding(); break;
			case 4: testAutomaticStarting(); break;
			case 5: testAutomaticEndingAndStarting(); break;
		}
		
		Timer.getInstance().stop();
	}
	
	public static void testCronometrarTasca() throws InterruptedException {
		Project root = new Project();
		Project project1 = new Project("P1");
		Project project2 = new Project("P2");
		Task task1 = new Task("T1");
		Task task2 = new Task("T2");
		Task task3 = new Task("T3");
		root.addWork(project1);
		project1.addWork(task3);
		project1.addWork(project2);
		project2.addWork(task1);
		project2.addWork(task2);
		
		System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
		task3.start();
		Thread.sleep(3000);
		task3.stop();
		
		Thread.sleep(7000);
		
		task2.start();
		Thread.sleep(10000);
		task2.stop();
		
		task3.start();
		Thread.sleep(2000);
		task3.stop();
	}
	
	public static void testCronometrarTascaSimultani() throws InterruptedException {
		Project root = new Project();
		Project project1 = new Project("P1");
		Project project2 = new Project("P2");
		Task task1 = new Task("T1");
		Task task2 = new Task("T2");
		Task task3 = new Task("T3");
		root.addWork(project1);
		project1.addWork(task3);
		project1.addWork(project2);
		project2.addWork(task1);
		project2.addWork(task2);
		
		System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
		task3.start();
		Thread.sleep(4000);
		
		task2.start();
		Thread.sleep(2000);
		task3.stop();
		
		Thread.sleep(2000);
		
		task1.start();
		Thread.sleep(4000);
		task1.stop();
		
		Thread.sleep(2000);
		task2.stop();
		
		Thread.sleep(4000);
		
		task3.start();
		Thread.sleep(2000);
		task3.stop();
	}
	
	public static void testSerialize() throws InterruptedException {
		String filename = "session.ser";
		Work root = quickSession();
		Logging.getLogger().trace(Integer.toString(root.hashCode()));
		root.acceptVisitor(new DataPrinterVisitor());
		try {
			SessionKeeper.saveSession(filename, root);
			System.out.println("Session saved.");
		} catch (IOException e) {
			Logging.getLogger().error("Unable to save session to " + filename);
		}
		try {
			root = SessionKeeper.loadSession(filename);
			if (root != null) {
				System.out.println("Session loaded.");
				System.out.println();
				System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
				root.acceptVisitor(new DataPrinterVisitor());
			} else {
				Logging.getLogger().error("Error load session data, unexisting class");
			}
		} catch (IOException e) {
			Logging.getLogger().error("Couldn't open session file " + filename);
		}
	}
	
	public static void testAutomaticEnding() throws InterruptedException {
		System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		Project root = new Project();
		Task task = new AutomaticEnding(new Task("Automatic"), 4000);
		root.addWork(task);
		Task task2 = new Task("Reference");
		root.addWork(task2);
		task.start();
		task2.start();
		Thread.sleep(12000);
		task2.stop();
	}

	public static void testAutomaticStarting() throws InterruptedException {
		System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		Calendar startingDate = new GregorianCalendar();
		startingDate.add(Calendar.SECOND, 4);
		Project root = new Project();
		Task task = new AutomaticStarting(new Task("Automatic"), startingDate);
		root.addWork(task);
		Task task2 = new Task("Reference");
		root.addWork(task2);
		task2.start();
		Thread.sleep(12000);
		task.stop();
		task2.stop();
	}
	
	public static void testAutomaticEndingAndStarting() throws InterruptedException {
		System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		Calendar startingDate = new GregorianCalendar();
		startingDate.add(Calendar.SECOND, 4);
		Project root = new Project();
		Task task = new AutomaticStarting(new AutomaticEnding(new Task("Automatic"), 4000), startingDate);
		root.addWork(task);
		Task task2 = new Task("Reference");
		root.addWork(task2);
		task2.start();
		Thread.sleep(16000);
		task2.stop();
	}
	

	
	public static Work quickSession() throws InterruptedException {
		Project root = new Project();
		Project project1 = new Project("P1");
		Project project2 = new Project("P2");
		Project project3 = new Project("P3");
		Task task1 = new Task("T1");
		Task task2 = new Task("T2");
		Task task3 = new Task("T3");
		root.addWork(project1);
		root.addWork(project3);
		project1.addWork(task3);
		project1.addWork(project2);
		project2.addWork(task1);
		project2.addWork(task2);
		
		System.out.println("Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
		task3.start();
		Thread.sleep(3000);
		task3.stop();
		
		task2.start();
		Thread.sleep(3000);
		task2.stop();
		
		task3.start();
		Thread.sleep(2000);
		task3.stop();
		
		return root;
	}
	


}
