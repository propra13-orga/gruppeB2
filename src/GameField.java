import java.util.*;


/**
 *  <i>GameField</i>. Stellt das eigentliche Spielfeld dar. Erzeugt das zuge-
 *  hoerige Frame und enthalt die Spielschleife. 
 *  Die Kollisionsabfrage und Logik wird hier behandelt. Zudem werden hier
 *  die einzelnen Level geladen.
 * 
 */
public class GameField implements Runnable
{
	//In diesem String-Array sind die einzelnen Level (Dateinamen) gespeichert.
	String [] lvlArray;
	int currentLvl;
	
	//Array, in welchem die Objekte des Spielfeldes gespeichert sind
	Block[][] field;

	//Eine Klasse ReadLevel, welche sich um das Einlesen der Level aus
	//Textdatein kuemmert
	LevelManager lvl;
	KeyManager key;
	
	Status status;
	
	//Zeilen und Spalten des Spielfeldes
	int rows, columns;
	
	//Gibt zurueck, ob an einer Stelle (links, rechts, oben, unten) eine
	//Kollision auftrat
	boolean collideLeft, collideRight, collideUp, collideDown, inGameMenu;
	
	//Der Spieler
	Player player1;
	
	ArrayList<Item> items;
	ArrayList<NPC> npc;
	ArrayList<Enemy> enemy;
	
	//x- und y-Position des Spielers und x- und y-Position eines Spielfeldblockes 
	//(zur Kollisionsabfrage und Logik)		
	double pX, pY, fX, fY, eX, eY;
	int setBack;
	double diffX, diffY;
	
	
	int countE = 0;
	
	
	//x- und y-Position des Spielers zu Beginn des Levels
	int[] playerPos;
	double playerX, playerY;
	
	//Lebt der Spieler noch? - Spaeter evtl. nicht mehr noetig wegen Lebenspunkten
	boolean isAlive = true;
	
    /**
     * Konstruktor des Spielfeldes
     *
     */
	public GameField()
	{	
		//erzeugt das Array, in dem die Level-Textdateinamen gespeichert werden
		lvlArray = new String[] 
		{
			"lvl1.txt", "lvl2.txt", "lvl3.txt", "lvl4.txt",
			"lvl5.txt", "lvl6.txt", "lvl7.txt", "lvl8.txt",
			"lvl9.txt", "lvl10.txt", "lvl11.txt", "lvl12.txt",
		};
		
		currentLvl = 0;
		
		//Erzeugt die Hilfsklasse zum Einlesen der Textdateien
		lvl = new LevelManager(40, 40);
		key = new KeyManager(this);
		
		inGameMenu = false;
		
		this.initLvl(false, false);
		
		this.run();
	}
		
    /**
     * Initialisiert das Spielfeld aus dem char-Array <i>feld</i> und erzeugt
     * die zugehoerigen Objekte.
     */
	public void initLvl(boolean back, boolean load)
	{		
		isAlive = true;

		field = lvl.initLevel(lvlArray[currentLvl]);
		
		columns = field[0].length;
		rows = field.length;
		
		StdDraw.setCanvasSize(40 * columns, 40 * (rows + 2));
		StdDraw.setXscale(0, 40 * columns);
		StdDraw.setYscale(0, 40 * (rows + 2));
		
		if(!back && !load)
			playerPos = lvl.getStartPosition();
		else if(back)
			playerPos = lvl.getBackPosition();
		else
			playerPos = player1.getCheckPointPos();
			
		if(player1 == null)
			player1 = new Player(playerPos[0], playerPos[1]);
		else
			player1.setPos(playerPos[0], playerPos[1]);
		
		playerX = playerPos[0];
		playerY = playerPos[1];
		
		items = lvl.getItems();
		npc = lvl.getNPC();
		enemy = lvl.getEnemys();
		
		status = new Status(player1);
	}
	
