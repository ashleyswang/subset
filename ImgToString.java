import java.io.*;

public class ImgToString{

    protected String filePathway = new String();
    protected String unformattedCode = new String();
    // private String formattedCode = new String();
    protected TextDetection textdetectionAPI = new TextDetection();
    protected String outputType = new String();

    // Constructor
    public ImgToString(){};
    public ImgToString(String filePathway){
	this.filePathway = filePathway;
    }

    // Getters
    public String getFilePathway(){ return filePathway; }
    public String getUnformatted(){ return unformattedCode; }
    // public String getOutputType(){ return outputType;}

    // Modifier
    public void setFilePathway(String s){ filePathway = new String(s);	}
    public void setOutputType(String s){ outputType = new String(s);}


    public void exportAs(){
	// outfile is the name of the file to be downloaded from user
	
	PrintWriter outfile = null;
	// unformattedCode = textdetectionAPI.detectText(filePathway);
	
	try{
	    outfile = new PrintWriter("outfile.txt");
	    unformattedCode = textdetectionAPI.detectText(filePathway);
	    for(char ch: unformattedCode.toCharArray()){
		outfile.append(ch);
	    }
	} catch (FileNotFoundException e){
	    System.out.println(e.toString());
	} catch (Exception e){
	    System.out.println("fuck this shit im so done");
	} finally {
	    if (outfile != null){
		outfile.close();
	    }
	}
    }
    
}
    