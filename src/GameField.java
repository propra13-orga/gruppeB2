import java.util.*;


/**
 *  Stellt das eigentliche Spielfeld dar. Erzeugt das zuge-
 *  hoerige Frame und enthalt die Spielschleife. 
 *  Die Kollisionsabfrage und Logik wird hier behandelt. Zudem werden hier
 *  die einzelnen Level geladen.
 * 
 */
public class GameField
{
	/**
	 * Hilfsvariable zur fluessigen Animation
	 */
	long delta = 0;
	/**
	 * Hilfsvariable zur fluessigen Animation
	 */
	long last = 0;
	/**
	 * Hilfsvariable zur fluessigen Animation
	 */
	long fps = 0;

	/**
	 * Speichert das Array, in dem die Level-Dateipfade gespeichert sind
	 */
	String [] lvlArray;
	/**
	 * Speichert das momentane Level / den momentanen Raum
	 */
	int currentLvl;


	/**
	 * Speichert die Bloecke des Spielfeldes zur konkreten Darstellung
	 */
	Block_Block[][] field;


	/**
	 * Level-Manager zum Einlesen der Level- und Raum-Textdateien
	 */
	Manager_Level lvl;
	/**
	 * Key-Manager zur Behandlung von Tasten-Eingaben
	 */
	Manager_Key key;
	/**
	 * Sound-Manager, der die Sound-Dateien zu Beginn laeft und bei bedarf abspielt
	 */
	Manager_Sound snd;
	
	/**
	 * Klasse zur Darstellung der Statusleiste
	 */
	Status status;
	
	/**
	 * Speichert Zeilen bzw. Spalten des Spielfelds
	 */
	int rows, columns;

	/**
	 * <b>booleans</b>, die speichert, ob und wo eine Kollision auftrat
	 */
	boolean collideLeft, collideRight, collideUp, collideDown, inGameMenu;
	/**
	 * Diese <b>booleans</b> speichern, welche Szene den Fokus hat (Spielfeld oder Battle)
	 */
	public boolean mapScreen, battleScreen;

	/**
	 * Speichert das Spieler-Objekt
	 */
	Player player1;
	
	/**
	 * Speichert die Items in dem jeweiligen Raum
	 */
	ArrayList<Item> items;
	/**
	 * Speichert die NPCs in dem jeweiligen Raum
	 */
	ArrayList<NPC> npc;
	/**
	 * Speichert die Gegner in dem jeweiligen Raum
	 */
	ArrayList<Enemy> enemy;
	/**
	 * Speichert die aufsammelbaren Items in dem jeweiligen Raum
	 */
	ArrayList<Collectable> collectables;
	
	/**
	 * Zaehlt, wie oft die E-Taste gedrueckt wurde (Fuer die Dialoge)
	 */
	int countE = 0;
	/**
	 * Speichert, ob der Spieler mit einem NPC interagiert hat
	 */
	boolean interacted = false;
	/**
	 * Speichert die von Spieler gewaehlte Dialog-Option
	 */
	String option = "";
	
	/**
	 * Speichert die x- und y-Position des Spielers
	 */
	int[] playerPos;
	/**
	 * Speichert die x- bzw. y-Position des Spielers
	 */
	double playerX, playerY;

	/**
	 * Speichert, ob der Spieler am leben ist
	 */
	boolean isAlive = true;
	
    /**
     * Konstruktor des Spielfeldes
     *@param snd - Sound Manager wird vom Hauptmenu uebergeben
     */
	public GameField(Manager_Sound snd)
	{	
		//erzeugt das Array, in dem die Level-Textdateinamen gespeichert werden
		lvlArray = new String[] 
		{
			"level/lvl1.txt", "level/lvl2.txt", "level/lvl3.txt", "level/lvl4.txt",
			"level/lvl5.txt", "level/lvl6.txt", "level/lvl7.txt", "level/lvl8.txt",
			"level/lvl9.txt", "level/lvl10.txt", "level/lvl11.txt", "level/lvl12.txt",
		};
		
		currentLvl = 0;
		
		//Erzeugt die Hilfsklasse zum Einlesen der Textdateien
		lvl = new Manager_Level(40, 40);
		key = new Manager_Key(this);
		this.snd = snd;
		
		inGameMenu = false;
		
		this.initLvl(false, false);
		
		mapScreen = true;
		battleScreen = false;
		
		this.run();
	}
		
