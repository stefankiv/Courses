package courses.model;
	
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Basic part or the course.
 * 
 * Basic part of the course. May contain a test.
 * As default, test = null. If you want to add test,
 * use appropriate constructor or method setTest(Test test)
 */
@XStreamAlias("Module")
public class Module extends BasicNamedEntity {
	private Test test;
	
	//constructors
	public Module() { };
	
	public Module(String name) {
		setName(name);
	}
	
	public Module(String name, Test test) {
		setName(name);
		setTest(test);
	}

	//getters & setters
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	@Override
	public String toString() {
		return "Module name: " + name + ", " 
				+ (test == null ? "no test" : test);
	}
	
}
