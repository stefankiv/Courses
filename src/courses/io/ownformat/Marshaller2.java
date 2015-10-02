package courses.io.ownformat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import courses.model.Academy;
import courses.model.BasicNamedEntity;
import courses.model.Course;
import courses.model.Module;
import courses.model.Test;

public class Marshaller2 {
	public static final String FIELD_SEPARATOR = ":";
	public static final String SET_SEPARATOR = "|";	
	public static final String DATE_SEPARATOR = "\\.";
	public static final String OPEN_BRACE = "[";
	public static final String CLOSE_BRACE = "]";
	public static final String REG_EXP_FOR_COUNTER = ":\\[";
	
	
	private Validator2 validator = new Validator2();
	
	public Marshaller2() { };
	
	public String marshall(Test test) {
		return test.getName() + FIELD_SEPARATOR + test.isHidden();
	}
	
	public String marshall(Module module) {
		if (module.getTest() == null) {
			return module.getName() + FIELD_SEPARATOR + OPEN_BRACE + CLOSE_BRACE;
		}
		return module.getName() + FIELD_SEPARATOR + OPEN_BRACE
				+ marshall(module.getTest()) + CLOSE_BRACE;
	}
	
	public String marshall(Course course) {
		return course.getName() + FIELD_SEPARATOR
				+ course.getPrettyStartDate() + FIELD_SEPARATOR
				+ course.getPrettyEndDate() + FIELD_SEPARATOR
				+ OPEN_BRACE
				+ marshallSet(course.getModules())
				+ CLOSE_BRACE;
	}
	
	public String marshall(Academy academy) {
		return academy.getName() + FIELD_SEPARATOR
				+ OPEN_BRACE
				+ marshallSet(academy.getCourses())
				+ CLOSE_BRACE;
	}
	
	
	public Test unmarshallTest(String test) throws InvalidDataException {
		if (test == null || test.equals("")) {
			return null;
		}
		validator.validateTest(test);
		Test result = new Test();
		//inc: name:hidden
		//      0     1
		result.setName(test.split(FIELD_SEPARATOR)[0]);
		result.setHidden(test.split(FIELD_SEPARATOR)[1].equals("true") ? true : false);
		return result;
	}
	
	public Module unmarshallModule(String module) throws InvalidDataException {
		if (module == null) {
			return null;
		}
		validator.validateModule(module);
		Module result = new Module();
		//inc: mouleName:[test]
		//        0        1
		result.setName(module.split(":", 2)[0]);
		result.setTest(unmarshallTest(removeBraces(module.split(FIELD_SEPARATOR, 2)[1])));
		return result;
	}
	
	public Course unmarshallCourse(String course) throws InvalidDataException {
		if (course == null) {
			return null;
		}
		validator.validateCourse(course);
		Course result = new Course();
		//inc: courseName:startDate:endDate[modules]
		//        0 	      1        2       3
		String[] courseParts = course.split(FIELD_SEPARATOR, 4);
		result.setName(courseParts[0]);
		result.setStartDate(unmarshallDate(courseParts[1]));
		result.setEndDate(unmarshallDate(courseParts[2]));
		
		System.out.println(removeBraces(courseParts[3]));
		for (String module : courseParts[3].split(SET_SEPARATOR)) {
			result.addModule(unmarshallModule(module));
		}
		
		
		return null;
	}
	
	private Calendar unmarshallDate(String date) {
		String[] dates = date.split(DATE_SEPARATOR);
		int year = Integer.parseInt(dates[2]);
		int month = Integer.parseInt(dates[1]) - 1;
		int day = Integer.parseInt(dates[0]);
		
		return new GregorianCalendar(year, month, day);
	}

	private <T extends BasicNamedEntity> String marshallSet(Set<T> set) {
		String result = "";
		Iterator<T> iterator = set.iterator();
		
		while (iterator.hasNext()) {
			Object item = iterator.next();
			if (item instanceof Course) {
				result += marshall((Course) item);
			} else if (item instanceof Module) {
				result += marshall((Module) item);
			}
			if (iterator.hasNext()) result += SET_SEPARATOR;
		}
		
		return result;
	}
	
	private String removeBraces(String string) {
		final String REGEXP = "^\\[|\\]$";
		return string.replaceAll(REGEXP, "");
	}
	
}
