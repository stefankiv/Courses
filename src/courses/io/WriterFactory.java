package courses.io;

import java.io.File;

public class WriterFactory {
	public static AbstractWriter getWriter(Object obj){
		AbstractWriter writer = null;
		String name = ((File) obj).getName();
		String extension = name.substring(name.lastIndexOf('.') + 1);
		if (extension.equals("json")) {
			writer = new JsonWriter((File) obj);
		} else if (extension.equals("xml")) {
			writer = new XmlWriter((File) obj);
		}
		return writer;
	}
}
