package courses.io;

import java.io.File;	
import java.io.IOException;

import courses.model.Academy;

public abstract class AbstractFileWriter extends AbstractWriter {
	protected File file;
		
	public AbstractFileWriter(File file) {
		this.file = file;
	}
	
	public abstract void write(Academy ac) throws IOException;
}
