import java.util.*;
import java.io.*;

public class Database implements Serializable
{
	private Hashtable<String, Entry> data = new Hashtable<String, Entry>();

	public Database()
	{}

	public void addEntry(String email, String file) //Adds Entry to Hashtable if it does not exist and adds File to it, otherwise adds File to existing Entry
	{
		if (data.containsKey(email))
		{
			Entry entry = data.get(email);
			entry.addFile(file);
			return;
		}
		data.put(email, new Entry(file));
	}

	public Entry getEntry(String email) //Returns Entry of specified Email
	{
		if (data.containsKey(email))
		{
			return data.get(email);
		}
		System.out.println("Email is not in Database");
		return null;
	}

	public String getFile(String email, int file) //Gets specified File from specified Entry in Hashtable
	{
		if (data.containsKey(email))
		{
			Entry entry = data.get(email);
			return entry.getFile(file);
		}
		System.out.println("Email is not in Database");
		return null;
	}

	public void printAll() //Prints all Entries and corresponding Files in Hashtable
	{
		System.out.println("Emails in Database: " + data.size());
		System.out.println("-------------------------------------------------------------------");
		Set<String> emails = data.keySet();

		for (String email : emails)
		{ 
            printEntry(email);
		}
	}

	public void printEntry(String email) //Prints all Files in specified Entry
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

	public int getNumEntries() //Returns number of Entries in Hashtable
	{
		return data.size();
	}

	public void removeEntry(String email) //Clear specified entry from table
	{
		if (data.containsKey(email))
		{
			data.remove(email);
			return;
		}
		System.out.println("Email is not in Database");
	}

	public void clearData() //Clears all Entries from Hashtable
	{
		data.clear();
	}

	private Hashtable<String, Entry> getHashtable() //Returns Hashtable
	{
		return data;
	}

	public void saveDatabase() //Saves Hashtable to HashData.ser
	{
		try
		{
			FileOutputStream fs = new FileOutputStream("HashData.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);

			os.writeObject(this);
			os.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void getDatabase() //Copies Hashtable from HashData.ser
	{
		try
		{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("HashData.ser"));
			Database restored = (Database) is.readObject();
			data = restored.getHashtable();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
