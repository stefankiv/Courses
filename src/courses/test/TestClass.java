package courses.test;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import courses.io.*;	
import courses.io.ownformat.*;
import courses.model.*;

public class TestClass {

	public static void main(String[] args) {
		
		Academy ac = getFixedAcademy();
		//Academy ac = getAcademyFromConsole();
		System.out.println(ac);
		
		//courses, which starts in a week
		System.out.println(ac.getCoursesWhichStartsInAWeek());
		
		//visible tests
		System.out.println(ac.getCourseByName(".NET course").getAllUnhideTests());
		
		
		//marshaller test
		//marshaller2Test();
		
		/*
		Marshaller2 m = new Marshaller2();
		String s = m.marshall(ac);
		System.out.println(s);
		System.out.println(m.makePrettyString(s));
		
		Academy ac2;
		
		try {
			ac2 = m.unmarshallAcademy(s);
			System.out.println(ac2);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}*/
		
		
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
		tempCourse.setName("Java course");
		tempCourse.setStartDate(new GregorianCalendar(2015, 9, 8)); //8.10.2015
		tempCourse.setEndDate(new GregorianCalendar(2015, 11, 11));
		tempCourse.addModule(new Module("Module 1"));
		tempCourse.addModule(new Module("Module 2"));
		tempCourse.addModule(new Module("Module 3"));
		tempCourse.addModule(new Module("TestModule 4", new Test("Final test", true)));
		ac.addCourse(tempCourse);
		
		tempCourse = new Course();
		tempCourse.setStartDate(new GregorianCalendar(2015, 9, 13)); //13.10.2015
		tempCourse.setEndDate(new GregorianCalendar(2016, 1, 19));
		tempCourse.setName(".NET course");
		tempCourse.addModule(new Module("Module 1"));
		tempCourse.addModule(new Module("Module 2"));
		tempCourse.addModule(new Module("TestModule 3", new Test("Visible test 1", false))); //visible
		tempCourse.addModule(new Module("TestModule 4", new Test("Hidden test 2", true)));
		tempCourse.addModule(new Module("TestModule 5", new Test("Visible test 3", false))); //visible
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
		System.out.println(ac.getCoursesWhichStartsBetweenTwoDates(beginningDate, endDate));
	}
	
	public static void marshaller2Test() {
		Test test = new Test("qweTest", true);
		Module module = new Module("qweModule", test);
		Course course = new Course();
		course.setName("qweCourse");
		course.setStartDate(new GregorianCalendar(2015, 5, 25));
		course.setEndDate(new GregorianCalendar(2015, 7, 24));
		course.addModule(new Module("Module 1"));
		course.addModule(new Module("Module 2", test));
		
		Marshaller2 m = new Marshaller2();
		String str;
		
		System.out.println(course);
		str = m.marshall(course);
		System.out.println(str);
		Course temp;
		try {
			temp = m.unmarshallCourse(str);
			System.out.println(temp);
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Module temp = m.unmarshallModule(str);
		//Course temp = m.unmarshallCourse(str);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
