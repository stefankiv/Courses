package courses.test;

import courses.io.*;	
import courses.model.*;
import java.util.*;
import java.io.*;

public class TestClass {

	public static void main(String[] args) {
		
		Academy ac = getFixedAcademy();
		
		//Academy ac = getAcademyFromConsole();
		
		System.out.println(ac);
		
		String path = "resources/out.json";
		//String path = "resources/out.xml";
		
		File file = new File(path);
		
		AbstractWriter writer = WriterFactory.getWriter(file);
		try {
			writer.write(ac);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			Academy ac2 = ReaderFactory.getReader(file).read();
			System.out.println(ac2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Academy getFixedAcademy() {
		Academy ac = new Academy("Chernivtsi IT Academy");
		
		Course tempCourse = new Course();
		tempCourse.setStartDate(new GregorianCalendar(2015, 11, 25));
		tempCourse.setEndDate(new GregorianCalendar(2015, 6, 24));
		tempCourse.setName("Java course");
		tempCourse.addModule(new Module("Module 1"));
		tempCourse.addModule(new Module("Module 2"));
		tempCourse.addModule(new Module("Module 3"));
		tempCourse.addModule(new Module("TestModule 4", new Test("Final test", true)));
		ac.addCourse(tempCourse);
		
		tempCourse = new Course();
		tempCourse.setStartDate(new GregorianCalendar(2015, 5, 15));
		tempCourse.setEndDate(new GregorianCalendar(2015, 8, 19));
		tempCourse.setName(".NET course");
		tempCourse.addModule(new Module("Module 1"));
		tempCourse.addModule(new Module("Module 2"));
		ac.addCourse(tempCourse);
		
		return ac;	
	}
	
	public static Academy getAcademyFromConsole() {
		Academy ac = null;
		try {
			ac = ReaderFactory.getReader(System.in).read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ac;
	}
}
