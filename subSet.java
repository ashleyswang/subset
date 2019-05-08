import java.util.Scanner;
import java.io.*;


public class subSet{
	public static void main(String[] args){
		// File file = new File("/Users/evanaltshule/Documents/UCSB/spring19/cs48/Project/subset/formatTest.txt");
		// ImgToString output = new ImgToString(file.getPath());
		// output.format(output.getFilePathway());
		// output.exportAs("~/Documents/UCSB/spring19/cs48/Project/subset/finalOutput.txt");

		Format format = new Format();
		String uf = "int main(){cout << \"Hello World \"; return 0;}";
		String f = new String();
		f = format.formatText(uf);
		//System.out.println(f);

		PrintWriter outfile = null;
	//unformattedCode = textdetectionAPI.detectText(filePathway);

		try{
			outfile = new PrintWriter("outfile.txt");
	    	for(char ch: f.toCharArray()){
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