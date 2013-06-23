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
	
	private int sizeX, sizeY;

	private int [] startPos;
	private int [] backPos;

	
	private ArrayList<Item> items;
	private ArrayList<NPC> npc;
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
		npc = new ArrayList<NPC>();
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
		field = this.readLevel(lvl);
		
		if(field == null)
		{
			JOptionPane.showMessageDialog(null, "Die Datei konnte nicht geladen werden oder beinhaltet kein quadratisches Spielfeld.");
			System.exit(0);
		}
		
		int columns = field[0].length;
		int rows = field.length;
		
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

				case 'S':
					fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY);
					startPos[0] = posX; startPos[1] = posY; 
				break;

				case 'B':
					fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY);
					backPos[0] = posX; backPos[1] = posY; 
				break;
				
				case 'c':
					fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY);
					items.add(new Coin(posX, posY));
				break;
				
				case 'h':
					fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY);
					items.add(new Heart(posX, posY));
				break;	
				
				case 'm':
					fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY);
					items.add(new Mana(posX, posY));
				break;		
				
				case '*':
					fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY);
					npc.add(new CheckPointNPC(posX, posY));
				break;					
				
				default: fieldInit[i][j] = new Floor(posX, posY, sizeX, sizeY); break;
				}	
			}
		}
		
		return fieldInit;
	}
	
	private char [][] readLevel(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		String [] linesString;
		
		int l = 0;
		
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
		return npc;
	}
}
