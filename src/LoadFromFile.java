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
	
	//Level
	private int curLvl;
	
	//energy
	private double energy;
	
	//mana
	private double mana;
	
	//coins
	private int coins;
	//sword
	private int sword;
	//armor
	private int armor;

	public LoadFromFile(String file)
	{
		this.setFile(file);
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile); 
					
			//Datei Information werden ausgelesen
			infoFromFile = lineRead.readLine();
			
			//Bearbeitung der Datein Informationen
			if(infoFromFile.length()==4)
			{
				//Erste Zahl gibt Level wieder
				curLvl = Integer.parseInt(infoFromFile.substring(0,1));
				//Zweite Zahl gibt Anzahl coins
				coins = Integer.parseInt(infoFromFile.substring(1,2));
				//Dritte Zahl gibt an, ob Schwert im Invntar oder nicht
				sword = Integer.parseInt(infoFromFile.substring(2,3));
				//Vierte Zahl gibt an, ob Schwert im Invntar oder nicht
				armor = Integer.parseInt(infoFromFile.substring(3,4));
			}
			
			readFile.close();
		}
		catch(IOException e)
		{
			System.out.println("Datei konnte nicht gelesen werden!");
		}
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getCurLvl() {
		return curLvl;
	}

	public void setCurLvl(int curLvl) {
		this.curLvl = curLvl;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public int getSword() {
		return sword;
	}

	public void setSword(int sword) {
		this.sword = sword;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
}
