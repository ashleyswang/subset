import java.io.*;

public class Formattest {
	public static void main(String[] arg) {
		Format format = new Format();
		String s = "\"int main(){cout << \"Hello World \"; if(we do something here){ return something;} return 0; //a comment \n /* comment 1 \n comment 2 */} \"";
		String f = format.formatText(s);
		System.out.println(f);
	}
}