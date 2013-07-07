import java.awt.Rectangle;

/**
 *  <i>Player</i>. Respaesentiert das Spieler-Objekt, welches die von Benutzer
 *  gesteuerte Spielfigut darstellt.
 */
public class Player extends Rectangle
{
	private static final long serialVersionUID = 1L;

	private Attack[] attacks = new Attack[4];
	private Magic[] magic = new Magic[4];
	Player_Inventory inventory;
	
	long anim;
	long delay;
	
	//x- und y-Position, an welcher der Spieler sich auf dem Spielfeld befindet.
	private double posX, posY;

	//Variablen zur Animation der Bewegung
	int swpL = 0;
	int swpR = 0;
	int swpU = 0;
	int swpD = 0;
	
	private int coins;
	private double health, maxHealth;
	private double mana, maxMana;
	private int lives;
	
	private String name;
	private int level;
	private double speed;
	
	private int attack;
	private int defense;
	private int spez;
	private int gena;
	
	private CheckPoint checkPoint;
	
	private boolean canMove;
	private String direction;
	
	private Weapon eqWep;
	private Armor eqArm;
	
    /**
     * Konstruktor eines Player-Objekts
     *
     * @param posX - die x-Position des Spielers zu Beginn (Rectangle)
     * @param posY - die y-Position des Spielers zu Beginn (Rectangle)
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
		
		//nur Testweise
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		setHealth(10);
		setMaxHealth(100);
		setMana(10);
		setMaxMana(100);
		lives = 3;
		speed = 125;
		level = 5;
		
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
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	}

	/**
     * Methode zum Zeichnen der Bewegungsanimation. Die Bilder wechseln periodisch
     * je nach Beweungsrichtung
     * 
     * @param direction - In welche Richtung bewegt sich der Spieler momentan
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
	
	
	public void increaseCoins(int amount)
	{
		coins = coins + amount;
	}
	
	public void decreaseCoins(int amount)
	{
		coins = coins - amount;
		
		if(coins < 0)
			coins = 0;
	}
	
	public int getCoins()
	{
		return coins;
	}
	
	public void setCoins(int amount)
	{
		coins = amount;
	}
	
	public void increaseHealth(double amount)
	{
		health = health + amount;
		
		if(health > maxHealth)
			health = maxHealth;
	}
	
	public void decreaseHealth(double amount)
	{
		health = health - amount;
		
		if(health < 0)
			health = 0;
	}
	
	public double getHealth()
	{
		return health;
	}
	
	public void setHealth(double amount)
	{
		health = amount;
	}
	
	public void increaseMana(double amount)
	{
		mana = mana + amount;
		
		if(mana > maxMana)
			mana = maxMana;
	}
	
	public void decreaseMana(double amount)
	{
		mana = mana - amount;
		
		if(mana < 0)
			mana = 0;
	}
	
	public double getMana()
	{
		return mana;
	}
	
	public void setMana(double amount)
	{
		mana = amount;
	}
	
	public void increaseLives(int amount)
	{
		lives = lives + amount;
	}
	
	public void decreaseLives(int amount)
	{
		lives = lives - amount;
		
		if(lives < 0)
			mana = 0;
	}
	
	public int getLives()
	{
		return lives;
	}
	
	public void setLives(int amount)
	{
		lives = amount;
	}
	
	public void increaseSpeed(int amount)
	{
		speed = speed + amount;
	}
	
	public void decreaseSpeed(int amount)
	{
		speed = speed - amount;
		
		if(speed < 2)
			speed = 2;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int amount)
	{
		speed = amount;
	}
	
	public void levelUp()
	{
		level++;
		
		attack = attack + 5;
		defense = defense + 5;
		spez = spez + 4;
		gena = gena + 4;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setMaxHealth(int amount)
	{
		maxHealth = amount;
	}
	
	public double getMaxHealth()
	{
		return maxHealth;
	}
	
	public void setMaxMana(int amount)
	{
		maxMana = amount;
	}
	
	public double getMaxMana()
	{
		return maxMana;
	}
	
	//-------------------------------------------------------------------
	
	public void setCheckPoint(int level, double posX, double posY)
	{
		checkPoint = new CheckPoint(level, posX, posY);
	}
	
	public CheckPoint getCheckPoint()
	{
		return checkPoint;
	}
	
	public int getCheckPointLevel()
	{
		if(checkPoint != null)
			return checkPoint.getLevel();
		else
			return -1;
	}
	
	public double getCheckPointPosX()
	{
		if(checkPoint != null)
			return checkPoint.getPosX();
		else
			return -1;
	}
	
	public double getCheckPointPosY()
	{
		if(checkPoint != null)
			return checkPoint.getPosY();
		else
			return -1;
	}
	
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
	
	public void stop()
	{
		this.canMove = false;
	}
	
	public void go()
	{
		this.canMove = true;
	}
	
	public boolean canMove()
	{
		return canMove;
	}
	
	public void setDiretion(String direction)
	{
		this.direction = direction;
	}
	
	//-------------------------------------------------------------------
	
	public void setPlayerName(String name)
	{
		this.name = name;
	}
	
	public String getPlayerName()
	{
		return name;
	}
	
	//-------------------------------------------------------------------
	
	public int getAttackCount()
	{
		int count = 0;
		
		for(int i = 0; i < 4; i++)
			if(attacks[i] != null)
				count ++;
		
		return count;
	}
	
	public Attack getAttack(int attack)
	{
		return attacks[attack - 1];
	}
	
	public int getMagicCount()
	{
		int count = 0;
		
		for(int i = 0; i < 4; i++)
			if(magic[i] != null)
				count ++;
		
		return count;
	}
	
	public Magic getMagic(int attack)
	{
		return magic[attack - 1];
	}
	
	//------------------------------------------------------------------------------------------

	public void increaseAtt(int amount)
	{
		this.attack += amount;
	}

	public void decreaseAtt(int amount)
	{
		this.attack -= amount;
	}
	
	public int getAtt()
	{
		return attack;
	}
	
	public void increaseDef(int amount)
	{
		this.defense += amount;
	}

	public void decreaseDef(int amount)
	{
		this.defense -= amount;
	}
	
	public int getDef()
	{
		return defense;
	}
	
	public void increaseSpez(int amount)
	{
		this.spez += amount;
	}

	public void decreaseSpez(int amount)
	{
		this.spez -= amount;
	}
	
	public int getSpez()
	{
		return spez;
	}
	
	public void increaseGena(int amount)
	{
		this.gena += amount;
	}

	public void decreaseGena(int amount)
	{
		this.gena -= amount;
	}
	
	public int getGena()
	{
		return gena;
	}
	
	//------------------------------------------------------------------------------------------
	
	public void handleMagic(Magic magic)
	{
		switch(magic.getEffect())
		{
		case "none": break;
		case "vert.": this.increaseDef((int)magic.getStrength()); break;
		case "angr.": this.increaseAtt((int)magic.getStrength()); break;
		case "spez": this.increaseSpez((int)magic.getStrength()); break;
		case "gena": this.increaseGena((int)magic.getStrength()); break;
		
		default: break;
		}
	}
	
	public void handleAttack(Attack attack)
	{
		switch(attack.getEffect())
		{
		case "none": break;
		case "vert.": this.increaseDef((int)attack.getStrength()); break;
		case "angr.": this.increaseAtt((int)attack.getStrength()); break;
		case "spez": this.increaseSpez((int)attack.getStrength()); break;
		case "gena": this.increaseGena((int)attack.getStrength()); break;
		
		default: break;
		}
	}
	
	//------------------------------------------------------------------------------------------
	
	public void equipWeapon(Weapon wep)
	{
		this.eqWep = wep;
		
		this.increaseAtt(wep.getBonus());
	}
	
	public void unequipWeapon()
	{
		if(this.eqWep != null)
		{
			this.decreaseAtt(eqWep.getBonus());
			
			this.eqWep = new Weapon_Faust();
		}
	}
	
	public Weapon getEquippedWeapon()
	{
		return this.eqWep;
	}
	
	public void equipArmor(Armor arm)
	{
		this.eqArm = arm;
		
		this.increaseDef(arm.getBonus());
	}
	
	public void unequipArmor()
	{
		if(this.eqArm != null)
		{
			this.decreaseDef(eqArm.getBonus());
			
			this.eqArm = new Armor_None();
		}
	}
	
	public Armor getEquippedArmor()
	{
		return this.eqArm;
	}
}
