import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;

public class DatabaseTest {

	@Before
	public void executeBeforeEachTest() {
		System.out.println("Starting test");
	}
	

	@Test
	public void EntryTests() {
		Entry entry = new Entry();
		
		String [] user = new String[]{"a", "b", "c", "d", "e"};
		
		int numFiles = entry.getNumFiles();
		assertEquals(numFiles, 0);

		entry.addFile("f"); //gets removed when the sixth file is added
		entry.addFile("e");
		
		numFiles = entry.getNumFiles();
		assertEquals(numFiles, 2);

		entry.addFile("d");
		entry.addFile("c");
		entry.addFile("b");
		entry.addFile("a");

		numFiles = entry.getNumFiles();
		assertEquals(numFiles, 5);

		String [] entryArray = entry.getAllFiles();

		assertEquals(entryArray, user);

		String file = entry.getFile(1);

		assertEquals(file, "b");
	}
	
	@Test
	public void DatabaseTests() {
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
		
		assertEquals(data.getFile("julialiu@ucsb.edu", 0), "int i = 420;");

		//data.simplePrint();
		//data.printEntry("georgekripac@ucsb.edu");
		//System.out.println(data.getFile("georgekripac@ucsb.edu", 0));

		//data.printAll();

		data.saveDatabase();

		System.out.println();
		
		assertEquals(data.getNumEntries(), 5);

		Database data2 = new Database();
		//data2.printAll();

		assertEquals(data2.getNumEntries(), 0);

		//System.out.println();
		
		data2.getDatabase();
		//data2.printAll();

		//System.out.println();

		assertEquals(data2, data);
		assertEquals(data2.getEntry("georgekripac@ucsb.edu"), data.getEntry("georgekripac@ucsb.edu"));

		String [] fileArray = new String[]{"int i = 4;", "int i = 5;"};
		
		assertEquals(data2.getAllFiles("georgekripac@ucsb.edu"), fileArray);


		/*
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
		System.out.println(data2.getAllFiles("george@ucsb.edu").length);


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
	
	@After
	public void executeAfterTest() {
		System.out.println("Test finished");
	}
	
	@AfterClass
	public static void executeAfterAllTests() {
		System.out.println("All tests complete");
	}
	
	@BeforeClass
	public static void executeBeforeAllTests() {
		System.out.println("Beginning tests");
	}
}
