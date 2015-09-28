package courses.io;

import java.io.File;
import java.io.InputStream;

public class ReaderFactory {
	public static AbstractReader getReader(Object obj){
		AbstractReader reader = null;
		if (obj instanceof File){
			String name = ((File) obj).getName();
			String extension = name.substring(name.lastIndexOf('.') + 1);
			
			if (extension.equals("json")) {
				reader = new JsonReader((File) obj);
			} else if (extension.equals("xml")) {
				reader = new XmlReader((File) obj);
			}
		} else if (obj instanceof InputStream){
			reader = new ConsoleReader();
		}
		
		return reader;
	}
}
