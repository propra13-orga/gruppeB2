import javax.swing.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *  <i>GameField</i>. Stellt das eigentliche Spielfeld dar. Erzeugt das zuge-
 *  hoerige Frame und enthalt die Spielschleife. 
 *  Die Kollisionsabfrage und Logik wird hier behandelt. Zudem werden hier
 *  die einzelnen Level geladen.
 * 
 */
public class GameField extends JFrame
{
	private static final long serialVersionUID = 9136636406585847854L;
	
	//In diesem String-Array sind die einzelnen Level (Dateinamen) gespeichert
	String [] lvlArray;
	//Integer, in welchem Level man sich gerade befindet
	int currentLvl;
	
	//Char-Feld mit dem Spielfeld
	char [][] feld;
	
	//Array, in welchem die Objekte des Spielfeldes gespeichert sind
	Block[][] field;
	
	//In der Liste sollen alle Gegenstaende hinterlegt werden
	ArrayList<Block> itemList = new ArrayList<Block>();
	
	//In der Liste sollen alle Gegner hinterlegt werden
	ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	Npc npc;
	//Eine Klasse ReadLevel, welche sich um das Einlesen der Level aus
	//Textdatein kuemmert
	ReadLevel lvl;
	
	//Zeilen und Spalten des Spielfeldes
	int rows, columns;
	
	//Gibt zurueck, ob an einer Stelle (links, rechts, oben, unten) eine
	//Kollision auftrat
	boolean collideLeft, collideRight, collideUp, collideDown, inGameMenu;
	
	//Der Spieler
	Player player1 = new Player(0,0);
	
	//Energieanzeige
	Energy energ;
	
	Mana mana;
	
	Inventar inventar;
	
	//x- und y-Position des Spielers und x- und y-Position eines Spielfeldblockes 
	//(zur Kollisionsabfrage und Logik)
		
	double pX, pY, fX, fY, eX, eY;
	
