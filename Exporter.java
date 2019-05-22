// Exporter.java
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Exporter{
	public static void exportAs(String input){
		// outfile is the name of the file to be downloaded from user
	
		PrintWriter outfile = null;
		// unformattedCode = textdetectionAPI.detectText(filePathway);
	
		try{
		    outfile = new PrintWriter("Hello.java");
		    for(char ch: input.toCharArray()){
				outfile.append(ch);
		    }
		} catch (FileNotFoundException e){
		    System.out.println(e.toString());
		} catch (Exception e){
		    System.out.println(e.toString());
		} finally {
		    if (outfile != null){
				outfile.close();
		    }
		}
    }
}