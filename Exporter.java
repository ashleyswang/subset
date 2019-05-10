// Exporter.java

public class Exporter{
	public void exportAs(String input){
		// outfile is the name of the file to be downloaded from user
	
		PrintWriter outfile = null;
		// unformattedCode = textdetectionAPI.detectText(filePathway);
	
		try{
		    outfile = new PrintWriter("outfile.txt");
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