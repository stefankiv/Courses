package courses.io;

import java.io.IOException;
import courses.model.Academy;

public abstract class AbstractWriter {
	public abstract void write(Academy ac) throws IOException;
}
