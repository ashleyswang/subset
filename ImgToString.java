import java.util.PrintWriter;

public class ImgToString{

    private String filePathway = new String();
    private String unformattedCode = new String();
    // private String formattedCode = new String();
    private String outputType = "";
    private TextDetection textdetectionAPI = new TextDetection();

    // Constructor
    public ImgToString(){};
    public ImgToString(String filePathway){
	this.filePathway = filePathway;
    }

    // Getters
    public String getFilePathway(){ return filePathway; }
    public String getUnformatted(){ return unformattedCode; }
    public String getFormatted(){ return formattedCode; }
    public String getOutputType(){ return outputType;}

    // Modifier
    public void setFilePathway(String s){ filePathway = new String(s);	}

    public void exportAs(String outfile) throws IOException {
	// outfile is the name of the file to be downloaded from user

	FileOutputStream f = null;

	unformattedCode = textdetectionAPI.detectText(filePathway);

	try{
	    outfile = new PrintWriter("outfile.txt");

	    for(char ch: unformattedCode.toCharArray()){
			outfile.append(ch);
	    }
	} finally {
	    if (f != null){
		f.close();
	    }
	}
    }
    
}
