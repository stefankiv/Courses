package courses.io.ownformat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import courses.model.*;

/**
 * Used for converting data into our own format.
 * 
 * @author St. Roman
 */
public class Marshaller {
	private Validator validator = new Validator();
	
	public Marshaller() { };
	
	//Overloaded method marshall(arg) can get any of BasicNamedEntity subclasses as argument
	public String marshall(Test test) {
		return test.getName() + Separators.TEST_SEPARATOR + test.isHidden();
	}
	
	public String marshall(Module module) {
		if (module.getTest() == null) {
			return module.getName() + Separators.MODULE_SEPARATOR;
		}
		return module.getName() + Separators.MODULE_SEPARATOR
				+ marshall(module.getTest());
	}
	
	public String marshall(Course course) {
		return course.getName() + Separators.COURSE_SEPARATOR
				+ course.getPrettyStartDate() + Separators.COURSE_SEPARATOR
				+ course.getPrettyEndDate() + Separators.COURSE_SEPARATOR
				+ Separators.LIST_SEPARATOR
				+ marshallList(course.getModules(), Separators.COURSE_SEPARATOR);
	}
	
	public String marshall(Academy academy) {
		return academy.getName() + Separators.ACADEMY_SEPARATOR
				+ Separators.LIST_SEPARATOR
				+ marshallList(academy.getCourses(), Separators.ACADEMY_SEPARATOR);
	}
	
	/**
	 * Expected format of string "StringName:booleanHidden".
	 * Returns null if test doesn't exist.
	 * @param test
	 * @return Test or null, depends on incoming string
	 */
	public Test unmarshallTest(String test) {
		if (test == null) {
			return null;
		}
		Test result = new Test();
		String[] parts = test.split(Separators.TEST_SEPARATOR);
		
		if (parts.length == 2 && validator.validateTest(parts[0], parts[1])) {
				result.setName(parts[0]);
				result.setHidden(parts[1].equals("true") ? true : false);
		} 
		
		return result;
	}
	
	/**
	 * Expected format of string "StringName,StringTest".
	 * Returns Module(name) if test doesn't exist.
	 * @param module
	 * @return Module(name) or Module(name, test), depends on incoming string
	 */
	public Module unmarshallModule(String module) { //TODO rename arg, add comments
		Module result = new Module();
		String[] parts = module.split(Separators.MODULE_SEPARATOR);
		if (validator.validateName(parts[0])) {
			result.setName(parts[0]);
		}
		if (parts.length == 1) {
			return result;
		}
		result.setTest(unmarshallTest(parts[1]));
		return result;
	}
	
	/**
	 * Expected format of string "StringName;StringStartDate;StringEndDate;~StringModules".
	 * @param course
	 * @return Course
	 */
	public Course unmarshallCourse(String course) {
		Course result = new Course();
		
		String[] parts = course.split(Separators.COURSE_SEPARATOR + Separators.LIST_SEPARATOR);
		String[] fields = parts[0].split(Separators.COURSE_SEPARATOR);
		String[] modules = parts[1].split(Separators.COURSE_SEPARATOR);
		
		if (validator.validateCourse(fields[0], fields[1], fields[2])) {
			result.setName(fields[0]);
			result.setStartDate(unmarshallDate(fields[1]));
			result.setEndDate(unmarshallDate(fields[2]));
		}
		
		for (String module : modules) {
			result.addModule(unmarshallModule(module));
		}
		return result;
	}
	
	public Academy unmarshallAcademy(String academy) {
		Academy result = new Academy();
		String[] parts = academy.split(Separators.ACADEMY_SEPARATOR + Separators.LIST_SEPARATOR);
		
		if (validator.validateName(parts[0])) {
			result.setName(parts[0]);
		}
		
		String[] courses = parts[1].split(Separators.ACADEMY_SEPARATOR);
		
		for (String course : courses) {
			result.addCourse(unmarshallCourse(course));
		}
		
		return result;
	}
	
	/**
	 * Creates String representation of list
	 * 
	 * @param list list to be marshaled
	 * @param separator appropriate separator
	 * @return marshaled list
	 */
	private <T> String marshallList(List<T> list, String separator) {
		String result = "";
		
		for (T item : list) {
			if (item instanceof Course) {
				result += marshall((Course) item);
			} else if (item instanceof Module) {
				result += marshall((Module) item);
			}
			
			result += separator;
		}
		return result;
	}
	
	/**
	 * Converts string like "DD.MM.YYYY" into GregorianCalendar
	 * @param date string to be converted
	 * @return appropriate GregorianCalendar
	 */
	private Calendar unmarshallDate(String date) {
		String[] dates = date.split(Separators.DATE_SEPARATOR);
		int year = Integer.parseInt(dates[2]);
		int month = Integer.parseInt(dates[1]) - 1;
		int day = Integer.parseInt(dates[0]);
		
		return new GregorianCalendar(year, month, day);
	}
	
}
