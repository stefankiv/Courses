package courses.model;

public class Test {
	private String name;
	private boolean hidden;
	
	public Test(){};
	
	public Test(String name, boolean hidden){
		setName(name);
		setHidden(hidden);
	}

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
	
	public String toString() {
		return "Test name: " + name + (hidden ? " is hidden. " : " is visible. ");
	}

}
