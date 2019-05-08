import java.io.*;

public class ImgToString{

    protected String filePathway = new String();
    protected String unformattedCode = new String();
    // private String formattedCode = new String();
    protected String outputType = "";
    private TextDetection textdetectionAPI = new TextDetection();

    // Constructor
    public ImgToString(){};
    public ImgToString(String filePathway){
	this.filePathway = filePathway;
    }

    // Getters
    public String getFilePathway(){ return filePathway; }
    public String getUnformatted(){ return unformattedCode; }
    public String getOutputType(){ return outputType;}

    // Modifier
    public void setFilePathway(String s){ filePathway = new String(s);	}

    public void exportAs() throws IOException {
		// outfile is the name of the file to be downloaded from user
		
		PrintWriter outfile = null;
		unformattedCode = textdetectionAPI.detectText(filePathway);

		try{
			outfile = new PrintWriter("outfile.txt");
	    	for(char ch: unformattedCode.toCharArray()){
				outfile.append(ch);
	    	}
	    } catch (FileNotFoundException e){
			System.out.println(e.toString());
		} finally {
	    	if (outfile != null){
				outfile.close();
	    	}
		}
    }
    
}
