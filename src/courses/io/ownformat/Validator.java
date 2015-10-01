package courses.io.ownformat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Used for validation string data.
 * @author Роман
 *
 */
public class Validator {
	
	public Validator() { };
	
	public boolean validateTest(String name, String hidden) throws InvalidDataException {
		if (!validateName(name)) return false;
		if (!validateBoolean(hidden)) return false;
		return true;
	}
	
	public boolean validateCourse(String name, String startDate, String endDate) 
															throws InvalidDataException {
		if (!validateName(name)) return false;
		if (!validateDate(startDate)) return false;
		if (!validateDate(endDate)) return false;
		
		return true;
	}
	
	/**
	 * Name should begin with a Capital letter or ['_', '.'] symbols
	 * 
	 * @param name
	 * @return true if name is correct, false if name starts with digit or whitespace
	 * @throws InvalidDataException 
	 */
	public boolean validateName(String name) throws InvalidDataException {
		String regexp = "^[\\DA-Z_.].*";
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(name);
		if (!m.matches()) {
			throw new InvalidDataException("Name should begin with "
										+ "a Capital letter or or ['_', '.'] symbols");
		}
		return m.matches();
	}
	
	public boolean validateBoolean(String bool) throws InvalidDataException {
		if (bool.equals("true") || bool.equals("false")) {
			return true;
		}
		throw new InvalidDataException("Boolean value should be 'true' or 'false'");
	}
	
	/**
	 * 
	 * @param date string "DD.MM.YYYY" 
	 * @return
	 * @throws InvalidDataException 
	 */
	public boolean validateDate(String date) throws InvalidDataException {
		String regexp = "(\\d{1,2}\\.)(\\d{1,2}\\.)(\\d{4})";
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(date);
		if (!m.matches()) {
			throw new InvalidDataException("Date should be in 'DD.MM.YYYY' format");
		}
		
		String dateFormat = "dd.MM.yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(date); //throws ParseException if dati is invalid
		} catch (ParseException e) {
			//e.printStackTrace();
			throw new InvalidDataException("Invalid date");
		}
		
		return m.matches();
	}
	
}
