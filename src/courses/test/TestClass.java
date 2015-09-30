package courses.test;

import java.io.*;
import java.util.*;

import courses.io.*;	
import courses.io.ownformat.*;
import courses.model.*;

public class TestClass {

	public static void main(String[] args) {
		
		Academy ac = getFixedAcademy();
		//Academy ac = getAcademyFromConsole();
		System.out.println(ac);
		
		//marshaller test
		//marshallerTest();
		Marshaller m = new Marshaller();
		String s = m.marshall(ac);
		System.out.println(s);
		Academy ac2 = m.unmarshallAcademy(s);
		System.out.println(ac2);
		
		//get list of courses which starts between two dates
		//getListOfCoursesWhichStartsBetweenTwoDates(ac);
		
		//input/output
		/*
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
		*/
		
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
	
	public static void getListOfCoursesWhichStartsBetweenTwoDates(Academy ac) {
		Calendar beginningDate = new GregorianCalendar(2015, 4, 15);
		Calendar endDate = new GregorianCalendar(2015, 6, 15);
		System.out.println(ac.renameThisMethodWithSomeProperName(beginningDate, endDate));
	}
	
	public static void marshallerTest() {
		Test test = new Test("qweTest", true);
		Module module = new Module("qweModule", test);
		Course course = new Course();
		course.setName("qweCourse");
		course.setStartDate(new GregorianCalendar(2015, 5, 25));
		course.setEndDate(new GregorianCalendar(2015, 7, 24));
		course.addModule(new Module("Module 1"));
		course.addModule(new Module("Module 2", test));
		
		Marshaller m = new Marshaller();
		String str;
		
		System.out.println(course);
		str = m.marshall(course);
		System.out.println(str);
		//Test temp = m.unmarshallTest(str);
		//Module temp = m.unmarshallModule(str);
		//Course temp = m.unmarshallCourse(str);
		//System.out.println(temp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
