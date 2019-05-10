import java.util.Scanner;

// completed
public class Format{
	
    public String formatText(String unformatted){
	String formatted = new String();
	unformatted += " ";
	int tabCounter = 0;
	boolean comment = false;
	boolean[] forloop;
	forloop = new boolean[2];
	forloop[0] = false;
	forloop[1] = false;
	for(int j = 0; j <unformatted.length(); j++){
	    char ch = unformatted.charAt(j);
	    // for loops
	    if(ch == 'f' && unformatted.charAt(j+1) == 'o' && unformatted.charAt(j+2) == 'r' && (unformatted.charAt(j+3) == '('
												 || unformatted.charAt(j+3) == ' ')){
		forloop[0] = true;
		forloop[1] = true;
		formatted+="f";
	    }
	    // one line comments
	    else if(ch == '/' && unformatted.charAt(j+1) == '/'){
		comment = true;
		formatted+="/";
	    }
	    // newline characters
	    else if (ch == '\n'){
		if (comment){
		    formatted+=ch;
		}
	    }
	    else if(ch == ' '){
		if (!(unformatted.charAt(j-1) == ';') && !(unformatted.charAt(j-2) == '\\' && unformatted.charAt(j-1) == 'n')){
		    formatted+=" ";
		}
	    }
	    // tab characters
	    else if (ch == '\t'){
		// do nothing
	    }
	    // semi colons
	    else if(ch == ';'){
		if(forloop[0] == true){
		    formatted+=ch;
		    forloop[0] = false;
		}
		else if(forloop[1] == true){
		    formatted+=ch;
		    forloop[1] = false;
		}
		else{
		    formatted+=ch;
		    formatted+="\n";
		    if (unformatted.charAt(j+1) != '}'){
			for(int i = 0; i < tabCounter; i++){
			    formatted+="\t";
			}
		    }
		    else {
			for(int i=0; i < tabCounter-1; i++){
			    formatted+="\t";
			}
		    }
		}
	    }
	    else if(ch == '{'){
		tabCounter++;
		formatted+="{";
		formatted+="\n";
		for(int i = 0; i < tabCounter; i++){
		    formatted+="\t";
		}
	    }
	    else if(ch == '}'){
		/*
		  if(unformatted.charAt(j-1) != '}' && unformatted.charAt(j-1) != ';' && unformatted.charAt(j-1) != ' '){
		  System.ouunformatted.println();
		  }
		*/
		tabCounter--;
		if(unformatted.charAt(j-1) == '}'){
		    for(int i = 0; i < tabCounter; i++){
			formatted+="\t";
		    }
		}
		formatted+="}";
		if(unformatted.charAt(j+1) != '}'){
		    for(int i = 0; i < tabCounter; i++){
			formatted+="\t";
		    }
		}
	    }
	    else{
		formatted+=ch;
	    }
	}
	return formatted;
    }
    
}
