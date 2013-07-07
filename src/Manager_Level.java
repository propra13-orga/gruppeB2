import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *  <i>ReadLevel</i>. Klasse, welche das Level aus einer Textdatei einliest
 */
public class Manager_Level 
{
	//FileReader und BufferedReader um die Datei einzulesen
	private FileReader readFile;
	private BufferedReader lineRead;
	
	//das eigentliche Feld, gespeichert als char-Array
	private char [][] field;
	private Block_Block [][] fieldInit;
	
	private int rows, columns;
	
	private int sizeX, sizeY;

	private int [] startPos;
	private int [] backPos;
	
	private ArrayList<Item> items;
	private ArrayList<NPC> npcs;
	private ArrayList<Enemy> enemys;
	private ArrayList<Collectable> collectables;
	
	private double checkPointX, checkPointY;
	/**
     * Konstruktor des ReadLevel-Objekts
     *
     */
	public Manager_Level(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		startPos = new int[2];
		backPos = new int[2];
		
		items = new ArrayList<Item>();
		npcs = new ArrayList<NPC>();
		enemys = new ArrayList<Enemy>();
		collectables = new ArrayList<Collectable>();
		
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
	public Block_Block[][] initLevel(String lvl)
	{
		field = this.readField(lvl);
		
		if(field == null)
		{
			JOptionPane.showMessageDialog(null, "Die Datei konnte nicht geladen werden oder beinhaltet kein quadratisches Spielfeld.");
			System.exit(0);
		}
		
		columns = field[0].length;
		rows = field.length;
		
		fieldInit = new Block_Block[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				//Position des entsprechenden Blocks
				int posX = j * sizeX + sizeX/2;
				int posY = (sizeY * (rows + 2))-(i * sizeY + sizeY/2);
				
				switch(field[i][j])
				{
				case ' ': fieldInit[i][j] = new Block_Floor(posX, posY, sizeX, sizeY); break;
				case 'X': fieldInit[i][j] = new Block_Wall(posX, posY, sizeX, sizeY); break;
				case 'E': fieldInit[i][j] = new Block_Door(posX, posY, sizeX, sizeY); break;
				case 'T': fieldInit[i][j] = new Block_Stairs(posX, posY, sizeX, sizeY); break;			
				
				default: fieldInit[i][j] = new Block_Floor(posX, posY, sizeX, sizeY); break;
				}	
			}
		}
		
		this.readItems(lvl);
		this.readTriggers(lvl);
		this.readNPC(lvl);
		this.readEnemy(lvl);
		this.readCollectables(lvl);
		
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
				case "ITEM_HEART": items.add(new Item_Heart(posX, posY)); break;
				case "ITEM_COINS": items.add(new Item_Coin(posX, posY)); break;
				case "ITEM_MANA": items.add(new Item_Mana(posX, posY)); break;
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
				case "NPC_CHECKPOINT": npcs.add(new NPC_CheckPoint(posX, posY, 50)); break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}
	
	private void readEnemy(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		int enemyX, enemyY, posX, posY;
		String enemy;
		
		int l = 0;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			while(!lineRead.readLine().equals("[enemy]"));
			
			lines.add(l, lineRead.readLine());
			
			while(lines.get(l) != null && !lines.get(l).equals(""))
			{
				l++;
				lines.add(l, lineRead.readLine());
			}
			
			for(int i = 0; i < l; i++)
			{
				enemyX = Integer.parseInt(lines.get(i).substring(0, lines.get(i).indexOf(",")));
				enemyY = Integer.parseInt(lines.get(i).substring(lines.get(i).indexOf(",") + 1, lines.get(i).indexOf(" ")));
				
				posX = enemyX * sizeX + sizeX/2;
				posY = (sizeY * (rows + 2))-(enemyY * sizeY + sizeY/2);
				
				enemy = lines.get(i).substring(lines.get(i).indexOf("=") + 1, lines.get(i).length()).trim();

				switch(enemy)
				{
				case "ENEMY_KIDHIPSTER_RIGHT": enemys.add(new Enemy_KidHipster(posX, posY, Direction.RIGHT, 50)); break;
				case "ENEMY_KIDHIPSTER_LEFT": enemys.add(new Enemy_KidHipster(posX, posY, Direction.LEFT, 50)); break;
				case "ENEMY_KIDHIPSTER_UP": enemys.add(new Enemy_KidHipster(posX, posY, Direction.UP, 50)); break;
				case "ENEMY_KIDHIPSTER_DOWN": enemys.add(new Enemy_KidHipster(posX, posY, Direction.DOWN, 50)); break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}
	
	private void readCollectables(String file)
	{
		ArrayList<String> lines = new ArrayList<String>();
		
		int itemX, itemY, posX, posY;
		String item;
		
		int l = 0;
		
		try
		{
			readFile = new FileReader(file);
			lineRead = new BufferedReader(readFile);
			
			while(!lineRead.readLine().equals("[collectables]"));
			
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
				case "ITEM_HEALTH_BOTTLE": collectables.add(new Item_HealthBottle(posX, posY)); break;
				case "ITEM_MANA_BOTTLE": collectables.add(new Item_ManaBottle(posX, posY)); break;
				
				
				case "WEAPON_KURZSCHWERT": collectables.add(new Weapon_Kurzschwert(posX, posY)); break;
				case "WEAPON_LANGSCHWERT": collectables.add(new Weapon_Langschwert(posX, posY)); break;
				case "WEAPON_KRUMMSCHWERT": collectables.add(new Weapon_Krummschwert(posX, posY)); break;
				case "WEAPON_SCHIMMERKLINGE": collectables.add(new Weapon_Schimmerklinge(posX, posY)); break;
				case "WEAPON_MORGENSTERN": collectables.add(new Weapon_Morgenstern(posX, posY)); break;
				case "WEAPON_STREITKOLBEN": collectables.add(new Weapon_Streitkolben(posX, posY)); break;
				case "WEAPON_KLEINE_AXT": collectables.add(new Weapon_Kleine_Axt(posX, posY)); break;
				case "WEAPON_GROSSE_AXT": collectables.add(new Weapon_Grosse_Axt(posX, posY)); break;
				case "WEAPON_KRUMMSAEBEL": collectables.add(new Weapon_Krummsaebel(posX, posY)); break;
				case "WEAPON_BERSERKERWUT": collectables.add(new Weapon_Berserkerwut(posX, posY)); break;
				case "WEAPON_BERSERKERZORN": collectables.add(new Weapon_Berserkerzorn(posX, posY)); break;
				case "WEAPON_PRUNKSCHWERT": collectables.add(new Weapon_Prunkschwert(posX, posY)); break;
				

				case "ARMOR_BAUER": collectables.add(new Armor_Bauernkleidung(posX, posY)); break;
				case "ARMOR_LEDER_HARNISCH": collectables.add(new Armor_Leder_Harnisch(posX, posY)); break;
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
	
	public ArrayList<Enemy> getEnemys()
	{
		return enemys;
	}
	
	public ArrayList<Collectable> getCollectables()
	{
		return collectables;
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
