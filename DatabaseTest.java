import java.io.*;

public class DatabaseTest
{
	public static void main(String[] arg)
	{
		Database data = new Database();
		
		data.addEntry("georgekripac@ucsb.edu", "int i = 5;");
		data.addEntry("georgekripac@ucsb.edu", "int i = 4;");
		data.addEntry("ashleyswang@ucsb.edu", "int i = 5;");
		data.addEntry("ealtshule@ucsb.edu", "int i = 1;");
		data.addEntry("ealtshule@ucsb.edu", "int i = -2;");
		data.addEntry("ealtshule@ucsb.edu", "int i = -10;");
		data.addEntry("ealtshule@ucsb.edu", "int i = -5;");
		data.addEntry("ealtshule@ucsb.edu", "int i = -4;");
		data.addEntry("ealtshule@ucsb.edu", "int i = -7;");
		data.addEntry("ealtshule@ucsb.edu", "int i = -77;");
		data.addEntry("ealtshule@ucsb.edu", "int i = 66;");
		data.addEntry("kellywang@ucsb.edu", "int i = 277;");
		data.addEntry("kellywang@ucsb.edu", "int i = 5;");
		data.addEntry("kellywang@ucsb.edu", "int i = 679;");
		data.addEntry("julialiu@ucsb.edu", "int i = 420;");
		
		//data.simplePrint();
		//data.printEntry("georgekripac@ucsb.edu");
		//System.out.println(data.getFile("georgekripac@ucsb.edu", 0));

		data.printAll();

		try
		{
			FileOutputStream fs = new FileOutputStream(“Data.ser”);
			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(Data);
			os.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

	}
}