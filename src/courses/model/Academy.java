package courses.model;

import java.util.*;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Academy")
public class Academy {
	private String name;
	private List<Course> courses = new ArrayList<Course>();
	
	public Academy(){};
	
	public Academy(String name) {
		setName(name);
	}
	
	public Academy(String name, List<Course> courses) {
		this(name);
		setCourses(courses);
	}

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
	
	public void addCourse(Course course) {
		courses.add(course);
	}

	public String toString() {
		return "Academy name: " + name + courses;
	}
}
