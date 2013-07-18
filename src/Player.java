import java.awt.Rectangle;

/**
 *  Respaesentiert das Spieler-Objekt, welches die von Benutzer
 *  gesteuerte Spielfigut darstellt.
 */
public class Player extends Rectangle
{
	private static final long serialVersionUID = 1L;

	/**
	 * Attacken des Spielers (max. Vier)
	 */
	private Attack[] attacks = new Attack[4];
	/**
	 * Zauber des Spielers (max. Vier)
	 */
	private Magic[] magic = new Magic[4];
	/**
	 * Inventar des Spielers
	 */
	Player_Inventory inventory;
	
	/**
	 * Hilfsvariable zur fluessigen Animation
	 */
	long anim;
	/**
	 * Hilfsvariable zur fluessigen Animation
	 */
	long delay;

	/**
	 * Speichert die x- bzw. y-Position auf dem Spielfeld
	 */
	private double posX, posY;

	/**
	 * Variablen zur Animation der Spielfigur
	 */
	int swpL = 0;
	/**
	 * Variablen zur Animation der Spielfigur
	 */
	int swpR = 0;
	/**
	 * Variablen zur Animation der Spielfigur
	 */
	int swpU = 0;
	/**
	 * Variablen zur Animation der Spielfigur
	 */
	int swpD = 0;
	
	/**
	 * Speichert die Anzahl an Muenzen
	 */
	private int coins;
	/**
	 * Speichert die momentanen sowie die maximalen Lebenspunkte
	 */
	private double health, maxHealth;
	/**
	 * Speichert die momentanen sowie die maximalen Manapunkte
	 */
	private double mana, maxMana;
	/**
	 * Speichert die Leben des Spielers
	 */
	private int lives;
	
	/**
	 * Speichert den Namen des Spielers
	 */
	private String name;
	/**
	 * Speichert das Level des Spielers
	 */
	private int level;
	/**
	 * Speichert die Erfahrungspunkte des Spielers
	 */
	private int XP;
	
	/**
	 * Speichert die Bewegungsgeschwindigkeit
	 */
	private double speed;
	
	/**
	 * Speichert das Attribut Attack und tempAtt (temporaere Attributaenderung in Kaempfen)
	 */
	double attack, tempAtt;
	/**
	 * Speichert das Attribut Def und tempDef (temporaere Attributaenderung in Kaempfen)
	 */
	double defense, tempDef;
	/**
	 * Speichert das Attribut Spez und tempSpez (temporaere Attributaenderung in Kaempfen)
	 */
	double spez, tempSpez;
	/**
	 * Speichert das Attribut Gena und tempGena (temporaere Attributaenderung in Kaempfen)
	 */
	double gena, tempGena;
	
	/**
	 * Speichert einen moeglichen Speicherpunkt
	 */
	private CheckPoint checkPoint;
	
	/**
	 * Gibt zurueck, ob der Spieler sich bewegen kann
	 */
	private boolean canMove;
	/**
	 * Gibt die Blickrichtung des Spielers zurueck
	 */
	private String direction;
	
	/**
	 * Speichert die moeglicherweise ausgereustete Waffe
	 */
	private Weapon eqWep;
	/**
	 * Speichert die moeglicherweise ausgereustete Ruestung
	 */
	private Armor eqArm;
	
