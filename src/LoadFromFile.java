import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoadFromFile 
{
	private FileReader readFile;
	private BufferedReader lineRead;

	//Dateiname von der geladen werden soll
	private String file;
	
	//was in Datei steht und bearbeitet werden soll
	private String infoFromFile;
	
	private int curLvl;
	
	public LoadFromFile(String file)
	{
		this.file = file;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile); 
					
			//Datei Information werden ausgelesen
			infoFromFile = lineRead.readLine();
			
			//Bearbeitung der Datein Informationen
			if(infoFromFile.length()==1)
			{
				//Erstes gibt Level wieder
				curLvl = Integer.parseInt(infoFromFile.substring(0));
			}
			
			readFile.close();
		}
		catch(IOException e)
		{
			System.out.println("Datei konnte nicht gelesen werden!");
		}
	}

	public int getCurLvl() {
		return curLvl;
	}

	public void setCurLvl(int curLvl) {
		this.curLvl = curLvl;
	}
	
	
}
