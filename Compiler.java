import java.io.*;
import java.util.Scanner;

public class Compiler {

	public static void compile(String fileName){
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

/*
    public static void main(String args[]) {
    	Compiler comp = new Compiler();
       	String fileName = "CompilerTest";
       	try{
       		comp.compile(fileName);
       	}
       	catch (Exception e){
       		System.out.println("Compilation error");
       	}
       	System.out.print(comp.run(fileName));
    }
    */
}