    /**
     * Konstruktor eines Player-Objekts
     *
     * @param posX - die x-Position des Spielers zu Beginn (Rectangle)
     * @param posY - die y-Position des Spielers zu Beginn (Rectangle)
     * @param delay - Hilfsvariable zur fluessigen Animation
     */
	public Player(int posX, int posY, long delay)
	{
		//Das Player-Objekt ist ein Rectangle-Objekt der Groesse 26x26 
		//(Etwas kleiner als ein Block fuer flexibelere Bewegung). Entsprechend
		//wird die Position des Rectangles etwas versetzt angegeben
		super(posX + 3, posY - 6, 30, 26);
		
		this.delay = delay;
		
		this.posX = posX;
		this.posY = posY;

		canMove = true;
		direction = Direction.DOWN;
		
		this.eqWep = new Weapon_Faust();
		this.eqArm = new Armor_None();
		
		tempAtt = tempDef = tempSpez = tempGena = 0;
		
		setMaxHealth(100);
		setHealth(maxHealth);
		setMana(0);
		setMaxMana(100);
		
		lives = 3;
		
		speed = 125;
		
		XP = 0;
		level = 1;
		
		name = "Player";
		
		inventory = new Player_Inventory(this);

		attacks[0] = Attacks.schwert_hieb;
		attacks[1] = Attacks.schild_block;
		attacks[2] = null;
		attacks[3] = null;
		
		magic[0] = Attacks.magie_pfeil;
		magic[1] = Attacks.magie_ruestung;
		magic[2] = null;
		magic[3] = null;

		attacks[0].setStrength(25);
		attacks[1].setStrength(10);
		magic[0].setStrength(40);
		magic[1].setStrength(5);
		
		
		attack = 10;
		defense = 10;
		spez = 8;
		gena = 8;
	}

	/**
     * Methode zum Zeichnen der Bewegungsanimation. Die Bilder wechseln periodisch
     * je nach Beweungsrichtung
     * 
     * @param direction - In welche Richtung bewegt sich der Spieler momentan
     * @param delta - Hilfsvariable zur fluessigen Animation
     */
	public void swapImg(String direction, long delta)
	{
		anim = anim + (delta/1000000);
		
		if(anim > delay)
		{
			anim = 0;
			
		if(direction.equalsIgnoreCase(Direction.LEFT))
		{
			if(swpL < 5)
			{
				StdDraw.picture(posX, posY, "images/player/player_left_"+1+".png");
				swpL++;
			}
			else if(swpL < 10)
			{
				StdDraw.picture(posX, posY, "images/player/player_left_"+2+".png");
				swpL++;				
			}
			else if(swpL < 15)
			{
				StdDraw.picture(posX, posY, "images/player/player_left_"+3+".png");
				swpL++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/player/player_left_"+2+".png");
				swpL++;
				
				if(swpL == 19)
					swpL = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.RIGHT))
		{
			if(swpR < 5)
			{
				StdDraw.picture(posX, posY, "images/player/player_right_"+1+".png");
				swpR++;
			}
			else if(swpR < 10)
			{
				StdDraw.picture(posX, posY, "images/player/player_right_"+2+".png");
				swpR++;				
			}
			else if(swpR < 15)
			{
				StdDraw.picture(posX, posY, "images/player/player_right_"+3+".png");
				swpR++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/player/player_right_"+2+".png");
				swpR++;
				
				if(swpR == 19)
					swpR = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.UP))
		{
			if(swpU < 5)
			{
				StdDraw.picture(posX, posY, "images/player/player_up_"+1+".png");
				swpU++;
			}
			else if(swpU < 10)
			{
				StdDraw.picture(posX, posY, "images/player/player_up_"+2+".png");
				swpU++;				
			}
			else if(swpU < 15)
			{
				StdDraw.picture(posX, posY, "images/player/player_up_"+3+".png");
				swpU++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/player/player_up_"+2+".png");
				swpU++;
				
				if(swpU == 19)
					swpU = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.DOWN))
		{
			if(swpD < 5)
			{
				StdDraw.picture(posX, posY, "images/player/player_down_"+1+".png");
				swpD++;
			}
			else if(swpD < 10)
			{
				StdDraw.picture(posX, posY, "images/player/player_down_"+2+".png");
				swpD++;				
			}
			else if(swpD < 15)
			{
				StdDraw.picture(posX, posY, "images/player/player_down_"+3+".png");
				swpD++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/player/player_down_"+2+".png");
				swpD++;
				
				if(swpD == 19)
					swpD = 0;					
			}
		}
		
		this.direction = direction;
		}
	}
	
