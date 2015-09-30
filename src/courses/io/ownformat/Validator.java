package courses.io.ownformat;

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
	
	public boolean validateName(String name) {
		//some conditions here
		if (name instanceof String) { 
			return true;
		}
		return false;
	}
	
	public boolean validateBoolean(String bool) {
		if (bool.equals("true") || bool.equals("false")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Expected string "DD.MM.YYYY"
	 * @param date
	 * @return
	 */
	public boolean validateDate(String date) {
		
		String[] dates = date.split(Separators.DATE_SEPARATOR);
		if (dates.length != 3) return false;
		
		String day = Integer.toString(Integer.parseInt(dates[0]));
		if (!day.equals(dates[0])) return false;
		
		String month = Integer.toString(Integer.parseInt(dates[1]));
		if (!month.equals(dates[1])) return false;
		
		String year = Integer.toString(Integer.parseInt(dates[2]));
		if (!year.equals(dates[2])) return false;
		
		return true;
	}
	
}