    /**
     * Initialisiert das Spielfeld mittels des Level-Managers
     * @param back - Ist der Spieler in einen vorherigen Raum gegangen
     * @param load - Wurde ein CheckPoint geladen
     */
	public void initLvl(boolean back, boolean load)
	{		
		last = System.nanoTime();
		
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
			player1 = new Player(playerPos[0], playerPos[1], delta);
		else
			player1.setPos(playerPos[0], playerPos[1]);
		
		playerX = playerPos[0];
		playerY = playerPos[1];
		
		items = lvl.getItems();
		npc = lvl.getNPC();
		enemy = lvl.getEnemys();
		collectables = lvl.getCollectables();
		
		status = new Status(player1);
	}
	
	/**
	 * Berechnung die gesamte Spiellogik (Kollision, NPC-Interaktionen, Gegener-Interaktionen, etc)
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
				//Pruefe Kollision des Spielers mit dem Spielfeldbloecken
				coll = field[i][j].checkCollision(player1);
			
				if(player1.getHealth() < 1)
				{
					if(player1.getLives() > 1)
					{
						player1.setHealth(player1.getMaxHealth());
						player1.setMana(0);
						player1.decreaseLives(1);
						
						if(player1.getCheckPointLevel() == -1)
							currentLvl = 0;
						else
							currentLvl = player1.getCheckPointLevel();
						
						items.clear();
						npc.clear();
						enemy.clear();
						collectables.clear();
						
						initLvl(false, player1.getCheckPoint() != null);
						break logicLoop;
					}
				}
			
				
				//Schleife, die die Items des Feldes durchlaeuft. Prueft Kollision zwischen
				//Spieler und Items. Bei Kollision wird das Item aus dem Spiel entfernt
				Iterator<Item> it = items.iterator();	
				
				while(it.hasNext())
				{
					Item nextItem = it.next();
					
					if(nextItem.checkCollision(player1))
						it.remove();
				}
	
				Iterator<Collectable> co = collectables.iterator();
				
				while(co.hasNext())
				{
					Collectable nextColl = co.next();
					
					if(coll == Direction.NO_COLLISION)
						coll = nextColl.checkCollision(player1);
					
					if(nextColl.checkCollision(player1) != Direction.NO_COLLISION)
					{
						if(countE > 0)
						{
							interacted = true;
							
							player1.stop();
							
							if(player1.inventory.canAddItem(nextColl))
							{
								status.drawInfo(player1.inventory.itemPicked(nextColl));
								
								if(countE == 2)
								{
									player1.inventory.addItem(nextColl);
									countE = 0;
									player1.go();
									co.remove();
								}
							}
							else
							{
								status.drawInfo(player1.inventory.itemNotPicked(nextColl));
								
								if(countE == 2)
								{
									countE = 0;
									player1.go();
								}								
							}
						}
						else
							countE = 0;
					}
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
							interacted = true;
							
							String[] dialog = nextNPC.getDialog(countE);
							
							if(dialog != null)
							{
								status.drawDialog(dialog);
								
								if(nextNPC.isOptionDialog(countE))
								{
									if(option.equals(Dialog.ABORT))
									{
										countE = 0;
										option = "";
									}
									else if(option.equals(Dialog.APPROVE))
									{
										if(nextNPC instanceof NPC_CheckPoint)
										{
											countE++;
											option = "";
											player1.setCheckPoint(currentLvl, lvl.getCheckPointX(), lvl.getCheckPointY());
										}
										else if(nextNPC instanceof NPC_Shop)
										{
											countE++;
											option = "";
											player1.stop();
											mapScreen = false;
											new Shop(this, snd);
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
					
					//------------Kollision Spieler <-> NPC-----------------------------------
					
					if(coll == Direction.NO_COLLISION)
						coll = nextNPC.checkCollision(player1);
					
				}


				//------------Interaktionen mit Gegnern-----------------------------------
					
				Iterator<Enemy> en = enemy.iterator();	
					
				while(en.hasNext())
				{
					Enemy nextEnemy = en.next();
						
					if(nextEnemy.playerInLine(player1))
					{
						status.setAvatar(nextEnemy.getAvatar());
						player1.stop();
						player1.setDiretion(nextEnemy.getDirection());
							
						if(nextEnemy.checkCollision(player1) == Direction.NO_COLLISION)
							nextEnemy.moveToPlayer(player1, delta);
						else
						{
							interacted = true;
							
							nextEnemy.drawImg();
							player1.draw();	
								
							status.drawDialog(nextEnemy.startDialog());
								
							if(countE > 0)
							{
								String[] dialog = nextEnemy.getDialog(countE + 1);
								
								if(dialog != null)
									status.drawDialog(dialog);
								else
									if(mapScreen && !battleScreen)
									{
										status.drawDialog(nextEnemy.getDialog(countE));
										
										mapScreen = false;
										battleScreen = true;
										
										if(nextEnemy instanceof Boss_Micki)
											snd.playSound(9);
										else
											snd.playSound(0);
										
										new BattleScreen(this, nextEnemy);
									}
								
							}
						}
					}
						
					if(coll == Direction.NO_COLLISION)
						coll = nextEnemy.checkCollision(player1);
				}	
				
				//Verarbeite moegliche Kollision
				handleCollision(coll);
				
				if(coll == Direction.COLLIDE_DOOR || coll == Direction.COLLIDE_BACK)
					break logicLoop;	
				
				if(interacted)
					interacted = false;
				else
					countE = 0;
			}		
	}
	
	/**
	 * Zeichnet aktuelles Spielfeld mit allen aktiven Elementen
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
		
		//Einsammelbare Items
		for(Collectable curColl : collectables)
			curColl.drawImg();
		
		//Statusleiste
		status.draw();
	}

    /**
     * Spielschleife. Wird waehrend des Spiels permanent durchlaufen. Hier werden
     * Animationen realisiert und spielbezogene (interne) Werte geprueft und ggf.
     * aktualisiert.
     *
     */
	public void run()
	{				
		//Spielschleife wird so lange durchlaufen, wie der Spieler am leben ist
		while(isAlive && mapScreen)
		{	
			computeDelta();
			//Wartet mit dem Zeichnen 5ms. Zeichne erst intern das neue Spielfeld
			//um Ladefehler und Ruckeln zu vermeiden
			StdDraw.show(3);
			{
				//System.out.println(StdDraw.isKeyPressedSingle('e'));
				//Loesche die Zeichenflaeche
				StdDraw.clear(StdDraw.BLACK);
				
				//Mana wird fuellt sich langsam auf
				player1.increaseMana(0.01);

				//Zeichne neues Feld
				draw();
				//Berechnet die Spiellogik
				doLogic();
				
				//Pruefe (moegliche) Tasteneingaben
				if(StdDraw.hasPressedAnyKey())
				{
					key.handleKeyInput();
				}
				else
					player1.draw();
				
			}
		}
	}
	
	/**
	 * Behandelt auftretende Kollisionen mit Bloecken, NPCs, Gegner, etc.
	 * @param col - <b>int</b> ob und wo eine Kollision auftrat
	 */
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
					enemy.clear();
					collectables.clear();
					
					this.initLvl(false, false);					
				}
			break;
			
			case Direction.COLLIDE_BACK:			
					currentLvl--;							

					items.clear();
					npc.clear();
					enemy.clear();
					collectables.clear();
					
					this.initLvl(true, false);	
			break;
		}
	}
	
	/**
	 * Hilfsmethode fuer fluessige Animationen
	 */
	private void computeDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
		fps = ((long) 1e9)/delta;
	}
}