	 /**
     * Methode zum Zeichnen des Players, wenn er sich nicht bewegt
     */
	public void draw()
	{
		if(direction.equals(Direction.DOWN))
			StdDraw.picture(posX, posY, "images/player/player_down_"+2+".png");
		else if(direction.equals(Direction.UP))
			 StdDraw.picture(posX, posY, "images/player/player_up_"+2+".png");
		else if(direction.equals(Direction.LEFT))
			 StdDraw.picture(posX, posY, "images/player/player_left_"+2+".png");
		else if(direction.equals(Direction.RIGHT))
			 StdDraw.picture(posX, posY, "images/player/player_right_"+2+".png");
		else
			 StdDraw.picture(posX, posY, "images/player/player_down_"+2+".png");
	}

	 /**
     * Methode, welche die Position des Player-Objekts und des Rectangles setzt
     * 
     * @param posX - x-Position des Spielers
     * @param posY - y-Position des Spielers
     */
	public void setPos(double posX, double posY)
	{
		this.posX = posX;
		this.posY = posY;
		
		this.setLocation((int)posX + 3, (int)posY - 6);
	}

	 /**
     * Methode, welche die x-Position des Player-Objekts und des Rectangles setzt
     *      
     * @param posX - x-Position des Spielers
     */
	public void setPosX(double posX)
	{
		this.posX = posX;
		this.setLocation((int)posX + 3, (int)posY - 6);
	}

	 /**
     * Methode, welche die y-Position des Player-Objekts und des Rectangles setzt
     *
     * @param posY - y-Position des Spielers
     */
	public void setPosY(double posY)
	{
		this.posY = posY;
		this.setLocation((int)posX + 3, (int)posY - 6);
	}
	
	 /**
     * Methode, welche die x-Position des Spielers zurueckgibt
     *
     * @return x-Position des Spieler-Objekts
     */
	public double getPosX()
	{
		return posX;
	}
	
	/**
    * Methode, welche die y-Position des Spielers zurueckgibt
    *
    * @return y-Position des Spieler-Objekts
    */
	public double getPosY()
	{
		return posY;
	}
	
	
	//-------------------------------------------------------------------
	
	/**
	 * Erhoeht die Muenzanzahl des Spielers
	 * @param amount - Erhoehe Muenzen um
	 */
	public void increaseCoins(int amount)
	{
		coins = coins + amount;
	}

	/**
	 * Verkleiner die Muenzanzahl des Spielers
	 * @param amount - Verkleiner Muenzen um
	 */
	public void decreaseCoins(int amount)
	{
		coins = coins - amount;
		
		if(coins < 0)
			coins = 0;
	}

	/**
	 * Gibt die Muenzanzahl zurueck
	 * @return Muenzanzahl
	 */
	public int getCoins()
	{
		return coins;
	}
	
	/**
	 * Setzt die Muenzanzahl
	 * @param amount - Setzt Muenzanzahl auf
	 */
	public void setCoins(int amount)
	{
		coins = amount;
	}

	/**
	 * Erhoeht die LP des Spielers
	 * @param amount - Erhoehe LP um
	 */
	public void increaseHealth(double amount)
	{
		health = health + amount;
		
		if(health > maxHealth)
			health = maxHealth;
	}

	/**
	 * Verkleiner die LP des Spielers
	 * @param amount - Verkleiner LP um
	 */
	public void decreaseHealth(double amount)
	{
		health = health - amount;
		
		if(health < 0)
			health = 0;
	}
	
	/**
	 * Gibt die LP zurueck
	 * @return Lebenspunkte
	 */
	public double getHealth()
	{
		return health;
	}
	
	/**
	 * Setzt die LP
	 * @param amount - Setzt LP auf
	 */
	public void setHealth(double amount)
	{
		health = amount;
	}

	/**
	 * Erhoeht die MP des Spielers
	 * @param amount - Erhoehe MP um
	 */
	public void increaseMana(double amount)
	{
		mana = mana + amount;
		
		if(mana > maxMana)
			mana = maxMana;
	}

