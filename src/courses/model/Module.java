package courses.model;
	
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Module")
public class Module {
	private String name;
	private Test test;
	
	public Module(){};
	
	public Module(String name) {
		setName(name);
	}
	
	public Module(String name, Test test) {
		setName(name);
		setTest(test);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	public String toString() {
		return "Module name: " + name + ", " + 
				(test == null ? "no test" : test);
	}
	
}
