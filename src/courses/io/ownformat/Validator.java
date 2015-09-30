package courses.io.ownformat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used for validation string data.
 * @author Роман
 *
 */
public class Validator {
	
	public Validator() { };
	
	public boolean validateTest(String name, String hidden) {
		if (!validateName(name)) return false;
		if (!validateBoolean(hidden)) return false;
		return true;
	}
	
	public boolean validateCourse(String name, String startDate, String endDate) {
		if (!validateName(name)) return false;
		if (!validateDate(startDate)) return false;
		if (!validateDate(endDate)) return false;
		
		return true;
	}
	
	/**
	 * Name should begin with a Capital letter or any symbol, excluding whitespaces
	 * 
	 * @param name
	 * @return true if name is correct, false if name starts with digit or whitespace
	 */
	public boolean validateName(String name) {
		String regexp = "^[^\\d\\sa-z].*";
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(name);
		return m.matches();
	}
	
	public boolean validateBoolean(String bool) {
		if (bool.equals("true") || bool.equals("false")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param date string "DD.MM.YYYY" 
	 * @return
	 */
	public boolean validateDate(String date) {
		//TODO: add checking real dates (99.99.9999 shouldn't return true)
		String regexp = "(\\d{1,2}\\.)(\\d{1,2}\\.)(\\d{4})";
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(date);
		return m.matches();
	}
	
}
