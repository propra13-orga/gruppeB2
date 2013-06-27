import java.awt.Rectangle;

/**
 *  <i>Player</i>. Respaesentiert das Spieler-Objekt, welches die von Benutzer
 *  gesteuerte Spielfigut darstellt.
 */
public class Player extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
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
	
	private CheckPoint checkPoint;
	
	private boolean canMove;
	private String direction;
	
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
		
		setHealth(100);
		setMaxHealth(100);
		setMana(0);
		setMaxMana(50);
		lives = 3;
		speed = 125;
		level = 1;
		
		name = "Player";
		
		canMove = true;
		direction = Direction.DOWN;
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
}
