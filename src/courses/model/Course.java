package courses.model;

import java.util.*;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Course")
public class Course {
	private String name;
	private Calendar startDate;
	private Calendar endDate;
	private List<Module> modules = new ArrayList<Module>();
	
	public Course(){};
	
	public Course(String name, Calendar startDate, Calendar endDate) {
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	
	public List<Module> getModules(){
		return modules;
	}
	
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	public void addModule(Module module) {
		this.modules.add(module);
	}
	
	public String toString() {
		return "Course name: " + name + 
				", start date: " + formatDate(startDate) +
				", end date: " + formatDate(endDate) +
				modules;
	}
	
	private String formatDate(Calendar c) {
		return c.get(Calendar.DAY_OF_MONTH) + "." +
				(c.get(Calendar.MONTH) + 1) + "." +
				c.get(Calendar.YEAR);
	}
	
}
