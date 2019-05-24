import java.io.*;
import java.util.Scanner;

public class Compiler {

	public void compile(String fileName){
	    String s;
      Process p;
      try {
          p = Runtime.getRuntime().exec("javac " + fileName + ".java");
          BufferedReader br = new BufferedReader(
               new InputStreamReader(p.getInputStream()));
          while ((s = br.readLine()) != null)
              System.out.println(s);
          p.waitFor();
          p.destroy();
        } catch (Exception e) {}
	}

	public String run(String fileName){
		String s;
		String output = "";
        Process r;
        try {
            r = Runtime.getRuntime().exec("java " + fileName);
            BufferedReader br = new BufferedReader(
                new InputStreamReader(r.getInputStream()));
            while ((s = br.readLine()) != null){
                output += (s + "\n");
            }
            r.waitFor();
            r.destroy();
        } catch (Exception e) {}
        return output;
	}

  public String FindFileName(String fileText){
    String fileName = new String();
    boolean fileSwitch = false;
    int nameSwitch = 0;
    for(int i = 0; i <fileText.length(); i++){
      char ch = fileText.charAt(i);
      if(ch == 'p' && fileText.charAt(i+1) == 'u' && fileText.charAt(i+2) == 'b' && fileText.charAt(i+3) == 'l' && fileText.charAt(i+4) == 'i' && fileText.charAt(i+5) == 'c' && fileText.charAt(i+6) == ' ' && fileText.charAt(i+7) == 'c' && fileText.charAt(i+8) == 'l' && fileText.charAt(i+9) == 'a' && fileText.charAt(i+10) == 's' && fileText.charAt(i+11) == 's'){
        fileSwitch = true;
      }
      if(fileSwitch == true){
        if(nameSwitch >= 13 && fileText.charAt(i) != '{' && fileText.charAt(i) != ' '){
           fileName += ch;
         }
         nameSwitch++;
      if(fileText.charAt(i) == '{' || (nameSwitch > 13 && fileText.charAt(i) == ' '))
        break;
      }
    }
    return fileName;
  }


    public static void main(String args[]) {
        Compiler compiler = new Compiler();
       	/*String fileName = "CompilerTest";
       	try{
       		compiler.compile(fileName);
       	}
       	catch (Exception e){
       		System.out.println("Compilation error");
       	}
       	System.out.print(compiler.run(fileName));
        */
        String text = "import java.util.Stack;public class Format{public String formatText(String unformatted){";
        System.out.println(compiler.FindFileName(text));

    }
    
    
}
