import java.util.*;

public class Database
{
	private Hashtable<String, Entry> Data = new Hashtable<String, Entry>();
	
	public Database()
	{}

	public void addEntry(String email, String file) //Email and file contents as a String
	{
		if (Data.containsKey(email))
		{
			Entry entry = Data.get(email);
			entry.addFile(file);
			return;
		}
		Data.put(email, new Entry(file));
	}

	public Entry getEntry(String email)
	{
		return Data.get(email);
	}
	
	public String getFile(String email, int file) //0 is most recent file
	{
		Entry entry = Data.get(email);
		return entry.getFile(file);
	}

	public void printAll() //Prints all Entries and corresponding Files in Database
	{
		System.out.println("Emails in Database: " + Data.size());
		System.out.println("-------------------------------------------------------------------");
		Set<String> emails = Data.keySet();

		for (String email : emails)
		{ 
            printEntry(email);
		}
	}

	public void printEntry(String email) //Prints all files associated with Email
	{
		Entry entry = Data.get(email);
		System.out.println(email + ":");
		entry.printFiles();
	}

	public void simplePrint()
	{
		System.out.println(Data);
	}

	public int getNumEntries()
	{
		return Data.size();
	}

	public void removeEntry(String email) //Clear specified entry from table
	{
		Data.remove(email);
	}

	public void clearData() //Clear all Entries from table
	{
		Data.clear();
	}
}