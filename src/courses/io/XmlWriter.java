package courses.io;

import courses.model.*;

import java.io.*;

import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class XmlWriter extends AbstractFileWriter {
	public XmlWriter(File file) {
		super(file);
	}

	public void write(Academy ac) throws IOException {
		XStream xs = new XStream(new StaxDriver());
		xs.processAnnotations(Academy.class);
		FileOutputStream fos = new FileOutputStream(file);		
		xs.marshal(ac, new PrettyPrintWriter(new OutputStreamWriter(fos)));
		System.out.println("xml created!");
	}
}
