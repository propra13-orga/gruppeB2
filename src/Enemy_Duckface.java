import java.awt.Rectangle;

/**
 * Stellt den Gegner "AttentionWhoreKidHipster" dar. Erbt von der Oberklasse Enemy.
 * @author Mike Bechtel
 *
 */
public class Enemy_Duckface extends Enemy
{
	private static final long serialVersionUID = 1L;

	/**
	 * Hilftvariablen zur flussigen Animation
	 */
	long delay, anim;
	
	/**
	 * Geschwindigkeit, mit welcher sich der Feind ueber das Spielfeld bewegt
	 */
	final double SPEED = 0.02;
	
	/**
	 * Array, in dem die Attacken gespeichert werden, die dem Duckface
	 * zur Verfuegung stehen
	 */
	private Attack[] attacks = new Attack[]
			{
			Attacks.attention_geschrei,
			Attacks.attention_logic
			};
	
	/**
	 * Array, in dem die Zauber gespeichert werden, die dem Duckface
	 * zur Verfuegung stehen
	 */
	private Magic[] magics = new Magic[]
			{
			Attacks.duckface_duckface
			};
	
	/**
	 * Dateipfad der Avatars (fuer Spielfelddialog)
	 */
	private final String AVATAR = "images/enemy/duckface/avatar.png";	
	
	/**
	 * Array mit den Dialog-Seiten
	 */
	private final String [][] DIALOG = new String[][]
			{
			{"Hey lass uns ein Foto", "machen! Hehe!", "Duckface!!!", "                                      weiter mit [e]"},
			{"Quack, quack, quack!", "Ey du machst ja", "garnicht mit!!", "                                      weiter mit [e]"}
			};
	
	/**
	 * Speichert die momentan angezeige Dialog-Seite
	 */
	private int currentPage = 0;
	
	/**
	 * x- und y-Position des Gegners auf dem Spielfeld
	 */
	private double posX, posY;

	/**
	 * Hilfsvariable zur Animation der Bewegung
	 */
	int swpL = 0;
	/**
	 * Hilfsvariable zur Animation der Bewegung
	 */
	int swpR = 0;
	/**
	 * Hilfsvariable zur Animation der Bewegung
	 */
	int swpU = 0;
	/**
	 * Hilfsvariable zur Animation der Bewegung
	 */
	int swpD = 0;
	
	/**
	 * Speichert die Ausrichtung des Gegner auf dem Feld
	 */
	private final String DIRECTION;
	/**
	 * Speichert das Level des Gegners
	 */
	private final int LEVEL;
	
	
	/**
	 * Konstruktor einer Duckface
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param direction - Ausrichtung des Gegners auf dem Feld
	 * @param delay - Hilfsvariable zur fluessigen Animation
	 */
	public Enemy_Duckface(int posX, int posY, String direction, long delay)
	{
		super(posX, posY, 40, 40);
		
		this.delay = delay;
		
		this.posX = posX;
		this.posY = posY;
		
		this.DIRECTION = direction;
		this.LEVEL = (int)(Math.random() * ((10 - 4) + 4) + 4);
		
		this.maxHealth = 30 + 18 * LEVEL;
		this.health = maxHealth;
		this.maxMana = 15 * LEVEL;
		this.mana = maxMana;
		
		this.defense = LEVEL * 3;
		this.attack = LEVEL * 5;
		this.spez = LEVEL * 4.5;
		this.gena = LEVEL * 3;
		
		this.xp = LEVEL * 23;
		this.gold = LEVEL * 70 + (int)(Math.random() * ((60 - 10) + 10) + 10);

		this.type = Types.duckface;
		
		this.attacks[0].setStrength(20);
		this.attacks[1].setStrength(35);
		this.magics[0].setStrength(30);
	}
	
	/**
	 * Konstruktor einer Duckface
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param direction - Ausrichtung des Gegners auf dem Feld
	 * @param level - Level des Gegners
	 * @param delay - Hilfsvariable zur fluessigen Animation
	 */
	public Enemy_Duckface(int posX, int posY, String direction, int level, long delay)
	{
		super(posX, posY, 40, 40);
		
		this.delay = delay;
		
		this.posX = posX;
		this.posY = posY;
		
		this.DIRECTION = direction;
		this.LEVEL = level;
		
		this.maxHealth = 30 + 18 * LEVEL;
		this.health = maxHealth;
		this.maxMana = 15 * LEVEL;
		this.mana = maxMana;
		
		this.defense = LEVEL * 3;
		this.attack = LEVEL * 5;
		this.spez = LEVEL * 4.5;
		this.gena = LEVEL * 3;
		
		this.xp = LEVEL * 23;
		this.gold = LEVEL * 70 + (int)(Math.random() * ((60 - 10) + 10) + 10);

		this.type = Types.duckface;
		
		this.attacks[0].setStrength(20);
		this.attacks[1].setStrength(35);
		this.magics[0].setStrength(30);
	}

