package courses.model;

import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Contains list of courses.
 * 
 * @author St. Roman
 */

@XStreamAlias("Academy")
public class Academy extends BasicNamedEntity {
	//TODO generate equals() and hashcode()
	private Set<Course> courses = new HashSet<Course>();
	
	//constructors
	
	public Academy() { };
	
	public Academy(String name) {
		setName(name);
	}
	
	public Academy(String name, Set<Course> courses) {
		this(name);
		setCourses(courses);
	}

	//getters&setters
	
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
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
	
	public Course getCourseByName(String name) {
		for (Course course : courses) {
			if (course.getName().equals(name)) {
				return course;
			}
		}
		return null;
	}
	
	/**
	 * Returns list of courses, which starts between two dates
	 * 
	 * @param beginningDate beginning date
	 * @param endDate end date
	 * @return list of courses, which starts between beginning date and end date
	 */
	public List<Course> getCoursesWhichStartsBetweenTwoDates(Calendar beginningDate, Calendar endDate) {  
		List<Course> list = new ArrayList<Course>();
		for (Course c : courses) {
			if (c.getStartDate().after(beginningDate) 
					&& c.getStartDate().before(endDate)) {
				list.add(c);
			}
		}
		
		return list;
	}
	
	/**
	 * Returns list of courses, which starts in a week
	 * 
	 * @return list of courses, which starts in a week
	 */
	public List<Course> getCoursesWhichStartsInAWeek() {
		List<Course> list = new ArrayList<Course>();
		Calendar deadline = new GregorianCalendar(); //current date
		deadline.add(Calendar.DAY_OF_YEAR, 7); //+7 days
		for (Course course : courses) {
			if (course.getStartDate().before(deadline)) {
				list.add(course);
			}
		}
		return list;
	}

	@Override
	public String toString() {
		return "Academy name: " + name + courses;
	}
}
