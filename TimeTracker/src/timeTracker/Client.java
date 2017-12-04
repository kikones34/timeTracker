package timetracker;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import report.BriefReport;
import report.Formatter;
import report.FullReport;
import report.HtmlFormatter;
import report.PlainTextFormatter;
import report.Report;
import utilities.DateUtilities;
import utilities.Logging;

import ch.qos.logback.classic.Level;

// Simulates the interaction of the client with the application.
// Contains the main class and some test functions.
public final class Client {
	
	private Client() {
	}
	
	public static void main(final String[] args) throws InterruptedException {
		
		// parameter configuration
		Logging.getLogger().setLevel(Level.DEBUG);
		DateUtilities.setDefaultFormat("yyyy/MM/dd HH:mm:ss");
		Timer.setTimeUnit(2000);
		Timer.getInstance().start();
		
		Thread.sleep(100);
		
		// parameter that determines which test will be executed
		//generateTestFita2Session();
		int test = 6;
		boolean fullReport = true;
		Formatter reportFormatter = new HtmlFormatter();
		
		switch (test) {
			case 0: testCronometrarTasca(); break;
			case 1: testCronometrarTascaSimultani(); break;
			case 2: testSerialize(); break;
			case 3: testAutomaticEnding(); break;
			case 4: testAutomaticStarting(); break;
			case 5: testAutomaticEndingAndStarting(); break;
			case 6: testReport(fullReport, reportFormatter); break;
			default: System.out.println("Invalid test case selected.");
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
		
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
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
	
	public static void testCronometrarTascaSimultani()
			throws InterruptedException {
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
		
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
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
				System.out.println(
						"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
				root.acceptVisitor(new DataPrinterVisitor());
			} else {
				Logging.getLogger().error(
						"Error load session data, unexisting class");
			}
		} catch (IOException e) {
			Logging.getLogger().error("Couldn't open session file " + filename);
		}
	}
	
	public static void testAutomaticEnding() throws InterruptedException {
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
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
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
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
	
	public static void testAutomaticEndingAndStarting()
			throws InterruptedException {
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		Calendar startingDate = new GregorianCalendar();
		startingDate.add(Calendar.SECOND, 4);
		Project root = new Project();
		Task task = new AutomaticStarting(
				new AutomaticEnding(new Task("Automatic"), 4000), startingDate);
		root.addWork(task);
		Task task2 = new Task("Reference");
		root.addWork(task2);
		task2.start();
		Thread.sleep(16000);
		task2.stop();
	}
	

	
	public static Project quickSession() throws InterruptedException {
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
		
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
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
	
	public static Project longSession() throws InterruptedException {
		Project root = new Project();
		Project project1 = new Project("P1");
		Project project2 = new Project("P2");
		Project project3 = new Project("P3");
		Project project4 = new Project("P4");
		Project project5 = new Project("P5");
		Project project6 = new Project("P6");
		Task task1 = new Task("T1");
		Task task2 = new Task("T2");
		Task task3 = new Task("T3");
		Task task4 = new Task("T4");
		Task task5 = new Task("T5");
		Task task6 = new Task("T6");
		Task task7 = new Task("T7");
		Task task8 = new Task("T8");
		Task task9 = new Task("T9");
		root.addWork(project1);
		root.addWork(project2);
		root.addWork(project5);
		root.addWork(task1);
		root.addWork(task2);
		project1.addWork(task3);
		project2.addWork(task4);
		project2.addWork(task5);
		project5.addWork(project3);
		project5.addWork(project4);
		project5.addWork(task6);
		project3.addWork(task7);
		project3.addWork(task8);
		project4.addWork(project6);
		project6.addWork(task9);
		
		System.out.println(
				"Nom\tTemps inici\t\tTemps final\t\tDurada (hh:mm:ss)");
		
		Random random = new Random();
		Task[] tasks = {task1, task2, task3, task4, task5,
				task6, task7, task8, task9};
		for (Task task: tasks) {
			task.start();
			Thread.sleep((2 + random.nextInt(5)) * 1000);
			task.stop();
		}
		
		final String filename = "longSession.ser";
		
		try {
			SessionKeeper.saveSession(filename, root);
			System.out.println("Session saved.");
		} catch (IOException e) {
			Logging.getLogger().error("Unable to save session to " + filename);
		}
		
		return root;
	}
	
	public static Project generateTestFita2Session()
			throws InterruptedException {
		Project root = new Project();
		
		Project p1 = new Project("P1");
		Project p2 = new Project("P2");
		Project p12 = new Project("P1.2");
		Task t1 = new Task("T1");
		Task t2 = new Task("T2");
		Task t3 = new Task("T3");
		Task t4 = new Task("T4");
		
		root.addWork(p1);
		root.addWork(p2);
		
		p1.addWork(p12);
		
		p1.addWork(t1);
		p1.addWork(t2);
		p12.addWork(t4);
		p2.addWork(t3);
		
		t1.start();
		t4.start();
		Thread.sleep(4000);
		t1.stop();
		
		t2.start();
		Thread.sleep(6000);
		t2.stop();
		t4.stop();
		
		t3.start();
		Thread.sleep(4000);
		t3.stop();
		
		t2.start();
		Thread.sleep(2000);
		
		t3.start();
		Thread.sleep(4000);
		t2.stop();
		t3.stop();
		
		final String filename = "testFita2.ser";
		
		try {
			SessionKeeper.saveSession(filename, root);
			System.out.println("Session saved.");
		} catch (IOException e) {
			Logging.getLogger().error("Unable to save session to " + filename);
		}
		
		return root;
	}
	
	public static void testReport(final boolean full,
			final Formatter formatter) {
		Project root;
		try {
			root = (Project) SessionKeeper.loadSession("testFita2.ser");
		} catch (IOException e) {
			Logging.getLogger().error("Couldn't load session file.");
			return;
		}
		//root.acceptVisitor(new DataPrinterVisitor());
		/*Calendar startDate = new GregorianCalendar();
		startDate.add(Calendar.DAY_OF_MONTH, -1);
		Calendar endDate = new GregorianCalendar();
		endDate.add(Calendar.DAY_OF_MONTH, +1);*/
		Calendar startDate = new GregorianCalendar(2017, 10, 29, 20, 31, 20);
		Calendar endDate   = new GregorianCalendar(2017, 10, 29, 20, 31, 30);
		Report report;
		if (full) {
			report = new FullReport(startDate, endDate, formatter, root);
		} else {
			report = new BriefReport(startDate, endDate, formatter, root);
		}
		String reportString = report.generateReport();
		System.out.println(reportString);
		String filename;
		if (full) {
			filename = "fullReport";
		} else {
			filename = "briefReport";
		}
		report.saveReport(filename);
	}

}