	/**
	 * Zeichnet das Bild des Gegners, wenn er sich nicht bewegt
	 */
	public void drawImg() 
	{
		switch(DIRECTION)
		{
		case Direction.DOWN: 
			StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_down_2.png"); 
			break;
		case Direction.UP: 
			StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_up_2.png"); 
			break;
		case Direction.LEFT: 
			StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_left_2.png"); 
			break;
		case Direction.RIGHT: 
			StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_right_2.png"); 
			break;
		}
	}

	/**
	 * Prueft, ob der Spieler sich in Angriffslinie befindet
	 * @param player - Player, fuer den die Angriffslinie geprueft wird
	 */
	public boolean playerInLine(Player player) 
	{
		double playerPosX = player.getCenterX();
		double playerPosY = player.getCenterY();
		
		if(playerPosX < this.getCenterX() + 15 && playerPosX > this.getCenterX() - 15 && Math.abs(this.getCenterY() - playerPosY) < 200)
			if(playerPosY < this.getCenterY())
				return DIRECTION.equals(Direction.DOWN);
			else
				return DIRECTION.equals(Direction.UP);
		
		if(playerPosY < this.getCenterY() + 15 && playerPosY > this.getCenterY() - 15 && Math.abs(this.getCenterX() - playerPosX) < 200)
			if(playerPosX < this.getCenterX())
				return DIRECTION.equals(Direction.LEFT);
			else
				return DIRECTION.equals(Direction.RIGHT);
		
		return false;
	}
	
	/**
	 * Methode, welche den Gegner sich zum Spieler bewegen laesst
	 * @param player - Spieler, zu dem sich der Gegner bewegt
	 * @param delta - Hilfsvariable zur Animation
	 */
	public void moveToPlayer(Player player, long delta)
	{
		switch(DIRECTION)
		{
		case Direction.DOWN: this.setPosY(posY - SPEED); break;
		case Direction.UP: this.setPosY(posY + SPEED); break;
		case Direction.LEFT: this.setPosX(posX - SPEED); break;
		case Direction.RIGHT: this.setPosX(posX + SPEED); break;
		}
		this.swapImg(delta);
	}
	
