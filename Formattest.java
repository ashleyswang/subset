import java.io.*;

public class Formattest {
	public static void main(String[] arg) {
		Format format = new Format();
		String s = "\"int main(){cout << \"Hello World \"; if(we do something here){ return something;} return 0; //a comment \n /* comment 1 \n comment 2 */} \"";
		String f = format.formatText(s);
		String expected = "int main(){\n\tcout << \"Hello World \";\n\tif(we do something here){\n\t\t return something;\n\t}\n\t return 0;\n\t//a comment \n /* comment 1 \n comment 2 */\n}\n ";
		
		String s2 = "\" \"";
		String f2 = format.formatText(s2);
		String expected2 = " ";

		String s3 = "\"int main(){int i=1; \n\t/* The loop would continue to print\n\t* the value of i until the given condition\n\t* i<=6 returns false.\n\t*/while(i<=6){cout<<\"Value of variable i is: \"<<i<<endl; i++;}} \"";
		String f3 = format.formatText(s3);
		String expected3 = "int main(){\n\tint i=1;\n\t\n/* The loop would continue to print\n* the value of i until the given condition\n* i<=6 returns false.\n*/\n\twhile(i<=6){\n\t\tcout<<\"Value of variable i is: \"<<i<<endl;\n\t\ti++;\n\t}\n}\n ";

		System.out.println(f.equals(expected));
		System.out.println(f2.equals(expected2));
		System.out.println(f3.equals(expected3));
	}
}
