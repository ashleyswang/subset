import java.io.*;

public class ImgToFormattedString extends ImgToString{

    private String formattedCode = new String();
    private Format formatting = new Format();
    private String outputType = new String();

    // Constructor
    public ImgToFormattedString(){};
    public ImgToFormattedString(String filePathway){
        this.filePathway = filePathway;
    }

    // Getter
    public String getOutputType(){ return outputType; }
    
    // Setter
    public void setOutputType(String s){
        if (s.equals(".java") || s.equals(".cpp"))
            outputType = s;
        else
            System.out.println("Not .java or .cpp file. Use ImgToString class.");
            return;
    }

    public void exportAs(){
        // outfile is the name of the file to be downloaded from user
        
        PrintWriter outfile = null;
        // unformattedCode = textdetectionAPI.detectText(filePathway);
        // formattedCode = formatting.formatText(unformattedCode);
        try{
            unformattedCode = textdetectionAPI.detectText(filePathway);
            formattedCode = formatting.formatText(unformattedCode);

            if (outputType.equals(".java")){
                outfile = new PrintWriter("outfile.java");
            } else if (outputType.equals(".cpp")){
                outfile = new PrintWriter("outfile.cpp");
            }
	    System.out.println(formattedCode);
            for(char ch: unformattedCode.toCharArray()){
                outfile.append(ch);
            }
        } catch (FileNotFoundException e){
            System.out.println(e.toString());
        } catch (Exception e){
            System.out.println(" java is still being a bitch ");
        } finally {
            if (outfile != null){
                outfile.close();
            }
        }
    }
    
}
