import java.io.*;
import java.util.ArrayList;

public class ReadLevel 
{
	private FileReader readFile;
	private BufferedReader lineRead;
	boolean quad;
	char [][] field;
	
	public ReadLevel()
	{
	}
	
    /**
     * Liest eine Textdatei ein, in der ein quadratisches Spielfeld gespeichert ist.
     *
     * @param file Einzulesende Datei als String (Dateiname)
     * 
     * @return Ein Array mit den chars, welche das Spielfeld beschreiben -
     * NULL, wenn das Spielfeld nicht quadratisch ist 
     * 
     * @throws IOException falls beim Einlesen der Datei ein Fehler auftrat
     */
	public char [][] readLevel(String file)
	{
		ArrayList lines = new ArrayList();
		String [] linesString;
		
		int l = 0;
		
		quad = true;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			lines.add(l, lineRead.readLine());
			
			while(lines.get(l) != null)
			{
				l++;
				
				lines.add(l, lineRead.readLine());
			}
			
			linesString = new String[lines.size() - 1];
			
			for(int i = 0; i < linesString.length; i++)
			{
				linesString[i] = (String) lines.get(i);
				
				if(i > 0)
					if(linesString[i].length() != linesString[i-1].length())
						quad = false;
			}
			
			if(quad)
			{
				field = new char[linesString.length][linesString[0].length()];
				
				for(int i = 0; i < linesString.length; i++)
					for(int j = 0; j < linesString[0].length(); j++)
						field[i][j] = linesString[i].charAt(j);
			}			
		}
		catch(IOException e)
		{
			System.out.print(""+lines.get(1));
		}
		
		if(!quad)
			return null;
		else
			return field;
	}
}