	/**
	 * Verkleiner die MP des Spielers
	 * @param amount - Verkleiner MP um
	 */
	public void decreaseMana(double amount)
	{
		mana = mana - amount;
		
		if(mana < 0)
			mana = 0;
	}
	
	/**
	 * Gibt die MP zurueck
	 * @return Manapunkte
	 */
	public double getMana()
	{
		return mana;
	}
	
	/**
	 * Setzt die MP
	 * @param amount - Setzt MP auf
	 */
	public void setMana(double amount)
	{
		mana = amount;
	}

	/**
	 * Erhoeht die Leben des Spielers
	 * @param amount - Erhoehe Leben um
	 */
	public void increaseLives(int amount)
	{
		lives = lives + amount;
	}

	/**
	 * Verkleiner die Leben des Spielers
	 * @param amount - Verkleiner Leben um
	 */
	public void decreaseLives(int amount)
	{
		lives = lives - amount;
		
		if(lives < 0)
			mana = 0;
	}
	
	/**
	 * Gibt die Leben zurueck
	 * @return Leben
	 */
	public int getLives()
	{
		return lives;
	}
	
	/**
	 * Setzt die Leben
	 * @param amount - Setzt Leben auf
	 */
	public void setLives(int amount)
	{
		lives = amount;
	}

	/**
	 * Erhoeht die Geschwindigkeit des Spielers
	 * @param amount - Erhoehe Geschwindigkeit um
	 */
	public void increaseSpeed(int amount)
	{
		speed = speed + amount;
	}

	/**
	 * Verkleiner die Geschwindigkeit des Spielers
	 * @param amount - Verkleiner Geschwindigkeit um
	 */
	public void decreaseSpeed(int amount)
	{
		speed = speed - amount;
		
		if(speed < 2)
			speed = 2;
	}
	
	/**
	 * Gibt die Geschwindigkeit zurueck
	 * @return Geschwindigkeit
	 */
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * Setzt die Geschwindigkeit
	 * @param amount - Setzt Geschwindigkeit auf
	 */
	public void setSpeed(int amount)
	{
		speed = amount;
	}
	
	/**
	 * Prueft, ob die XP ausreichen, um ein Level aufzusteigen
	 * @return <b>true</b> wenn die XP zum Leveln reichen, <b>false</b> sonst
	 */
	public boolean canLevelUp()
	{
		if(this.XP >= this.level * 100)
			return true;
		else
			return false;
	}
	
	/**
	 * Level-UP Methode erhoeht das Level des Spielers um Eins
	 */
	public void levelUp()
	{
		XP = XP - level * 100;
		
		level++;
		
		attack = attack + 5;
		defense = defense + 5;
		spez = spez + 4;
		gena = gena + 4;

		setMaxHealth((int)(getMaxHealth() + 20));
		setMaxMana((int)(getMaxMana() + 20));

		setHealth(getMaxHealth());
		setMana(getMaxMana());
	}
	
	/**
	 * Gibt das Level des Spieler zurueck
	 * @return Spieler-Level
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gibt die XP des Spieler zurueck
	 * @return XP des Spielers
	 */	
	public int getXP()
	{
		return XP;
	}

	/**
	 * Erhoeht die XP des Spielers
	 * @param amount - Erhoehe XP um
	 */
	public void increaseXP(int amount)
	{
		XP += amount;
	}

	/**
	 * Verkleiner die XP des Spielers
	 * @param amount - Verkleiner XP um
	 */
	public void decreaseXP(int amount)
	{
		XP -= amount;
		
		if(XP <= 0)
			XP = 0;
	}
	
	/**
	 * Setzt die Maximalen LP
	 * @param amount - Setzt maxmalen LP auf
	 */
	public void setMaxHealth(int amount)
	{
		maxHealth = amount;
	}
	
	/**
	 * Gibt die maximalen LP des Spieler zurueck
	 * @return max. LP des Spielers
	 */	
	public double getMaxHealth()
	{
		return maxHealth;
	}
	
