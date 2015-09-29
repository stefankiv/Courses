package courses.io.marshaller;

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
	private static final String TEST_SEPARATOR = ":";
	private static final String MODULE_SEPARATOR = ",";
	private static final String COURSE_SEPARATOR = ";";
	private static final String LIST_SEPARATOR = "~";
	private static final String ACADEMY_SEPARATOR = "/";
	
	public Marshaller() { };
	
	//Overloaded method marshall(arg) can get any of courses.model classes as argument
	public String marshall(Test test) {
		return test.getName() + TEST_SEPARATOR + test.isHidden();
	}
	
	public String marshall(Module module) {
		if (module.getTest() == null) {
			return module.getName() + MODULE_SEPARATOR;
		}
		return module.getName() + MODULE_SEPARATOR
				+ marshall(module.getTest());
	}
	
	public String marshall(Course course) {
		return course.getName() + COURSE_SEPARATOR
				+ course.getPrettyStartDate() + COURSE_SEPARATOR
				+ course.getPrettyEndDate() + COURSE_SEPARATOR
				+ LIST_SEPARATOR + marshallList(course.getModules(), COURSE_SEPARATOR);
	}
	
	public String marshall(Academy academy) {
		return academy.getName() + ACADEMY_SEPARATOR
				+ LIST_SEPARATOR + marshallList(academy.getCourses(), ACADEMY_SEPARATOR);
	}
	
	public Test unmarshallTest(String s) {
		Test result = new Test();
		String[] parts = s.split(TEST_SEPARATOR);
		
		if (parts.length == 2) {
			result.setName(parts[0]);
			
			switch (parts[1]) {
			case "true": 
				result.setHidden(true);
				break;
			case "false":
				result.setHidden(false);
				break;
			default:
				return null;
			}
			
		} else {
			return null;
		}
		
		return result;
	}
	
	public Module unmarshallModule(String s) {
		Module result = new Module();
		String[] parts = s.split(MODULE_SEPARATOR);
		result.setName(parts[0]);
		if (parts.length == 1) {
			return result;
		}
		result.setTest(unmarshallTest(parts[1]));
		return result;
	}
	
	public Course unmarshallCourse(String s) {
		Course result = new Course();
		String[] parts = s.split(COURSE_SEPARATOR + LIST_SEPARATOR);
		String[] fields = parts[0].split(COURSE_SEPARATOR);
		String[] modules = parts[1].split(COURSE_SEPARATOR);
		result.setName(fields[0]);
		result.setStartDate(unmarshallDate(fields[1]));
		result.setEndDate(unmarshallDate(fields[2]));
		
		for (String module : modules) {
			result.addModule(unmarshallModule(module));
		}
		return result;
	}
	
	public Academy unmarshallAcademy(String s) {
		Academy result = new Academy();
		String[] parts = s.split(ACADEMY_SEPARATOR + LIST_SEPARATOR);
		
		result.setName(parts[0]);
		String[] courses = parts[1].split(ACADEMY_SEPARATOR);
		
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
	 * @param s string to be converted
	 * @return appropriate GregorianCalendar
	 */
	private Calendar unmarshallDate(String s) {
		final String DATE_SEPARATOR = "\\.";
		String[] date = s.split(DATE_SEPARATOR);
		int year, month, day;
		year = Integer.parseInt(date[2]);
		month = Integer.parseInt(date[1]);
		day = Integer.parseInt(date[0]);
		
		return new GregorianCalendar(year, month, day);
	}
	
}