	/**
	 * Berechnung der Kollisionen, deren Folgen und der Spiellogik
	 */
	public void doLogic()
	{	
		//Speichert auftretende Kollision
		int coll;
		
		//Schleifen, in denen das Feld durchlaufen wird um die Kollisionen / Logik
		//zu pruefen
		logicLoop:
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
			{	
				if(player1.getHealth() < 1)
				{
					if(player1.getLives() > 1)
					{
						player1.setHealth(100);
						player1.setMana(0);
						player1.decreaseLives(1);
						
						if(player1.getCheckPointLevel() == -1)
							currentLvl = 0;
						else
							currentLvl = player1.getCheckPointLevel();
						
						items.clear();
						npc.clear();
						
						initLvl(false, player1.getCheckPoint() != null);
						break logicLoop;
					}
				}
				
				//Pruefe Kollision des Spielers mit dem Spielfeldbloecken
				coll = field[i][j].checkCollision(player1);
			
				
				//Schleife, die die Items des Feldes durchlaeuft. Prueft Kollision zwischen
				//Spieler und Items. Bei Kollision wird das Item aus dem Spiel entfernt
				Iterator<Item> it = items.iterator();	
				
				while(it.hasNext())
				{
					Item nextItem = it.next();
					
					if(nextItem.checkCollision(player1))
						it.remove();
				}
	
				
				//Schleife, die die NPC des Feldes durchlaeuft. Wenn der Spiel im Aktionsradius
				//eine NPC ist, zeichne den entsprechenden NPC-Dialog. (e-Taste fuer Interaktion
				//und blaettern der Dialogseiten).
				//Ausserdem wird die Kollision des Spielers mit einem NPC geprueft.
				Iterator<NPC> np = npc.iterator();
				
				while(np.hasNext())
				{
					NPC nextNPC = np.next();
					
					//------------Dialog mit NPC----------------------------------------------
					
					if(nextNPC.playerInRange(player1))
					{
						status.setAvatar(nextNPC.getAvatar());
						
						if(countE > 0)
						{
							String[] dialog = nextNPC.getDialog(countE);
							
							if(dialog != null)
							{
								status.drawDialog(dialog);
								
								if(nextNPC.isOptionDialog(countE))
								{
									String opt = nextNPC.interactWithPlayer();
									
									if(opt.equals(Dialog.ABORT))
										countE = 0;
									else if(opt.equals(Dialog.APPROVE))
									{
										if(nextNPC instanceof CheckPointNPC)
										{
											player1.setCheckPoint(currentLvl, lvl.getCheckPointX(), lvl.getCheckPointY());
											countE++;
										}
									}
								}
							}
							else
								countE = 0;
						}
						else
							status.drawDialog(Dialog.INFO_INTERACTION);
					}
					else
						countE = 0;
					
					
					Iterator<Enemy> en = enemy.iterator();	
					
					while(en.hasNext())
					{
						Enemy nextEnemy = en.next();
						
						if(nextEnemy.playerInLine(player1))
						{
							status.setAvatar(nextEnemy.getAvatar());
							player1.stop();
							
							if(nextEnemy.checkCollision(player1) == Direction.NO_COLLISION)
								nextEnemy.moveToPlayer(player1);
							else
								nextEnemy.drawImg();
						}
					}
					
					//------------Kollision Spieler <-> NPC-----------------------------------
					
					if(coll == Direction.NO_COLLISION)
						coll = nextNPC.checkCollision(player1);
				}	
				
				//Verarbeite moegliche Kollision
				handleCollision(coll);
				
				if(coll == Direction.COLLIDE_DOOR || coll == Direction.COLLIDE_BACK)
					break logicLoop;		
			}				
	}
	
	/**
	 * zeichnet aktuelles Spielfeld mit allen aktiven Elementen
	 */
	public void draw()
	{
		//Spielfeld
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				field[i][j].drawImg();
		
		//Items
		for(Item item : items)
			item.drawImg();
		
		//NPC
		for(NPC curNPC : npc)
			curNPC.drawImg();
		
		//Enemy
		for(Enemy curEnemy : enemy)
			if(!curEnemy.playerInLine(player1))
				curEnemy.drawImg();
		
		//Statusleiste
		status.draw();
	}

    /**
     * Spielschleife. Wird w�hrend des Spiels permanent durchlaufen. Hier werden
     * Animationen realisiert und spielbezogene (interne) Werte gepr�ft und ggf.
     * aktualisiert.
     *
     */
	public void run()
	{				
		//Spielschleife wird so lange durchlaufen, wie der Spieler am leben ist
		while(isAlive)
		{	
			//Wartet mit dem Zeichnen 5ms. Zeichne erst intern das neue Spielfeld
			//um Ladefehler und Ruckeln zu vermeiden
			StdDraw.show(3);
			{
				//Loesche die Zeichenflaeche
				StdDraw.clear(StdDraw.BLACK);
				
				//Mana wird fuellt sich langsam auf
				player1.increaseMana(0.04);

				//Zeichne neues Feld
				draw();
				//Berechnet die Spiellogik
				doLogic();
				
				//Pruefe (moegliche) Tasteneingaben
				if(StdDraw.hasPressedAnyKey())
				{
					if(player1.canMove())
						key.handleKeyInput();
					else
						player1.draw();
				}
				else
					player1.draw();
			}
			//nach interner Aktualisierung zeichne das neue Feld ins Fenster
			StdDraw.show();
		}
	}
	
	private void handleCollision(int col)
	{
		switch(col)
		{
			case Direction.COLLIDE_UP: collideUp = true; break;
			case Direction.COLLIDE_RIGHT: collideRight = true; break;
			case Direction.COLLIDE_DOWN: collideDown = true; break;
			case Direction.COLLIDE_LEFT: collideLeft = true; break;
			
			case Direction.COLLIDE_DOOR:
				if(currentLvl < lvlArray.length)
				{					
					currentLvl++;							

					items.clear();
					npc.clear();
					
					this.initLvl(false, false);					
				}
			break;
			
			case Direction.COLLIDE_BACK:			
					currentLvl--;							

					items.clear();
					npc.clear();
					
					this.initLvl(true, false);	
			break;
		}
	}
}
