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
	
	//das eigentliche Feld, gespeichert als char-Array
	char [][] field;

	//zum speichern von Levelinformationen
	private String levelLocation;
	
	/**
     * Konstruktor des ReadLevel-Objekts
     *
     */
	public ReadLevel()
	{
		
	}
	
    /**
     * Liest eine Textdatei ein
     * erste Zeile mit den Levelinfos wird seperat gespeichert
     * die weiteren stellen das spielfeld dar, diese 
     * werden in ein Array gespeichert 
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
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			//Erste Zeile wird als String hier gespecihert
			levelLocation = lineRead.readLine();
			
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
				
			}
				field = new char[linesString.length][linesString[0].length()];
				
				for(int i = 0; i < linesString.length; i++)
					for(int j = 0; j < linesString[0].length(); j++)
						field[i][j] = linesString[i].charAt(j);			
			
		}
		catch(IOException e)
		{
			return null;
		}
			return field;
	}

	public String getLevelLocation() {
		return levelLocation;
	}

	public void setLevelLocation(String levelLocation) {
		this.levelLocation = levelLocation;
	}

}
