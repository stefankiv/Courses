package courses.io;

import java.io.File;
import java.io.IOException;

import courses.model.Academy;

public abstract class AbstractFileReader extends AbstractReader {
	protected File file;
	
	public AbstractFileReader(File file) {
		this.file = file;
	}
	
	public abstract Academy read() throws IOException;
}
