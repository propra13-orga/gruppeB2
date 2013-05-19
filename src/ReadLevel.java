import java.io.*;
import java.util.ArrayList;

/**
 *  <i>ReadLevel</i>. Klasse, welche das Level aus einer Textdatei einliest
 */
public class ReadLevel 
{
	//FileReader und BufferedReader um die Datei einzulesen
	private FileReader readFile;
	private BufferedReader lineRead;
	
	//Boolean, welches prueft, ob das eingelesene Feld auch rechteckig ist
	boolean quad;
	//das eigentliche Feld, gespeichert als char-Array
	char [][] field;
	
    /**
     * Konstruktor des ReadLevel-Objekts
     *
     */
	public ReadLevel()
	{
	}
	
    /**
     * Liest eine Textdatei ein, in der ein quadratisches Spielfeld gespeichert ist.
     *
     * @param file - Einzulesende Datei als String (Dateiname)
     * 
     * @return Ein Array mit den chars, welche das Spielfeld beschreiben </br>
     * <b>null</b>, wenn das Spielfeld nicht quadratisch ist 
     * 
     * @throws IOException falls beim Einlesen der Datei ein Fehler auftrat
     */
	public char [][] readLevel(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
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
			return null;
		}
		
		if(!quad)
			return null;
		else
			return field;
	}
}