	/**
	 * Setzt die Maximalen MP
	 * @param amount - Setzt maximalen MP auf
	 */
	public void setMaxMana(int amount)
	{
		maxMana = amount;
	}
	
	/**
	 * Gibt die maximalen MP des Spieler zurueck
	 * @return max. MP des Spielers
	 */	
	public double getMaxMana()
	{
		return maxMana;
	}
	
	//-------------------------------------------------------------------
	
	/**
	 * Setzt den Speicherpunkt des Spielers
	 * @param level - Level des Speicherpunkts
	 * @param posX - x-Position des Speicherpunkts auf dem Spielfeld
	 * @param posY - y-Position des Speicherpubkts auf dem Spielfeld
	 */
	public void setCheckPoint(int level, double posX, double posY)
	{
		checkPoint = new CheckPoint(level, posX, posY);
	}
	
	/**
	 * Gibt den Speicherpunkt des Spielers zurueck
	 * @return Speicherpunkt
	 */
	public CheckPoint getCheckPoint()
	{
		return checkPoint;
	}
	
	/**
	 * Gibt das Level zurueck, in dem der Speicherpunkt sich befindet
	 * @return Level des Speicherpunkts
	 */
	public int getCheckPointLevel()
	{
		if(checkPoint != null)
			return checkPoint.getLevel();
		else
			return -1;
	}
	
	/**
	 * Gibt die x-Pos zurueck, auf dem der Speicherpunkt sich befindet
	 * @return x-Pos des Speicherpunkts
	 */
	public double getCheckPointPosX()
	{
		if(checkPoint != null)
			return checkPoint.getPosX();
		else
			return -1;
	}
	
	/**
	 * Gibt die y-Pos zurueck, auf dem der Speicherpunkt sich befindet
	 * @return y-Pos des Speicherpunkts
	 */
	public double getCheckPointPosY()
	{
		if(checkPoint != null)
			return checkPoint.getPosY();
		else
			return -1;
	}
	
	/**
	 * Gibt die x- und y-Pos zurueck, auf dem der Speicherpunkt sich befindet
	 * @return x- und y-Pos des Speicherpunkts
	 */
	public int[] getCheckPointPos()
	{
		int[] pos = new int[2];
		
		if(checkPoint != null)
		{
			pos[0] = (int)checkPoint.getPosX();
			pos[1] = (int)checkPoint.getPosY();
			
			return pos;
		}
		else
			return null;
	}
	
	//-------------------------------------------------------------------
	
	/**
	 * Stoppt die Bewegung des Spielers
	 */
	public void stop()
	{
		this.canMove = false;
	}
	
	/**
	 * Setzt die Bewegung des Spielers fort
	 */
	public void go()
	{
		this.canMove = true;
	}
	
	/**
	 * Gibt zurueck, ob die Bewegung des Spieler gestoppt wurde oder nicht
	 * @return Kann sich der Spieler bewegen
	 */
	public boolean canMove()
	{
		return canMove;
	}
	
	/**
	 * Setzt die Blickrichtung des Spielers
	 * @param direction - Blickrichtung des Spielers
	 */
	public void setDiretion(String direction)
	{
		this.direction = direction;
	}
	
	//-------------------------------------------------------------------
	
	/**
	 * Setzt den Namen des Spielers
	 * @param name - Zu setzendes Spielername
	 */
	public void setPlayerName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gibt den Spielernamen zurueck
	 * @return Spielernamen
	 */
	public String getPlayerName()
	{
		return name;
	}
	
	//-------------------------------------------------------------------
	
	/**
	 * Gibt die Anzahl der Attacks zurueck
	 * @return Attackanzahl
	 */
	public int getAttackCount()
	{
		int count = 0;
		
		for(int i = 0; i < 4; i++)
			if(attacks[i] != null)
				count ++;
		
		return count;
	}
	
	/**
	 * Gibt die Attack an der Position <i>attack</i> zurueck
	 * @param attack - Position der Attacke
	 * @return Attacke an Position <i>attack</i>
	 */
	public Attack getAttack(int attack)
	{
		return attacks[attack - 1];
	}
	
