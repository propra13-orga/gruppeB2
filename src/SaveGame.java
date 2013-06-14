import java.io.FileWriter;
import java.io.IOException;


public class SaveGame
{
	FileWriter file;

	//Was in der Datei gespeichert werden  soll
	String info;

	public SaveGame(String info)
	{
		this.info = info;
	}
	
	public void save()
	{
		try
		{
		   file = new FileWriter("savegame");
		   
		   //specihert den String(infos) in die Datei
		   file.write(info);  

		}
		catch(IOException ex)
		{
		   System.out.println("Datei konnte nicht gespeichert werden!");
		}
		
		finally
		{
			//prueft, ob die Datei leer ist
		   if (file != null)
		      try
		      {
		        file.close();
		      }
		      catch(Exception ex)
		      {
		    	  System.out.println("Beim speichern, Datei konnte nicht geschlossen werden!");
		      }
		}
	}
}
