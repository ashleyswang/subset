import java.io.*;

public class Entry implements Serializable
{
	private static final int maxFiles = 5; //Number of files each entry can take
	private int numFiles;
	private String files[] = new String[maxFiles];
	

	public Entry()
	{}

	public Entry(String file)
	{
		files[0] = file;
		numFiles++;
	}

	public void addFile(String file)
	{
		if (numFiles < maxFiles)
		{
			numFiles++;
		}
		shift();
		files[0] = file;
	}

	public String getFile(int index) //0 is most recent file
	{
		if (index > -1 && index < numFiles)
		{
			return files[index];
		}
		System.out.println("File does not exist");
		return null;
	}

	public String[] getAllFiles() //Returns String array with all files
	{
		String allFiles[] = new String[numFiles];
		for (int i = 0; i < numFiles; i++)
		{
			allFiles[i] = files[i];
		}
		return allFiles;
	}

	public int getNumFiles()
	{
		return numFiles;
	}

	public void printFiles()
	{
		for (int i = 0; i < numFiles; i++)
		{
			System.out.println(i + ": " + files[i]);
		}
		System.out.println("-------------------------------------------------------------------");
	}

	private void shift()
	{
		for (int i = numFiles-1; i > 0; i--)
		{
			files[i] = files[i-1];
		}
	}
}
