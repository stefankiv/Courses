package courses.io;

import java.io.IOException;
import courses.model.Academy;

public abstract class AbstractReader {
	public abstract Academy read() throws IOException;
}
