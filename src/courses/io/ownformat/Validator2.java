package courses.io.ownformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator2 {
	public static final String NAME_PATTERN = "^[\\DA-Z_.].*";
	public static final String DATE_PATTERN = "(\\d{1,2}\\.)(\\d{1,2}\\.)(\\d{4})";
	public static final String TEST_PATTERN = "(.+):(\\w{4,5})";
	public static final String MODULE_PATTERN = "(.+):\\[(.+:\\w{4,5})?]";
	public static final String COURSE_PATTERN = "(.+):(\\d{1,2}\\.\\d{1,2}\\.\\d{4}):"
												+ "(\\d{1,2}\\.\\d{1,2}\\.\\d{4}):\\[(.*)\\]";
	
	
	public Validator2() { };
	
	public boolean validateCourse(String course) throws InvalidDataException {
		if (course == null) {
			return true;
		}
		String regexp = COURSE_PATTERN;
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(course);
		if (!matcher.matches()) {
			throw new InvalidDataException("Invalid course");
		}
		validateName(matcher.group(1));
		validateDate(matcher.group(2));
		validateDate(matcher.group(3));
		
		return true;
	}
	
	public boolean validateModule(String module) throws InvalidDataException {
		if (module == null) {		   
			return true;
		}
		String regexp = MODULE_PATTERN;
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(module);
		if (!matcher.matches()) {
			throw new InvalidDataException("Invalid module");
		}
		validateName(matcher.group(1));
		validateTest(matcher.group(2));
		return true;
	}
	
	public boolean validateTest(String test) throws InvalidDataException {
		if (test == null) {		   
			return true;
		}
		String regexp = TEST_PATTERN;
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(test);
		if (!matcher.matches()) {
			throw new InvalidDataException("Invalid test");
		}
		validateName(matcher.group(1));
		validateBoolean(matcher.group(2));
		return true;
	}
	
	public boolean validateDate(String date) throws InvalidDataException {
		String regexp = DATE_PATTERN;
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(date);
		if (!matcher.matches()) {
			throw new InvalidDataException("Date should be in 'DD.MM.YYYY' format");
		}
		
		String dateFormat = "dd.MM.yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(date); //throws ParseException if date is invalid
		} catch (ParseException e) {
			//e.printStackTrace();
			throw new InvalidDataException("Invalid date");
		}
		
		return matcher.matches();
	}
	
	public boolean validateName(String name) throws InvalidDataException {
		String regexp = NAME_PATTERN;
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			throw new InvalidDataException("Name should begin with "
										+ "a Capital letter or ['_', '.'] symbols");
		}
		return matcher.matches();
	}
	
	public boolean validateBoolean(String bool) throws InvalidDataException {
		if (bool.equals("true") || bool.equals("false")) {
			return true;
		}
		throw new InvalidDataException("Boolean value should be 'true' or 'false'");
	}

}
