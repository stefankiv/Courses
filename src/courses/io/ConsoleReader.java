package courses.io;

import java.io.IOException;
import java.util.*;

import courses.model.*;

public class ConsoleReader extends AbstractReader {
	private final Scanner sc = new Scanner(System.in);
	
	public Academy read() throws IOException {
		return getAcademy();
	}
	
	private Academy getAcademy() {
		Academy academy = new Academy();
		System.out.print("Enter the name of the Academy: "); //TODO try other ways to read from console
		academy.setName(sc.nextLine());
		
		System.out.print("Enter a number of courses: ");
		int numOfCourses = sc.nextInt();
		sc.nextLine();
		academy.setCourses(getSetOfCourses(numOfCourses));
		
		return academy;
	}
	
	private Set<Course> getSetOfCourses(int count) {
		Set<Course> set = new HashSet<Course>();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				set.add(getCourse());
			}
		}
		
		return set;
	}

	private Course getCourse() {
		Course course = new Course();
				
		System.out.print("Enter the name of the course: ");
		course.setName(sc.nextLine());
		
		System.out.println("Enter the start date: ");
		course.setStartDate(getDate());
		
		System.out.println("Enter the end date: ");
		course.setEndDate(getDate());
		
		System.out.print("Enter a number of modules: ");
		int numOfModules = sc.nextInt();
		sc.nextLine();
		course.setModules(getSetOfModules(numOfModules));
		
		return course;
	}
	
	private Set<Module> getSetOfModules(int count) {
		Set<Module> set = new HashSet<Module>();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				set.add(getModule());
			}
		}
		
		return set;
	}

	private GregorianCalendar getDate() {
		int day;
		int month;
		int year;
		System.out.print("day of month: ");
		day = sc.nextInt();
		System.out.print("month (0 for January): ");
		month = sc.nextInt();
		System.out.print("year: ");
		year = sc.nextInt();
		sc.nextLine();
		return new GregorianCalendar(year, month, day);
	}

	private Module getModule() {
		Module module = null;
		
		System.out.print("Enter the name of the module: ");
		String name = sc.nextLine();
		
		System.out.print("Does module contain a test? (y/n): ");
		String answer = sc.nextLine();
		while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			System.out.print("Please enter \"y\" or \"n\": ");
			answer = sc.nextLine();
		}
		if (answer.equalsIgnoreCase("y")) {
			Test test = getTest();
			module = new Module(name, test);
			
		} else {
			module = new Module(name);
		}
		
		return module;
	}

	private Test getTest() {
		Test test = new Test();
		System.out.print("Enter the name of the test: ");
		test.setName(sc.nextLine());
		
		System.out.print("Should it be hidden? (y/n): ");
		String answer = sc.nextLine();
		while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			System.out.print("Please enter \"y\" or \"n\": ");
			answer = sc.nextLine();
		}
		if (answer.equalsIgnoreCase("y")) {
			test.setHidden(true);
		} else {
			test.setHidden(false);
		}
		
		return test;
	}
	
	

}