	/**
	 * Prueft die Kollision des Gegner mit einem anderen Rectangle
	 * @param rect - Rectangle, mit dem die Kollision gecheckt wird
	 */
	public int checkCollision(Rectangle rect) 
	{
		if(this.intersects(rect))
		{
			double pX = rect.getCenterX();
			double pY = rect.getCenterY();
			double fX = this.getCenterX();
			double fY = this.getCenterY();
			
			if(pX > fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_LEFT;
			if(pX < fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_RIGHT;
			if(pY > fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_DOWN;
			if(pY < fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_UP;
			else
				return Direction.NO_COLLISION;
		}
		else
			return Direction.NO_COLLISION;
	}
	
	//------------------------------------------------------------------------
	
	/**
	 * Gibt den Dateipfad des Avatars zurueck
	 */
	public String getAvatar() 
	{
		return AVATAR;
	}
	
	/**
	 * Gibt den Level des Gegeners zurueck
	 */
	public int getLevel()
	{
		return LEVEL;
	}
	
	/**
	 * Gibt den Startdialog (erste Seite) des Dialogs zurueck
	 */
	public String[] startDialog()
	{
		currentPage = 0;
		return DIALOG[0];
	}
	
	/**
	 * Gibt die Dialogseite <i>int</i> zurueck
	 * @param page - anzuzeigende Seite
	 */
	public String[] getDialog(int page) 
	{
		if(page <= DIALOG.length)
		{
			currentPage = page - 1;
			return DIALOG[page - 1];
		}
		else
			return null;
	}	
	
	/**
	 * Gibt den gesamten Dialog zurueck
	 */
	public String[] getDialog() 
	{
		if(currentPage >= 0 && currentPage < DIALOG.length)
			return DIALOG[currentPage];
		else
			return null;
	}
	
	/**
	 * Gibt die naechste Dialogseite zurueck
	 */
	public String[] getNextDialogPage()
	{
		if(currentPage < DIALOG.length)
		{
			currentPage++;
			return DIALOG[currentPage];
		}
		else
			return null;
	}
	
	/**
	 * Prueft, ob es eine weitere Dialogseite gibt
	 */
	public boolean hasNextPage()
	{
		return currentPage < DIALOG.length;
	}
	
	//------------------------------------------------------------------------
	 
	/**
     * Methode, welche die x-Position des Enemy-Objekts und des Rectangles setzt
     *
     * @param posX - x-Position des Enemy
     */
	public void setPosX(double posX)
	{
		this.posX = posX;
		this.setLocation((int)posX, (int)posY);
	}

	 /**
     * Methode, welche die y-Position des Enemy-Objekts und des Rectangles setzt
     *
     * @param posY - y-Position des Enemy
     */
	public void setPosY(double posY)
	{
		this.posY = posY;
		this.setLocation((int)posX, (int)posY);
	}
	
	 /**
     * Methode, welche die x-Position des Enemy zurueckgibt
     *
     * @return x-Position des Enemy-Objekts
     */
	public double getPosX()
	{
		return posX;
	}
	
	/**
    * Methode, welche die y-Position des Enemy zurueckgibt
    *
    * @return y-Position des Enemy-Objekts
    */
	public double getPosY()
	{
		return posY;
	}
	
	/**
	 * Methode, die die Bewegung des Gegner animiert
	 * @param delta - Hilfsvariable zur fluessigen Animation
	 */
	public void swapImg(long delta)
	{		anim = anim + (delta/1000000);
	
		if(anim > delay)
		{
			anim = 0;
			
		if(DIRECTION.equals(Direction.LEFT))
		{
			if(swpL < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_left_1.png");
				StdDraw.picture(posX, posY + 26, "images/enemy/inRange.png");
				swpL++;
			}
			else if(swpL < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_left_2.png");
				swpL++;				
			}
			else if(swpL < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_left_3.png");
				swpL++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_left_2.png");
				swpL++;
				
				if(swpL == 1999)
					swpL = 0;					
			}
		}
		else if(DIRECTION.equals(Direction.RIGHT))
		{
			if(swpR < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_right_1.png");
				swpR++;
			}
			else if(swpR < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_right_2.png");
				swpR++;				
			}
			else if(swpR < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_right_3.png");
				swpR++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_right_2.png");
				swpR++;
				
				if(swpR == 1999)
					swpR = 0;					
			}
		}
		else if(DIRECTION.equals(Direction.UP))
		{
			if(swpU < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_up_1.png");
				swpU++;
			}
			else if(swpU < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_up_2.png");
				swpU++;				
			}
			else if(swpU < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_up_3.png");
				swpU++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_up_2.png");
				swpU++;
				
				if(swpU == 1999)
					swpU = 0;					
			}
		}
		else if(DIRECTION.equals(Direction.DOWN))
		{
			if(swpD < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_down_1.png");
				swpD++;
			}
			else if(swpD < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_down_2.png");
				swpD++;				
			}
			else if(swpD < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_down_3.png");
				swpD++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/duckface/duckface_down_2.png");
				swpD++;
				
				if(swpD == 1999)
					swpD = 0;					
			}
		}	

		StdDraw.picture(posX, posY + 26, "images/enemy/inRange.png");
		
		}
	}
	
	/**
	 * Gibt eine String-Repraesentation des Gegners zurueck
	 */
	public String toString()
	{
		return "duckface";
	}
	
	/**
	 * Gibt den Namen des Gegners zur konkrekten Dialoganzeige zurueck
	 */
	public String getName()
	{
		return "Duckface";
	}
	
	/**
	 * Gibt die Ausrichtung des Gegners zurueck
	 */
	public String getDirection()
	{
		if(this.DIRECTION.equals(Direction.DOWN))
			return Direction.UP;
		if(this.DIRECTION.equals(Direction.UP))
			return Direction.DOWN;
		if(this.DIRECTION.equals(Direction.LEFT))
			return Direction.RIGHT;
		if(this.DIRECTION.equals(Direction.RIGHT))
			return Direction.LEFT;
		else
			return "Keine Richtung";
	}
	
	
	//----------------------------------------------------------------------------------------
	
	/**
	 * Gibt eine zufaellige Attacke zurueck, welche im Kampf benutzt wird
	 */
	public Attack getEnemyAttack()
	{
		double att= Math.random();

		if(att < 0.35)
			return this.attacks[0];
		else
			return this.attacks[1];
	}
	
	/**
	 * Gibt einen zufaelligen Zauber zuruek, welcher im Kapf benutzt werden kann
	 */
	public Magic getEnemyMagic()
	{
		return this.magics[0];
	}
}
