package courses.model;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Contains list of courses.
 * 
 * @author St. Roman
 */

@XStreamAlias("Academy")
public class Academy {
	private String name;
	private List<Course> courses = new ArrayList<Course>();
	
	//constructors
	
	public Academy(){};
	
	public Academy(String name) {
		setName(name);
	}
	
	public Academy(String name, List<Course> courses) {
		this(name);
		setCourses(courses);
	}

	//getters&setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	//other methods
	/**
	 * Add course to the list of courses.
	 * 
	 * @param course course to be added
	 */
	public void addCourse(Course course) {
		courses.add(course);
	}
	
	/**
	 * Returns list of courses, which starts between two dates
	 * 
	 * @param beginningDate beginning date
	 * @param endDate end date
	 * @return list of courses, which starts between beginning date and end date
	 */
	public List<Course> renameThisMethodWithSomeProperName(Calendar beginningDate, Calendar endDate) {  
		//getCoursesWhichStartsBetween() ??????
		
		List<Course> list = new ArrayList<Course>();
		for (Course c : courses){
			if (c.getStartDate().after(beginningDate) 
					&& c.getStartDate().before(endDate)) {
				list.add(c);
			}
		}
		
		return list;
	}

	@Override
	public String toString() {
		return "Academy name: " + name + courses;
	}
}
