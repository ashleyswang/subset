import java.util.Scanner;

public class Format{
	public static void main(String[] args){
	Scanner s = new Scanner(System.in);
	System.out.print("Enter a one liner: ");
	String t = s.nextLine();
	t+= ' ';
	int tabCounter = 0;
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
		}
		// semi colons
		if(ch == ';'){
			if(forloop[0] == true){
				System.out.print(ch);
				forloop[0] = false;
			}
			else if(forloop[1] == true){
				System.out.print(ch);
				forloop[1] = false;
			}
			else{
				System.out.print(ch);
				System.out.println();
				if (t.charAt(j+1) != '}'){
				    for(int i = 0; i < tabCounter; i++){
					System.out.print("\t");
				    }
				}
				else {
				    for(int i=0; i < tabCounter-1; i++){
					System.out.print("\t");
				    }
				}
			}
		}
		else if(ch == '{'){
			tabCounter++;
			System.out.print("{");
			System.out.println();
			for(int i = 0; i < tabCounter; i++){
				System.out.print("\t");
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
					System.out.print("\t");
				}
			}
			System.out.println('}');
			if(t.charAt(j+1) != '}'){
				for(int i = 0; i < tabCounter; i++){
					System.out.print("\t");
				}
			}
		}
		else{
			System.out.print(ch);
		}
	}

	//System.out.println();

}

}
