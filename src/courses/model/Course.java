package courses.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Core, consists of modules.
 * 
 * Contains name, start/end dates and list of modules.
 * 
 */

@XStreamAlias("Course")
public class Course extends BasicNamedEntity {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss z")
	private Calendar startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss z")
	private Calendar endDate;
	private Set<Module> modules = new HashSet<Module>();
	
	//constructors
	
	public Course() { };
	
	public Course(String name, Calendar startDate, Calendar endDate) {
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
	}
	
	//getters & setters
	
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
	
	public Set<Module> getModules() {
		return modules;
	}
	
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
	@JsonIgnore
	public String getPrettyStartDate() {
		return formatDate(startDate);
	}
	
	@JsonIgnore
	public String getPrettyEndDate() {
		return formatDate(endDate);
	}
	
	//other methods
	
	/**
	 * Add module to the set of modules.
	 * 
	 * @param module module to be added
	 */
	public void addModule(Module module) {
		this.modules.add(module);
	}
	
	/**
	 * Returns string representation of date in format DD.MM.YYYY.
	 * 
	 * @param c Calendar instance to be converted
	 * @return String representation of date
	 */
	private String formatDate(Calendar c) {
		return c.get(Calendar.DAY_OF_MONTH) + "." 
				+ (c.get(Calendar.MONTH) + 1) + "." 
				+ c.get(Calendar.YEAR);
	}
	
	@Override
	public String toString() {
		return "Course name: " + name
				+ ", start date: " + formatDate(startDate)
				+ ", end date: " + formatDate(endDate)
				+ modules;
	}
	
	
	
}
