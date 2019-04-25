import java.util.Scanner;

public class ImgToString{

    private String filePathway = new String();
    private String unformattedCode = new String();
    private String formattedCode = new String();

    // Constructor
    public ImgToString(String filePathway){
	this.filePathway = filePathway;
	unformattedCode = "";
	formattedCode = "";
    }

    // Getters
    public String getFilePathway(){ return filePathway; }
    public String getUnformatted(){ return unformattedCode; }
    public String getFormatted(){ return formattedCode; }


    // Functions
    public void translate(String filePathway){ /* convert image to unformattedCode */ } 
    public void format(String t){
	//Scanner s = new Scanner(System.in);
	//System.out.print("Enter a one liner: ");
	//String t = s.nextLine();
	//t+= ' ';

	// t is now the entire string of unformatted code

	int tabCounter = 0;
	boolean comment = false;
	boolean[] forloop;
	forloop = new boolean[2];
	forloop[0] = false;
	forloop[1] = false;
	for(int j = 0; j <t.length(); j++){
		char ch = t.charAt(j);
		// for loops
		if(ch == 'f' && t.charAt(j+1) == 'o' && t.charAt(j+2) == 'r' && (t.charAt(j+3) == '('
		|| t.charAt(j+3) == ' ')){
			forloop[0] = true;
			forloop[1] = true;
			formattedCode+="f";
		}
		// one line comments
		else if(ch == '/' && t.charAt(j+1) == '/'){
		    comment = true;
		    formattedCode+="/";
		}
		else if(ch == '\\' && t.charAt(j+1) == 'n'){
		    if (comment){
			formattedCode+='\n';
			for(int i = 0; i < tabCounter; i++){
			    formattedCode+="\t";
			}
		    }
		    j++;
		}
		else if(ch == ' '){
		    if (!(t.charAt(j-1) == ';') && !(t.charAt(j-2) == '\\' && t.charAt(j-1) == 'n')){
			formattedCode+=" ";
		    }
		}
		// semi colons
		else if(ch == ';'){
			if(forloop[0] == true){
				formattedCode+=ch;
				forloop[0] = false;
			}
			else if(forloop[1] == true){
				formattedCode+=ch;
				forloop[1] = false;
			}
			else{
				formattedCode+=ch;
				formattedCode+="\n";
				if (t.charAt(j+1) != '}'){
				    for(int i = 0; i < tabCounter; i++){
					formattedCode+="\t";
				    }
				}
				else {
				    for(int i=0; i < tabCounter-1; i++){
					formattedCode+="\t";
				    }
				}
			}
		}
		else if(ch == '{'){
			tabCounter++;
			formattedCode+="{";
			formattedCode+="\n";
			for(int i = 0; i < tabCounter; i++){
				formattedCode+="\t";
			}
		}
		else if(ch == '}'){
		    /*
			if(t.charAt(j-1) != '}' && t.charAt(j-1) != ';' && t.charAt(j-1) != ' '){
			    System.out.println();
			}
		    */
			tabCounter--;
			if(t.charAt(j-1) == '}'){
				for(int i = 0; i < tabCounter; i++){
					formattedCode+="\t";
				}
			}
			formattedCode+="}";
			if(t.charAt(j+1) != '}'){
				for(int i = 0; i < tabCounter; i++){
					formattedCode+="\t";
				}
			}
		}
		else{
			formattedCode+=ch;
		}
	}

	//System.out.println();

    }

    public void exportAs(String outfile){
	// file io here
	// outfile is name of the file it is exported to
    }
    
}
