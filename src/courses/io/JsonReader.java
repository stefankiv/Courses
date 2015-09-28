package courses.io;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;

import courses.model.*;

public class JsonReader extends AbstractFileReader {

	public JsonReader(File file) {
		super(file);
	}

	public Academy read() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setTimeZone(TimeZone.getDefault());
		return mapper.readValue(file, Academy.class);
	}

}