	/**
	 * Gibt die Anzahl der Magic zurueck
	 * @return Magicanzahl
	 */
	public int getMagicCount()
	{
		int count = 0;
		
		for(int i = 0; i < 4; i++)
			if(magic[i] != null)
				count ++;
		
		return count;
	}
	
	/**
	 * Gibt die Magic an der Position <i>attack</i> zurueck
	 * @param attack - Position der Magic
	 * @return Magic an Position <i>attack</i>
	 */
	public Magic getMagic(int attack)
	{
		return magic[attack - 1];
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Erhoeht das Attribut Attack
	 * @param amount - Erhoehe Attack um
	 */
	public void increaseAtt(int amount)
	{
		this.attack += amount;
	}

	/**
	 * Verkleiner das Attribut Attack
	 * @param amount - Verkleiner Attack um
	 */
	public void decreaseAtt(int amount)
	{
		this.attack -= amount;
	}
	
	/**
	 * Gibt das Attribut Attack zurueck
	 * @return Attribut Attack
	 */
	public int getAtt()
	{
		return (int)(attack + tempAtt);
	}
	
	/**
	 * Gibt das Attribut Attack ohne Waffenbonus zurueck
	 * @return Attribut Attack ohne Waffenboni
	 */
	public int getAttPure()
	{
		return (int)(attack - this.eqWep.getBonus());
	}

	/**
	 * Erhoeht das Attribut Def.
	 * @param amount - Erhoehe Def. um
	 */
	public void increaseDef(int amount)
	{
		this.defense += amount;
	}

	/**
	 * Verkleiner das Attribut Def.
	 * @param amount - Verkleiner Def. um
	 */
	public void decreaseDef(int amount)
	{
		this.defense -= amount;
	}
	
	/**
	 * Gibt das Attribut Def. zurueck
	 * @return Attribut Def.
	 */
	public int getDef()
	{
		return (int)(defense + tempDef);
	}
	
	/**
	 * Gibt das Attribut Defense ohne Armorbonus zurueck
	 * @return Attribut Defense ohne Armorboni
	 */
	public int getDefPure()
	{
		return (int)(defense - this.eqArm.getBonus());
	}

	/**
	 * Erhoeht das Attribut Spez
	 * @param amount - Erhoehe Spez um
	 */
	public void increaseSpez(int amount)
	{
		this.spez += amount;
	}

	/**
	 * Verkleiner das Attribut Spez
	 * @param amount - Verkleiner Spez um
	 */
	public void decreaseSpez(int amount)
	{
		this.spez -= amount;
	}
	
	/**
	 * Gibt das Attribut Spez zurueck
	 * @return Attribut Spez
	 */
	public int getSpez()
	{
		return (int)(spez + tempSpez);
	}

	/**
	 * Erhoeht das Attribut Gena
	 * @param amount - Erhoehe Gena um
	 */
	public void increaseGena(int amount)
	{
		this.gena += amount;
	}

	/**
	 * Verkleiner das Attribut Gena
	 * @param amount - Verkleiner Gena um
	 */
	public void decreaseGena(int amount)
	{
		this.gena -= amount;
	}
	
	/**
	 * Gibt das Attribut Gena zurueck
	 * @return Attribut Gena
	 */
	public int getGena()
	{
		return (int)(gena + tempGena);
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Behandelt den Effekt eines Zaubers und erhoeht die entsprechenden Attribute
	 * @param magic - Verwendeter Zauber
	 */
	public void handleMagic(Magic magic)
	{
		switch(magic.getEffect())
		{
		case "none": break;
		case "vert.": 
			if(tempDef <= 30)
				tempDef += (magic.getStrength()/200); 
			break;
		case "angr.":
			if(tempAtt <= 30)
				tempAtt += (magic.getStrength()/200); 
			break;
		case "spez": 
			if(tempSpez <= 25)
				tempSpez += (magic.getStrength()/200); 
			break;
		case "gena": 
			if(tempGena <= 20)
				tempGena += (magic.getStrength()/200); 
			break;
		
		default: break;
		}
	}
	
	/**
	 * Behandelt den Effekt einer Attacke und erhoeht die entsprechenden Attribute
	 * @param attack - Verwendete Attacke
	 */
	public void handleAttack(Attack attack)
	{
		switch(attack.getEffect())
		{
		case "none": break;
		case "vert.": 
			if(tempDef <= 30)
				tempDef += (attack.getStrength()/200); 
			break;
		case "angr.": 
			if(tempAtt <= 30)
				tempAtt += (attack.getStrength()/200); 
			break;
		case "spez": 
			if(tempSpez <= 25)
				tempSpez += (attack.getStrength()/200); 
			break;
		case "gena": 
			if(tempGena <= 20)
				tempGena += (attack.getStrength()/200); 
			break;
		
		default: break;
		}
	}
	
	/**
	 * Setzt die temporaeren Statusaenderungen zurueck
	 */
	public void resetTemps()
	{
		tempDef = tempAtt = tempSpez = tempGena = 0;
	}
	
	/**
	 * Prueft, ob der Spieler eine neue Attacke lernt (alle 3 Level eine neue Attacke)
	 * @return <b>true</b> wenn neue Attacke erlernt werden kann, <b>false</b> sonst
	 */
	public boolean learnsNewAttack()
	{
		if(this.level % 3 == 0 && this.level < 31)
			return true;
		else
			return false;
	}	

	/**
	 * Prueft, ob der Spieler einen neuen Zauber lernt (alle 5 Level einen neuen Zauber)
	 * @return <b>true</b> wenn neuer Zauber erlernt werden kann, <b>false</b> sonst
	 */
	public boolean learnsNewMagic()
	{
		if(this.level % 5 == 0 && this.level < 51)
			return true;
		else
			return false;
	}
	
	/**
	 * Der Spieler erlernt die Attacke att wenn moeglich
	 * @param att - Zu erlernende Attacke
	 */
	public void learnAttack(Attack att)
	{
		int index = this.getAttackCount();
		
		attacks[index] = att;
	}
	
	/**
	 * Der Spieler erlernt den Zauber att wenn moeglich
	 * @param att - Zu erlernender Zauber
	 */
	public void learnMagic(Magic att)
	{
		int index = this.getMagicCount();
		
		magic[index] = att;
	}
	
	//------------------------------------------------------------------------------------------
	
	/**
	 * Legt eine Waffe an
	 * @param wep - Auszuruestende Waffe
	 */
	public void equipWeapon(Weapon wep)
	{
		this.eqWep = wep;
		
		this.increaseAtt(wep.getBonus());
	}
	
	/**
	 * Legt die ausgereustete Waffe ab
	 */
	public void unequipWeapon()
	{
		if(this.eqWep != null)
		{
			this.decreaseAtt(eqWep.getBonus());
			
			this.eqWep = new Weapon_Faust();
		}
	}
	
	/**
	 * Gibt die momentan ausgereustete Waffe zurueck
	 * @return Ausgeruestete Waffe
	 */
	public Weapon getEquippedWeapon()
	{
		return this.eqWep;
	}
	
	/**
	 * Legt eien Ruestung an
	 * @param arm - Anzulegende Ruestung
	 */
	public void equipArmor(Armor arm)
	{
		this.eqArm = arm;
		
		this.increaseDef(arm.getBonus());
	}
	
	/**
	 * Left die ausgeruestete Ruestung ab
	 */
	public void unequipArmor()
	{
		if(this.eqArm != null)
		{
			this.decreaseDef(eqArm.getBonus());
			
			this.eqArm = new Armor_None();
		}
	}
	
	/**
	 * Gibt die momentan angelegte Ruestung zurueck
	 * @return Angelegte Ruestung
	 */
	public Armor getEquippedArmor()
	{
		return this.eqArm;
	}
}
