import java.awt.Rectangle;


public class KidHipster extends Enemy
{
	private static final long serialVersionUID = 1L;

	private final String AVATAR = "images/enemy/kid_hipster/avatar.png";
	
	private double posX, posY;

	//Variablen zur Animation der Bewegung
	int swpL = 0;
	int swpR = 0;
	int swpU = 0;
	int swpD = 0;
	
	private final String DIRECTION;
	private final int LEVEL;
	
	public KidHipster(int posX, int posY, String direction)
	{
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		
		this.DIRECTION = direction;
		this.LEVEL = (int)(Math.random() * ((5 - 1) + 1) + 1);
	}
	
	public KidHipster(int posX, int posY, String direction, int level)
	{
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		
		this.DIRECTION = direction;
		this.LEVEL = level;
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
		
		//System.out.println((playerPosY < this.getCenterY() + 20) && (playerPosY > this.getCenterY() - 20) );
		
		if(playerPosX < this.getCenterX() + 10 && playerPosX > this.getCenterX() - 10)
			if(playerPosY < this.getCenterY())
				return DIRECTION.equals(Direction.DOWN);
			else
				return DIRECTION.equals(Direction.UP);
		
		if(playerPosY < this.getCenterY() + 10 && playerPosY > this.getCenterY() - 10)
			if(playerPosX < this.getCenterX())
				return DIRECTION.equals(Direction.LEFT);
			else
				return DIRECTION.equals(Direction.RIGHT);
		
		return false;
	}
	
	public void moveToPlayer(Player player)
	{
		switch(DIRECTION)
		{
		case Direction.DOWN: this.setPosY(posY - 0.03); break;
		case Direction.UP: this.setPosY(posY + 0.03); break;
		case Direction.LEFT: this.setPosX(posX - 0.03); break;
		case Direction.RIGHT: this.setPosX(posX + 0.03); break;
		}
		this.swapImg();
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

	public String[] getDialog(int page) 
	{
		return null;
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
	
	public void swapImg()
	{
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
