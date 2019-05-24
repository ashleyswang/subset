import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Exportertest {
	public static void main(String[] args) {
		try {
			Scanner inFile = new Scanner(new File("HelloWorld.java"));
			String line;
			while (inFile.hasNextLine()) {
	    		line = inFile.nextLine();
	    		System.out.println(line);
			}

	// get all words in the file and print them one-by-one
	// assuming words are separated by the white space character(s)
			String word;
			while (inFile.hasNext()) {
	    		word = inFile.next();
	    		System.out.println(word);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
	}
	
}