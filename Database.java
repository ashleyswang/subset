import java.util.*;
import java.io.*;

public class Database implements Serializable
{
	private Hashtable<String, Entry> data = new Hashtable<String, Entry>();

	public void addEntry(String email, String file) //Email and file contents as a String
	{
		if (data.containsKey(email))
		{
			Entry entry = data.get(email);
			entry.addFile(file);
			return;
		}
		data.put(email, new Entry(file));
	}

	public Entry getEntry(String email)
	{
		if (data.containsKey(email))
		{
			return data.get(email);
		}
		System.out.println("Email is not in Database");
		return null;
	}
	
	public String getFile(String email, int file) //0 is most recent file
	{
		if (data.containsKey(email))
		{
			Entry entry = data.get(email);
			return entry.getFile(file);
		}
		System.out.println("Email is not in Database");
		return null;
	}

	public void printAll() //Prints all Entries and corresponding Files in Database
	{
		System.out.println("Emails in Database: " + data.size());
		System.out.println("-------------------------------------------------------------------");
		Set<String> emails = data.keySet();

		for (String email : emails)
		{ 
            printEntry(email);
		}
	}

	public void printEntry(String email) //Prints all files associated with Email
	{
		if (data.containsKey(email))
		{
			Entry entry = data.get(email);
			System.out.println(email + ":");
			entry.printFiles();
			return;
		}
		System.out.println("Email is not in Database");
	}

	public void simplePrint()
	{
		System.out.println(data);
	}

	public int getNumEntries()
	{
		return data.size();
	}

	public void removeEntry(String email) //Clear specified entry from table
	{
		if (data.containsKey(email))
		{
			data.remove(email);
		}
		System.out.println("Email is not in Database");
	}

	public void clearData() //Clear all Entries from table
	{
		data.clear();
	}
}