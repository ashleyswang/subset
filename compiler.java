import java.io.*;

public class Compiler {

	public void compile(String fileText){
	    String s;
      Process p;
      FileOutputStream out = null;
      try{
          out = new FileOutputStream("compileFile.java");
          for(int i = 0; i <fileText.length(); i++){
            out.write(fileText.charAt(i));
          }
      }
      finally {
         if (out != null) {
            out.close();
         }
      }
      try {
          p = Runtime.getRuntime().exec("javac compileFile.java");
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
            r = Runtime.getRuntime().exec("java compileFile.java");
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


    public static void main(String args[]) {
    	compiler comp = new compiler();
       	String fileName = "compilertest";
       	try{
       		comp.compile(fileName);
       	}
       	catch (Exception e){
       		System.out.println("Compilation error");
       	}
       	System.out.print(comp.run(fileName));
    }
}