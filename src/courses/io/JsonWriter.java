package courses.io;

import com.fasterxml.jackson.databind.ObjectMapper;	

import courses.model.Academy;

import java.io.*;

public class JsonWriter extends AbstractFileWriter {
	public JsonWriter(File file){
		super(file);
	}
	
	public void write(Academy ac) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(file, ac);
		System.out.println("json created!");
	}
}
