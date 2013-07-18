import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *  Klasse, welche die Level und die jeweiligen Raeume aus Textdateien einliest.
 *  @author Mike Bechtel
 */
public class Manager_Level 
{
	/**
	 * FileReader zum Einlesen der Datei
	 */
	private FileReader readFile;
	/**
	 * BufferedReader zum Zeilenweisen lesen einer Textdatei
	 */
	private BufferedReader lineRead;

	/**
	 * Speichert die Bloecke des Spielfeldes als Character
	 */
	private char [][] field;
	/**
	 * Speichert die Bloecke des Spielfeldes als Block-Objekt
	 */
	private Block_Block [][] fieldInit;
	
	/**
	 * Speichert die Zeilen und Spalten des Spielfeldes
	 */
	private int rows, columns;
	
	/**
	 * Speichert die Tile-Groesse
	 */
	private int sizeX, sizeY;

	/**
	 * Speichert die Startposition des Players in dem Raum
	 */
	private int [] startPos;
	/**
	 * Speichert die Position, zu der der Spieler zurueckkehrt, wenn er einen Raum
	 * zurueckgeht
	 */
	private int [] backPos;
	
	/**
	 * ArrayList zum Speichern der Items in dem Raum
	 */
	private ArrayList<Item> items;
	/**
	 * ArrayList zum Speichern der NPCs in dem Raum
	 */
	private ArrayList<NPC> npcs;
	/**
	 * ArrayList zum Speichern der Gegner in dem Raum
	 */
	private ArrayList<Enemy> enemys;
	/**
	 * ArrayList zum Speichern der aufsammelbaren Items in dem Raum
	 */
	private ArrayList<Collectable> collectables;
	
	/**
	 * Speicher einen moeglichen CheckPoint im Raum
	 */
	private double checkPointX, checkPointY;

	/**
	 * Konstruktor des Managers
	 * @param sizeX - Tile-Groesse X
	 * @param sizeY - Tile-Groesse Y
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
     * erstellt wird. Zudem werden die verschiedenen Array-Listen gefuellt.
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
				case 'S': fieldInit[i][j] = new Block_Shop(posX, posY, sizeX, sizeY); break;
				
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
	
	/**
	 * Liest die einzelnen Zeichen des Textdatei zur Darstellung und Speicherung der Bloecke
	 * @param file - einzulesende Datei
	 * @return <b>char</b>-Array welches die Bloecke repraesentiert
	 */
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
	
	/**
	 * Liest die in dem Raum enthaltenen Items ein und fuellt so die ArrayList <i>items</i>
	 * @param file - einzulesende Datei
	 */
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

	/**
	 * Liest die in dem Raum enthaltenen Trigger ein und fuellt so die 
	 * verschiedenen Trigger (Start Position, Checkpoint, etc)
	 * @param file - einzulesende Datei
	 */
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

	/**
	 * Liest die in dem Raum enthaltenen NPCs ein und fuellt so die ArrayList <i>npcs</i>
	 * @param file - einzulesende Datei
	 */
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
				case "NPC_SHOP_DOWN": npcs.add(new NPC_Shop(posX, posY, Direction.DOWN)); break;
				case "NPC_SHOP_UP": npcs.add(new NPC_Shop(posX, posY, Direction.UP)); break;
				case "NPC_SHOP_LEFT": npcs.add(new NPC_Shop(posX, posY, Direction.LEFT)); break;
				case "NPC_SHOP_RIGHT": npcs.add(new NPC_Shop(posX, posY, Direction.RIGHT)); break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}

	/**
	 * Liest die in dem Raum enthaltenen Gegner ein und fuellt so die ArrayList <i>enemys</i>
	 * @param file - einzulesende Datei
	 */
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

				case "ENEMY_ATTENTIONWHORE_RIGHT": enemys.add(new Enemy_AttentionWhore(posX, posY, Direction.RIGHT, 50)); break;
				case "ENEMY_ATTENTIONWHORE_LEFT": enemys.add(new Enemy_AttentionWhore(posX, posY, Direction.LEFT, 50)); break;
				case "ENEMY_ATTENTIONWHORE_UP": enemys.add(new Enemy_AttentionWhore(posX, posY, Direction.UP, 50)); break;
				case "ENEMY_ATTENTIONWHORE_DOWN": enemys.add(new Enemy_AttentionWhore(posX, posY, Direction.DOWN, 50)); break;

				case "ENEMY_DUCKFACE_RIGHT": enemys.add(new Enemy_Duckface(posX, posY, Direction.RIGHT, 50)); break;
				case "ENEMY_DUCKFACE_LEFT": enemys.add(new Enemy_Duckface(posX, posY, Direction.LEFT, 50)); break;
				case "ENEMY_DUCKFACE_UP": enemys.add(new Enemy_Duckface(posX, posY, Direction.UP, 50)); break;
				case "ENEMY_DUCKFACE_DOWN": enemys.add(new Enemy_Duckface(posX, posY, Direction.DOWN, 50)); break;

				case "BOSS_MICKI_RIGHT": enemys.add(new Boss_Micki(posX, posY, Direction.RIGHT, 50)); break;
				case "BOSS_MICKI_LEFT": enemys.add(new Boss_Micki(posX, posY, Direction.LEFT, 50)); break;
				case "BOSS_MICKI_UP": enemys.add(new Boss_Micki(posX, posY, Direction.UP, 50)); break;
				case "BOSS_MICKI_DOWN": enemys.add(new Boss_Micki(posX, posY, Direction.DOWN, 50)); break;
				}
			}	
		}
		catch(IOException e)
		{			
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler beim Lesen der Datei auf (IOException).");
			System.exit(0);
		}
	}

	/**
	 * Liest die in dem Raum enthaltenen aufsammelbaren Items ein und fuellt so die ArrayList <i>collectables</i>
	 * @param file - einzulesende Datei
	 */
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
		
	/**
	 * Gibt die Startposition des Spieler in dem Raum zurueck
	 * @return Startposition des Spielers
	 */
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
	
	/**
	 * Gibt die Back-Position des Spieler in dem Raum zurueck
	 * @return Back-Position des Spielers
	 */
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
	
	/**
	 * Gibt die ArrayList der Items zurueck
	 * @return ArrayList der Items
	 */
	public ArrayList<Item> getItems()
	{
		return items;
	}

	/**
	 * Gibt die ArrayList der NPCs zurueck
	 * @return ArrayList der NPCs
	 */
	public ArrayList<NPC> getNPC()
	{
		return npcs;
	}

	/**
	 * Gibt die ArrayList der Gegner zurueck
	 * @return ArrayList der Gegner
	 */
	public ArrayList<Enemy> getEnemys()
	{
		return enemys;
	}

	/**
	 * Gibt die ArrayList der aufsammelbaren Items zurueck
	 * @return ArrayList der aufsammelbaren Items
	 */
	public ArrayList<Collectable> getCollectables()
	{
		return collectables;
	}
	
	/**
	 * Gibt die x-Pos eines moeglichen CheckPoints zurueck
	 * @return x-Pos des CheckPoints
	 */
	public double getCheckPointX()
	{
		return checkPointX;
	}

	/**
	 * Gibt die y-Pos eines moeglichen CheckPoints zurueck
	 * @return y-Pos des CheckPoints
	 */
	public double getCheckPointY()
	{
		return checkPointY;
	}
}