	//x- und y-Position des Spielers zu Beginn des Levels
	int playerX, playerY;
	
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
			"lvl1.txt", "lvl2.txt", "lvl3.txt"
		};
		
		//Erzeugt die Hilfsklasse zum Einlesen der Textdateien
		lvl = new ReadLevel();
		inGameMenu = false;

		//Lade bei neuem Spielbeginn das erste Level (Array-Position 0)
		currentLvl = 0;
		this.loadLevel(lvlArray[currentLvl]);
		
		//Initialisiere das Feld (aus dem String-Array des Levels)
		this.initField();
		//Starte die Spielschleife
		this.run();
	}
	
	/**
	* Konstruktor zum Neuladen im selben Level
	* spater mit z�hlen der Lebenspunbkte
	* @param currentLvl
	*/
	public GameField(int currentLvl)
	{
		//erzeugt das Array, in dem die Level-Textdateinamen gespeichert werden
		lvlArray = new String[]
		{
			"lvl1.txt", "lvl2.txt", "lvl3.txt"
		};

		//Erzeugt die Hilfsklasse zum Einlesen der Textdateien
		lvl = new ReadLevel();
		inGameMenu = false;
		this.currentLvl = currentLvl;
		this.loadLevel(lvlArray[currentLvl]);

		//Initialisiere das Feld (aus dem String-Array des Levels)
		this.initField();
		//Starte die Spielschleife
		this.run();
	}
	
    /**
     * Methode, die die Textdatei einlesen laesst
     * 
     * @param level - Dateiname des einzulesenden Levels
     */
	public void loadLevel(String level)
	{
		feld = lvl.readLevel(level);
		
		//Wenn die Datei nicht gefunden wird, gebe eine Fehlermeldung zurueck und
		//beende das Spiel
		if(feld == null)
		{
			JOptionPane.showMessageDialog(this, "Die Datei konnte nicht geladen werden oder beinhaltet kein quadratisches Spielfeld.");
			System.exit(0);
		}
	}
	
    /**
     * Initialisiert das Spielfeld aus dem char-Array <i>feld</i> und erzeugt
     * die zugehoerigen Objekte.
     */
	public void initField()
	{	
		//Speichere die Anzahl der Spalten und Zeilen
		columns = feld[0].length;
		rows = feld.length;
		
		isAlive = true;
		
		//Ein neues Block-Array von der entsprechenden Spielfeldgroesse
		field = new Block[rows][columns];
		
		//Setze die Dimensionen der Zeichenebene
		StdDraw.setCanvasSize(40 * columns, 40 * rows);
		StdDraw.setXscale(0, 40 * columns);
		StdDraw.setYscale(0, 40 * rows);
		
		//Schleifen, die die chars aus dem char-Feld in Objekte uebertraegt
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				//Position des entsprechenden Blocks
				int posX = j * 40 + 20;
				int posY = (40 * rows)-(i * 40 + 20);
				
				//Je nach Character erzeuge das entsprechende Objekt
				if(feld[i][j] == ' ')
					field[i][j] = new Floor(posX, posY);
				else if(feld[i][j] == 'x'||feld[i][j] == 'X')
				{
					field[i][j] = new Wall(posX, posY);
				}
				else if(feld[i][j] == 'e'||feld[i][j] == 'E')
					field[i][j] = new Door(posX, posY);
				else if(feld[i][j] == 't'||feld[i][j] == 'T')
					field[i][j] = new Stairs(posX, posY);
				else if(feld[i][j] == 'r'||feld[i][j] == 'R') //Trap hinzugefuegt
					field[i][j] = new Trap(posX, posY);
				
				else if(feld[i][j] == '#'||feld[i][j] == '#')
					field[i][j] = new Status(posX, posY);
				
				else if(feld[i][j] == 'c'||feld[i][j] == 'C')
				{
					field[i][j] = new Floor(posX, posY);
					itemList.add(new Coins(posX, posY));	
				}

				else if(feld[i][j] == 'w'||feld[i][j] == 'W')
				{
					field[i][j] = new Floor(posX, posY);
					itemList.add(new Sword(posX, posY));	
				}
				else if(feld[i][j] == 'h'||feld[i][j] == 'H')
				{
					field[i][j] = new Floor(posX, posY);
					itemList.add(new Heart(posX, posY));	
				}
				else if(feld[i][j] == 'q'||feld[i][j] == 'Q')
				{
					field[i][j] = new Floor(posX, posY);
					itemList.add(new ManaBottle(posX, posY));	
				}
				else if(feld[i][j] == '+'||feld[i][j] == '+')
				{
					field[i][j] = new Status(posX, posY);
					energ = new Energy(posX, posY);
					field[i][j] = energ;
				}
				else if(feld[i][j] == '%'||feld[i][j] == '%')
				{
					field[i][j] = new Status(posX, posY);
					inventar = new Inventar(posX, posY);
					field[i][j] = inventar;
				}
				else if(feld[i][j] == '='||feld[i][j] == '=')
				{
					field[i][j] = new Status(posX, posY);
					mana = new Mana(posX, posY);
					field[i][j] = mana;
				}
				else if(feld[i][j] == 's'||feld[i][j] == 'S')
				{
					field[i][j] = new Floor(posX, posY);
					
					//Erzeugt den Spieler und setzt seine Startposition
					player1.setPosX(posX);
					player1.setPosY(posY);
					playerX = posX;
					playerY = posY;
				}
				else if(feld[i][j] == 'g'||feld[i][j] == 'G')
				{
					field[i][j] = new Floor(posX, posY);
					
					//Gegner in Liste hinzu
					enemyList.add(new Enemy(posX, posY)); 
				}
				else if(feld[i][j] == 'n'||feld[i][j] == 'N')
				{	
					npc = new Npc(posX, posY);
					field[i][j] = npc;
					
				}
			}
			
			System.out.println("");
		}
	}
	
	/**
	 * Berechnung der Kollision und deren Folgen
	 */
	public void calculate()
	{
		//Momentante Positions des Spielers (Kollisionsabfrage)
		pX = player1.getCenterX();
		pY = player1.getCenterY();
		
		npc.setHelp(false);
		//Schleifen, in denen das Feld gezeichnet wird und die Kollision / Logik
		//geprueft wird
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
			{					
				//Energy anzeige
				if(player1.getHealth()>66.6)
				{
					energ.setNrg(1);
				}
				else if(player1.getHealth()>33.3)
				{
					energ.setNrg(2);
				}
				else if(player1.getHealth()>0.1)
				{
					energ.setNrg(3);
				}
				else
					energ.setNrg(4);
				
				// Mana anzeige
				if(player1.getMana()>66.6)
				{
					mana.setNrg(1);
				}
				else if(player1.getMana()>33.3)
				{
					mana.setNrg(2);
				}
				else if(player1.getMana()>0.1)
				{
					mana.setNrg(3);
				}
				else
					mana.setNrg(4);
				
				//Inventar anzeige
				if(player1.getSword()==1)
				{
					inventar.setInventar(1);
				}
				else if(player1.getSword()==0)
				{
					inventar.setInventar(0);
				}
	
				//Position des gerade betrachteten Blocks
				fX = field[i][j].getCenterX();
				fY = field[i][j].getCenterY();
				
				//Kollisionsabfrage bezueglich der soliden Bloecke (Bewegung)
				if(player1.intersects(field[i][j]) && field[i][j].isSolid())
				{
					//Pruefe, wo die Kollision mit dem Spieler auftrat
					if(pX > fX && pY >= fY - 20 && pY <= fY + 20)
						collideLeft = true;
					if(pX < fX && pY >= fY - 20 && pY <= fY + 20)
						collideRight = true;
					if(pY > fY && pX >= fX - 20 && pX <= fX + 20)
						collideDown = true;
					if(pY < fY && pX >= fX - 20 && pX <= fX + 20)
						collideUp = true;
					if(player1.intersects(field[i][j]) && field[i][j].toString()=="npc")
					{
						npc.setHelp(true);
					}
				}
				
				
				//Kollisionsabfrage bezueglich der Ausgaenge
				else if(player1.intersects(field[i][j]) && (field[i][j].toString().equals("door")))
				{
					//Wenn das momentane Level noch nicht das letzte ist, so
					//wechsel zum naechsten Level
					if(currentLvl < lvlArray.length)
					{
						currentLvl++;
						this.loadLevel(lvlArray[currentLvl]);
						this.initField();						
					}
				}
				
				//Kollisionsabfrage mit Trap und direkter Neustart	
				else if(player1.intersects(field[i][j]) && (field[i][j].toString().equals("trap")))
				{	
					isAlive = false;
					player1.setHealthDown(1);//Lebensenergie wird um eins runter gesetzt
					
					//Listen werden f�r neu initialisierung geleert
					itemList.clear(); 
					enemyList.clear();
					
					if(itemList.size()==0 && enemyList.size()==0)
					{
						//Neuladen des Spielfeldes
						StdDraw.clear();
						this.loadLevel(lvlArray[currentLvl]);
						this.initField();	
					}
				}
				
				//Kollisionsabfrage Spieler und Items, vorerst ohne Inventar bearbeitung				
				for(int count=0;count<enemyList.size();count++)
				{	
					if(enemyList.get(count).intersects(field[i][j]) && field[i][j].isSolid())
					{
						eX = enemyList.get(count).getCenterX();
						eY = enemyList.get(count).getCenterY();
						//Pruefe, wo die Kollision mit dem Gegner auftrat
						if(eX > fX && eY >= fY - 20 && eY <= fY + 20)
							enemyList.get(count).setColLeft(true);
						if(eX < fX && eY >= fY - 20 && eY <= fY + 20)
							enemyList.get(count).setColRight(true);
						if(eY > fY && eX >= fX - 20 && eX <= fX + 20)
							enemyList.get(count).setColDown(true);
						if(eY < fY && eX >= fX - 20 && eX <= fX + 20)
							enemyList.get(count).setColUp(true);
					}
					
					//bei kontakt des Spielers und einem Gegner, Lebenspunktabzug
					//gegner explodiert
					if(player1.intersects(enemyList.get(count)))
					{
						if(StdDraw.isKeyPressed(KeyEvent.VK_S) || player1.getFire().isActive()==true)
						{
							enemyList.remove(enemyList.get(count));
						}
						else
						{
							player1.setHealthDown(15.0);
							enemyList.remove(enemyList.get(count));
						}
					}
					
					
				}
				
				for(int count=0;count<itemList.size();count++)
				{
							
					if(player1.intersects(itemList.get(count)))
					{
						if(itemList.get(count).toString()=="coin")
						{
							//Gold
							itemList.remove(itemList.get(count));
							player1.setMoney(1);
						}
						else if(itemList.get(count).toString()=="heart")
						{
							//energie
							itemList.remove(itemList.get(count));
							player1.setHealth(22.0);
						}
						else if(itemList.get(count).toString()=="mana")
						{
							//Mana
							itemList.remove(itemList.get(count));
							player1.setMana(17.0);
						}
						else if(itemList.get(count).toString()=="sword")
						{
							//Sword
							itemList.remove(itemList.get(count));
							player1.setSword(1);
						}						
					}
				}
				
				
				//Evtl Abfrage mit den Treppen-Objekten, um notfalls wieder in
				//das vorherige Level zu gelangen
				/*else if(player1.intersects(field[i][j]) && (field[i][j].toString().equals("stairs")))
				{					
					if(currentLvl > 0)
					{
						currentLvl--;
						this.loadLevel(lvlArray[currentLvl]);
						this.initField();				
					}
				}*/
			}				
	}
	
	/**
	 * zeichnet aktuelles Spielfeld mit allen aktiven Elementen
	 */
	public void drawField()
	{
		//Spielfeld
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				field[i][j].drawImg();
				
		//Aktive Gegner
		for(int c=0;c<enemyList.size();c++)
			enemyList.get(c).draw();
		
		//Aktive Gegenstaende
		for(int count=0;count<itemList.size();count++)
			itemList.get(count).drawImg();
		
		if(player1.getFire().isActive()==true)
		{
			player1.getFire().setPosX((int)player1.getPosX());
			player1.getFire().setPosY((int)player1.getPosY());
			player1.getFire().draw();
			player1.setMana(-0.5);
		}
			
	}

    /**
     * Spielschleife. Wird w�hrend des Spiels permanent durchlaufen. Hier werden
     * Animationen realisiert und spielbezogene (interne) Werte gepr�ft und ggf.
     * aktualisiert.
     *
     */
	public void run()
	{		
		//prueft, ob sich der Spieler in dem Schleifendurchlauf bewegt hat
		//oder nicht
		boolean noMove;
		
		//Spielschleife wird so lange durchlaufen, wie der Spieler am leben ist
		while(isAlive)
		{	
			//Wartet mit dem Zeichnen 5ms. Zeichne erst intern das neue Spielfeld
			//um Ladefehler und Ruckeln zu vermeiden
			StdDraw.show(5);
			{
				//Loesche die Zeichenflaeche
				StdDraw.clear(StdDraw.BLACK);
				
				//Zeichne neues Feld und pruefe Kollisionen
				drawField();
				
				//Berechnet die Kollision
				calculate();
				
				
				noMove = true;
				
				//Gegner bewegt sich nur hoch und runter
				for(int c=0;c<enemyList.size();c++)
				{
					if(!enemyList.get(c).isColDown())
					{
						enemyList.get(c).move("DOWN");
					}
					else if(!enemyList.get(c).isColUp())
					{
						enemyList.get(c).move("UP");
					}
					else
					{
						enemyList.get(c).draw();
					}
				}
				
				//Abfrage nach Tastendruecken des Benutzers. Sorgt fuer Bewegung
				//der Spielfigur		
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
				{
					if(StdDraw.isKeyPressed(KeyEvent.VK_S))
					{
								player1.attack(Direction.RIGHT);
					}		
					if(!collideRight)
					{
						playerX = playerX + 6;
						player1.setPosX(playerX);
					}
				
					if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
						player1.swapImg(Direction.RIGHT);
					
					noMove = false;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					if(StdDraw.isKeyPressed(KeyEvent.VK_S))
					{
						player1.attack(Direction.LEFT);
					}
					if(!collideLeft)
					{
						playerX = playerX - 6;
						player1.setPosX(playerX);
					}
					
					if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
						player1.swapImg(Direction.LEFT);
					
					noMove = false;
				}
				if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
				{
					if(StdDraw.isKeyPressed(KeyEvent.VK_S))
					{
						player1.attack(Direction.UP);
					}
					if(!collideUp)
					{
						playerY = playerY + 6;
						player1.setPosY(playerY);
					}
					
					player1.swapImg(Direction.UP);
					
					noMove = false;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				{
					if(StdDraw.isKeyPressed(KeyEvent.VK_S))
					{
						player1.attack(Direction.DOWN);
					}
					if(!collideDown)
					{
						playerY = playerY - 6;
						player1.setPosY(playerY);
					}
					
					player1.swapImg(Direction.DOWN);
					
					noMove = false;
				}
				
				if(StdDraw.isKeyPressed(KeyEvent.VK_A))
				{
					if(player1.getMana()>0)
						player1.getFire().setActive(true);
				}else if(!StdDraw.isKeyPressed(KeyEvent.VK_A))
					player1.getFire().setActive(false);
				

				//Wenn der Benutzer den Spieler nicht bewegt hat zeichne ein
				//festes Bild fuer die Spielfigur
				if(noMove)
					player1.draw();

				//setze die Kollisionswerte nach jedem Schleifendurchlauf wieder
				//zurueck
				collideRight = false;
				collideLeft = false;
				collideDown = false;
				collideUp = false;
				
				//setze Kollisionswerte zurueck
				for(int c=0;c<enemyList.size();c++)
					
				if(enemyList.get(c).isColDown() && enemyList.get(c).isColUp())
				{
					enemyList.get(c).setColDown(false);
				}else
					enemyList.get(c).setColUp(false);
			}
			//nach interner Aktualisierung zeichne das neue Feld ins Fenster
			StdDraw.show();
		}
	}
	
	
	
	/**
	public boolean checkRoof(char[][] feld, int i, int j)
	{
		if(i < rows - 1)
		{
			if(feld[i + 1][j] == 'x' || feld[i + 1][j] == 'X')
				return true;
			else 
				return false;
		}
		else
			return false;
	}
	
	public Roof getRoofTile(char[][] feld, int i, int j)
	{				
		int posX = j * 40 + 20;
		int posY = (40 * rows)-((i) * 40 + 20);
		if(i > 0 && i < rows && j > 0 && j < columns - 1)
		{
			if(checkRoof(feld, i - 1, j))
				if(checkRoof(feld, i, j - 1))
					if(checkRoof(feld, i, j + 1))
						return new Roof(posX, posY, 8);
					else
						return new Roof(posX, posY, 5);
				else
					if(checkRoof(feld, i, j + 1))
						return new Roof(posX, posY, 1);
					else
						return new Roof(posX, posY, 6);
			else
				if(checkRoof(feld, i, j - 1))
					if(checkRoof(feld, i, j + 1))
						return new Roof(posX, posY, 3);
					else
						return new Roof(posX, posY, 4);
				else
					if(checkRoof(feld, i, j + 1))
						return new Roof(posX, posY, 2);
					else
						return new Roof(posX, posY, 7);				
					
		}
		else
			return new Roof(posX, posY, 8);
	}*/
}
