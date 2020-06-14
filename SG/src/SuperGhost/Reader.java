package SuperGhost;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

public class Reader{
	
	private static void readLine(Consumer<String> lineConsumer, String path) throws IOException {
		FileReader fileReader = new FileReader(path);
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			lineConsumer.accept(line);
		}
		
		fileReader.close();
		
		bufferedReader.close();
	}
	
	public static <T extends Collection<String>> T getAllLines(String path, T conduit) throws IOException {		
		readLine(l -> conduit.add(l), path);
		
		return conduit;
	}
}
