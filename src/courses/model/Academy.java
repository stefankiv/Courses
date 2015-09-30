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
	
	/**
	 * Returns list of courses, which starts between two dates
	 * 
	 * @param beginningDate beginning date
	 * @param endDate end date
	 * @return list of courses, which starts between beginning date and end date
	 */
	public List<Course> renameThisMethodWithSomeProperName(Calendar beginningDate, Calendar endDate) {  
		//getCoursesWhichStartsBetween() ??????
		//varargs
		
		List<Course> list = new ArrayList<Course>();
		for (Course c : courses) {
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
