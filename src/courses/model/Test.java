package courses.model;

/**
 * A part of module.
 *
 * May be hidden or visible.
 */
public class Test {
	private String name;
	private boolean hidden;
	
	//constructors
	
	public Test(){};
	
	public Test(String name, boolean hidden){
		setName(name);
		setHidden(hidden);
	}

	//getters & setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	
	@Override
	public String toString() {
		return "Test name: " + name + (hidden ? " is hidden. " : " is visible. ");
	}

}
