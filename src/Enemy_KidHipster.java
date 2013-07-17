import java.awt.Rectangle;


public class Enemy_KidHipster extends Enemy
{
	private static final long serialVersionUID = 1L;

	long delay, anim;
	
	final double SPEED = 0.02;
	
	private Attack[] attacks = new Attack[]
			{
			Attacks.kidhipster_smartphone,
			Attacks.kidhipster_metrobarriere
			};
	
	private Magic[] magics = new Magic[]
			{
			Attacks.kidhipster_obey
			};
	
	private final String AVATAR = "images/enemy/kid_hipster/avatar.png";	
	
	private final String [][] DIALOG = new String[][]
			{
			{"Du Fashiongurke sieht ja", "lächerlich aus! Geh", "lieber in nen Keller!", "                                      weiter mit [e]"},
			{"Was?! Du bist ja immernoch", "hier!", "COME AT ME BRO!", "                                      weiter mit [e]"}
			};
	
	private int currentPage = 0;
	
	private double posX, posY;

	//Variablen zur Animation der Bewegung
	int swpL = 0;
	int swpR = 0;
	int swpU = 0;
	int swpD = 0;
	
	private final String DIRECTION;
	private final int LEVEL;
	
	public Enemy_KidHipster(int posX, int posY, String direction, long delay)
	{
		super(posX, posY, 40, 40);
		
		this.delay = delay;
		
		this.posX = posX;
		this.posY = posY;
		
		this.DIRECTION = direction;
		this.LEVEL = (int)(Math.random() * ((5 - 1) + 1) + 1);
		
		this.maxHealth = 50 + 15 * LEVEL;
		this.health = maxHealth;
		this.maxMana = 20 + 10 * LEVEL;
		this.mana = maxMana;
		
		this.defense = LEVEL * 5;
		this.attack = LEVEL * 5;
		this.spez = LEVEL * 2;
		this.gena = LEVEL * 2;
		
		this.xp = LEVEL * 20;
		this.gold = LEVEL * 50 + (int)(Math.random() * ((50 - 10) + 10) + 10);

		this.attacks[0].setStrength(20);
		this.attacks[1].setStrength(10);
		this.magics[0].setStrength(30);
	}
	
	public Enemy_KidHipster(int posX, int posY, String direction, int level, long delay)
	{
		super(posX, posY, 40, 40);
		
		this.delay = delay;
		
		this.posX = posX;
		this.posY = posY;
		
		this.DIRECTION = direction;
		this.LEVEL = level;
		
		this.maxHealth = 50 + 15 * LEVEL;
		this.health = maxHealth;
		this.maxMana = 20 + 10 * LEVEL;
		this.mana = maxMana;
		
		this.defense = LEVEL * 5;
		this.attack = LEVEL * 5;
		this.spez = LEVEL * 2;
		this.gena = LEVEL * 2;
		
		this.xp = LEVEL * 20;
		this.gold = LEVEL * 50 + (int)(Math.random() * ((50 - 10) + 10) + 10);
	}

	public void drawImg() 
	{
		switch(DIRECTION)
		{
		case Direction.DOWN: 
			StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_down_2.png"); 
			break;
		case Direction.UP: 
			StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_up_2.png"); 
			break;
		case Direction.LEFT: 
			StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_left_2.png"); 
			break;
		case Direction.RIGHT: 
			StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_right_2.png"); 
			break;
		}
	}

	@Override
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
	
	
	public String getAvatar() 
	{
		return AVATAR;
	}
	
	public int getLevel()
	{
		return LEVEL;
	}
	
	public String[] startDialog()
	{
		currentPage = 0;
		return DIALOG[0];
	}
	
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
	
	public String[] getDialog() 
	{
		if(currentPage >= 0 && currentPage < DIALOG.length)
			return DIALOG[currentPage];
		else
			return null;
	}
	
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
	
	public void swapImg(long delta)
	{		anim = anim + (delta/1000000);
	
		if(anim > delay)
		{
			anim = 0;
			
		if(DIRECTION.equals(Direction.LEFT))
		{
			if(swpL < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_left_1.png");
				StdDraw.picture(posX, posY + 26, "images/enemy/inRange.png");
				swpL++;
			}
			else if(swpL < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_left_2.png");
				swpL++;				
			}
			else if(swpL < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_left_3.png");
				swpL++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_left_2.png");
				swpL++;
				
				if(swpL == 1999)
					swpL = 0;					
			}
		}
		else if(DIRECTION.equals(Direction.RIGHT))
		{
			if(swpR < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_right_1.png");
				swpR++;
			}
			else if(swpR < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_right_2.png");
				swpR++;				
			}
			else if(swpR < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_right_3.png");
				swpR++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_right_2.png");
				swpR++;
				
				if(swpR == 1999)
					swpR = 0;					
			}
		}
		else if(DIRECTION.equals(Direction.UP))
		{
			if(swpU < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_up_1.png");
				swpU++;
			}
			else if(swpU < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_up_2.png");
				swpU++;				
			}
			else if(swpU < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_up_3.png");
				swpU++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_up_2.png");
				swpU++;
				
				if(swpU == 1999)
					swpU = 0;					
			}
		}
		else if(DIRECTION.equals(Direction.DOWN))
		{
			if(swpD < 500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_down_1.png");
				swpD++;
			}
			else if(swpD < 1000)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_down_2.png");
				swpD++;				
			}
			else if(swpD < 1500)
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_down_3.png");
				swpD++;				
			}				
			else
			{
				StdDraw.picture(posX, posY, "images/enemy/kid_hipster/kid_hipster_down_2.png");
				swpD++;
				
				if(swpD == 1999)
					swpD = 0;					
			}
		}	

		StdDraw.picture(posX, posY + 26, "images/enemy/inRange.png");
		
		}
	}
	
	public String toString()
	{
		return "kid_hipster";
	}
	
	public String getName()
	{
		return "Kid Hipster";
	}
	
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
	
	public Attack getEnemyAttack()
	{
		double att= Math.random();

		if(att < 0.75)
			return this.attacks[0];
		else
			return this.attacks[1];
	}
	
	public Magic getEnemyMagic()
	{
		return this.magics[0];
	}
}
