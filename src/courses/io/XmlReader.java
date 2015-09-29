package courses.io;

import java.io.File;	
import java.io.IOException;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import courses.model.*;

public class XmlReader extends AbstractFileReader {
	public XmlReader(File file) {
		super(file);
	}

	public Academy read() throws IOException {
		XStream xs = new XStream(new StaxDriver());
		xs.processAnnotations(Academy.class);
		return (Academy) xs.fromXML(file);
	}
}
