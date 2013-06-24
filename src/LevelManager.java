import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *  <i>ReadLevel</i>. Klasse, welche das Level aus einer Textdatei einliest
 */
public class LevelManager 
{
	//FileReader und BufferedReader um die Datei einzulesen
	private FileReader readFile;
	private BufferedReader lineRead;
	
	//das eigentliche Feld, gespeichert als char-Array
	private char [][] field;
	private Block [][] fieldInit;
	
	private int rows, columns;
	
	private int sizeX, sizeY;

	private int [] startPos;
	private int [] backPos;
	
	private ArrayList<Item> items;
	private ArrayList<NPC> npcs;
	
	private double checkPointX, checkPointY;
	/**
     * Konstruktor des ReadLevel-Objekts
     *
     */
	public LevelManager(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		startPos = new int[2];
		backPos = new int[2];
		
		items = new ArrayList<Item>();
		npcs = new ArrayList<NPC>();
		
		checkPointX = -1;
		checkPointY = -1;
	}
	
    /**
     * Liest eine Textdatei ein, welche das Spielfeld darstellt. Speichert diese
     * Zeichen in ein char-Array, aus welchem dann ein Object-Array der Bloecke
     * erstellt wird
     * 
     * @param lvl - Einzulesende Datei als String (Dateiname)
     * 
     * @return Ein Array mit den Block-Objects, welche das Spielfeld beschreiben </br>
     * <b>null</b>, wenn das Spielfeld nicht quadratisch ist 
     * 
     * @throws IOException falls beim Einlesen der Datei ein Fehler auftrat
     */
	public Block[][] initLevel(String lvl)
	{
		field = this.readField(lvl);
		
		if(field == null)
		{
			JOptionPane.showMessageDialog(null, "Die Datei konnte nicht geladen werden oder beinhaltet kein quadratisches Spielfeld.");
			System.exit(0);
		}
		
		columns = field[0].length;
		rows = field.length;
		
		fieldInit = new Block[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				//Position des entsprechenden Blocks
				int posX = j * sizeX + sizeX/2;
				int posY = (sizeY * (rows + 2))-(i * sizeY + sizeY/2);
				
				switch(field[i][j])
				{
				case ' ': fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY); break;
				case 'X': fieldInit[i][j] = new Wall(posX, posY, sizeX, sizeY); break;
				case 'E': fieldInit[i][j] = new Door(posX, posY, sizeX, sizeY); break;
				case 'T': fieldInit[i][j] = new Stairs(posX, posY, sizeX, sizeY); break;			
				
				default: fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY); break;
				}	
			}
		}
		
		this.readItems(lvl);
		this.readTriggers(lvl);
		this.readNPC(lvl);
		
		return fieldInit;
	}
	
	private char [][] readField(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		String [] linesString;
		
		int l = 0;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			lines.add(l, lineRead.readLine());
			
			while(lines.get(l) != null && !lines.get(l).equals(""))
			{
				l++;
				lines.add(l, lineRead.readLine());
			}
			
			linesString = new String[lines.size() - 1];
			
			for(int i = 0; i < linesString.length; i++)
			{
				linesString[i] = lines.get(i);
			}
				field = new char[linesString.length][linesString[0].length()];
				
				for(int i = 0; i < linesString.length; i++)
					for(int j = 0; j < linesString[0].length(); j++)
						field[i][j] = linesString[i].charAt(j);			
			
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
			return field;
	}
	
	private void readItems(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		int itemX, itemY, posX, posY;
		String item;
		
		int l = 0;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			while(!lineRead.readLine().equals("[items]"));
			
			lines.add(l, lineRead.readLine());
			
			while(lines.get(l) != null && !lines.get(l).equals(""))
			{
				l++;
				lines.add(l, lineRead.readLine());
			}
			
			for(int i = 0; i < l; i++)
			{
				itemX = Integer.parseInt(lines.get(i).substring(0, lines.get(i).indexOf(",")));
				itemY = Integer.parseInt(lines.get(i).substring(lines.get(i).indexOf(",") + 1, lines.get(i).indexOf(" ")));
				
				posX = itemX * sizeX + sizeX/2;
				posY = (sizeY * (rows + 2))-(itemY * sizeY + sizeY/2);
				
				item = lines.get(i).substring(lines.get(i).indexOf("=") + 1, lines.get(i).length()).trim();

				switch(item)
				{
				case "ITEM_HEART": items.add(new Heart(posX, posY)); break;
				case "ITEM_COINS": items.add(new Coin(posX, posY)); break;
				case "ITEM_MANA": items.add(new Mana(posX, posY)); break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}
	
	private void readTriggers(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		int triggerX, triggerY, posX, posY;
		String trigger;
		
		int l = 0;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			while(!lineRead.readLine().equals("[triggers]"));
			
			lines.add(l, lineRead.readLine());
			
			while(lines.get(l) != null && !lines.get(l).equals(""))
			{
				l++;
				lines.add(l, lineRead.readLine());
			}
			
			for(int i = 0; i < l; i++)
			{
				triggerX = Integer.parseInt(lines.get(i).substring(0, lines.get(i).indexOf(",")));
				triggerY = Integer.parseInt(lines.get(i).substring(lines.get(i).indexOf(",") + 1, lines.get(i).indexOf(" ")));
				
				posX = triggerX * sizeX + sizeX/2;
				posY = (sizeY * (rows + 2))-(triggerY * sizeY + sizeY/2);
				
				trigger = lines.get(i).substring(lines.get(i).indexOf("=") + 1, lines.get(i).length()).trim();

				switch(trigger)
				{
				case "TRIGGER_PLAYER_START": startPos[0] = posX; startPos[1] = posY; break;
				case "TRIGGER_PLAYER_BACK": backPos[0] = posX; backPos[1] = posY; break;
				case "TRIGGER_SAVEPOINT": checkPointX = posX; checkPointY = posY; break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}
	
	private void readNPC(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		int npcX, npcY, posX, posY;
		String npc;
		
		int l = 0;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			while(!lineRead.readLine().equals("[npc]"));
			
			lines.add(l, lineRead.readLine());
			
			while(lines.get(l) != null && !lines.get(l).equals(""))
			{
				l++;
				lines.add(l, lineRead.readLine());
			}
			
			for(int i = 0; i < l; i++)
			{
				npcX = Integer.parseInt(lines.get(i).substring(0, lines.get(i).indexOf(",")));
				npcY = Integer.parseInt(lines.get(i).substring(lines.get(i).indexOf(",") + 1, lines.get(i).indexOf(" ")));
				
				posX = npcX * sizeX + sizeX/2;
				posY = (sizeY * (rows + 2))-(npcY * sizeY + sizeY/2);
				
				npc = lines.get(i).substring(lines.get(i).indexOf("=") + 1, lines.get(i).length()).trim();

				switch(npc)
				{
				case "NPC_CHECKPOINT": npcs.add(new CheckPointNPC(posX, posY)); break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}
	
	
	//-----------------------------------------------------------------------------
		
	
	public int[] getStartPosition()
	{
		if(startPos.equals(null))
		{
			JOptionPane.showMessageDialog(null, "Es wurde kein Level eingelesen.");
		 	System.exit(0);
			return null;
		}
		else
			return startPos;
	}
	
	public int[] getBackPosition()
	{
		if(startPos.equals(null))
		{
			JOptionPane.showMessageDialog(null, "Es wurde kein Level eingelesen.");
		 	System.exit(0);
			return null;
		}
		else
			return backPos;
	}
	
	public ArrayList<Item> getItems()
	{
		return items;
	}
	
	public ArrayList<NPC> getNPC()
	{
		return npcs;
	}
	
	public double getCheckPointX()
	{
		return checkPointX;
	}
	
	public double getCheckPointY()
	{
		return checkPointY;
	}
}
