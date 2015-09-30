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
		System.out.print("Enter the name of the Academy: "); //TODO crash when more than 1 word entered
		academy.setName(sc.next());
		
		System.out.print("Enter a number of courses: ");
		academy.setCourses(getListOfCourses(sc.nextInt()));
		
		return academy;
	}
	
	private List<Course> getListOfCourses(int count) {
		List<Course> list = new ArrayList<Course>();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				list.add(getCourse());
			}
		}
		
		return list;
	}

	private Course getCourse() {
		Course course = new Course();
				
		System.out.print("Enter the name of the course: ");
		course.setName(sc.next());
		
		System.out.println("Enter the start date: ");
		course.setStartDate(getDate());
		
		System.out.println("Enter the end date: ");
		course.setEndDate(getDate());
		
		System.out.print("Enter a number of modules: ");
		course.setModules(getListOfModules(sc.nextInt()));
		
		return course;
	}
	
	private List<Module> getListOfModules(int count) {
		List<Module> list = new ArrayList<Module>();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				list.add(getModule());
			}
		}
		
		return list;
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
		return new GregorianCalendar(year, month, day);
	}

	private Module getModule() {
		Module module = null;
		
		System.out.print("Enter the name of the module: ");
		String name = sc.next();
		
		System.out.print("Does module contain a test? (y/n): ");
		String answer = sc.next();
		while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			System.out.print("Please enter \"y\" or \"n\": ");
			answer = sc.next();
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
		test.setName(sc.next());
		
		System.out.print("Should it be hidden? (y/n): ");
		String answer = sc.next();
		while (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n")) {
			System.out.print("Please enter \"y\" or \"n\": ");
			answer = sc.next();
		}
		if (answer.equalsIgnoreCase("y")) {
			test.setHidden(true);
		} else {
			test.setHidden(false);
		}
		
		return test;
	}
	
	

}
