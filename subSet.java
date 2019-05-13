import java.util.Scanner;
import java.io.*;


public class subSet{
    public static void main(String[] args){

	    
		// File file = new File("/Users/evanaltshule/Documents/UCSB/spring19/cs48/Project/subset/formatTest.txt");
		// ImgToString output = new ImgToString(file.getPath());
		// output.format(output.getFilePathway());
		// output.exportAs("~/Documents/UCSB/spring19/cs48/Project/subset/finalOutput.txt");

		Format format = new Format();
		String uf = "int main(){cout << \"Hello World \"; if(we do something here){ return something; }return 0; //a comment \n /* comment 1 \n comment 2 */}";
		String f = new String();
		f = format.formatText(uf);
		System.out.println(f);

		/*
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

*/

/*
		Scanner s = new Scanner(System.in);
		System.out.println("Enter image file pathway. If file is in current directly, add \"./\" before your file name.");

		String filePathway = new String(s.next());
		System.out.print("File name: \"");
		System.out.print(filePathway);
		System.out.println("\"");

		ImgToString test = new ImgToFormattedString(filePathway);
		test.setOutputType(".java");
		test.exportAs();

		System.out.println("File output completed under outfile.java");

	*/
    }
}