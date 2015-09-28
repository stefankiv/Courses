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

	@Override
	public String toString() {
		return "Academy name: " + name + courses;
	}
}
