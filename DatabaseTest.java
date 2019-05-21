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
		data.saveDatabase();

		System.out.println();

		Database data2 = new Database();
		data2.printAll();

		System.out.println();

		data2.getDatabase();
		data2.printAll();
		
		System.out.println();

		System.out.println(data2.getFile("georgekripac@ucsb.edu", 0));
		System.out.println(data2.getFile("george@ucsb.edu", 0));
		System.out.println(data2.getFile("georgekripac@ucsb.edu", 5));
		data2.removeEntry("georgekripac@ucsb.edu");
		data2.removeEntry("georgekripac@ucsb.edu");
		data2.printAll();
		System.out.println(data2.getAllFiles("julialiu@ucsb.edu")[0]);
		System.out.println(data2.getAllFiles("julialiu@ucsb.edu").length);
		System.out.println(data2.getAllFiles("ealtshule@ucsb.edu").length);
		System.out.println(data2.getAllFiles("kellywang@ucsb.edu").length);
		
		
		/*
		try
		{
			FileOutputStream fs = new FileOutputStream("HashData.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(data);
			os.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		try
		{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("HashData.ser"));
			Database Restored = (Database) is.readObject();
			
			Restored.printAll();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		*/
	}
}
