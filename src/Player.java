import java.awt.event.KeyEvent;


public class Player
{
//	private double posX, posY;
	private int posX, posY;
	
	private boolean alive = true;
	
	int swpL = 0;
	int swpR = 0;
	int swpU = 0;
	int swpD = 0;
	
	private int SPEED = 4;
	
//	public Player(double posX, double posY)
	public Player(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	public void swapImg(String direction)
	{
		if(direction.equalsIgnoreCase(Direction.LEFT))
		{
			if(swpL < 6)
			{
				StdDraw.picture(posX, posY, "images\\player\\player_left_"+1+".png");
				swpL++;
			}
			else
			{
				StdDraw.picture(posX, posY, "images\\player\\player_left_"+2+".png");
				swpL++;
				
				if(swpL == 11)
					swpL = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.RIGHT))
		{
			if(swpR < 6)
			{
				StdDraw.picture(posX, posY, "images\\player\\player_right_"+1+".png");
				swpR++;
			}
			else
			{
				StdDraw.picture(posX, posY, "images\\player\\player_right_"+2+".png");
				swpR++;
				
				if(swpR == 11)
					swpR = 0;				
			}
		}
		else if(direction.equalsIgnoreCase(Direction.UP))
		{
			if(swpU < 6)
			{
				StdDraw.picture(posX, posY, "images\\player\\player_up_"+1+".png");
				swpU++;
			}
			else
			{
				StdDraw.picture(posX, posY, "images\\player\\player_up_"+2+".png");
				swpU++;
				
				if(swpU == 11)
					swpU = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.DOWN))
		{
			if(swpD < 6)
			{
				StdDraw.picture(posX, posY, "images\\player\\player_down_"+1+".png");
				swpD++;
			}
			else
			{
				StdDraw.picture(posX, posY, "images\\player\\player_down_"+2+".png");
				swpD++;
				
				if(swpD == 11)
					swpD = 0;				
			}
		}
		
	}
	*/
	
	public void draw()
	{
		StdDraw.picture(posX, posY, "images\\player\\player_down_"+1+".png");
	}
	
	
	public void moveUp()
	{
		posY = posY + SPEED;
		if(swpU < 6)
		{
			StdDraw.picture(posX, posY, "images\\player\\player_up_"+1+".png");
			swpU++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images\\player\\player_up_"+2+".png");
			swpU++;
			
			if(swpU == 11)
				swpU = 0;					
		}
	}
	
	//Methoden zur Steuerung
	public void moveDown()
	{
		posY = posY - SPEED;
		if(swpD < 6)
		{
			StdDraw.picture(posX, posY, "images\\player\\player_down_"+1+".png");
			swpD++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images\\player\\player_down_"+2+".png");
			swpD++;
			
			if(swpD == 11)
				swpD = 0;				
		}
	}
	
	public void moveLeft()
	{
		posX  = posX - SPEED;
		if(swpL < 6)
		{
			StdDraw.picture(posX, posY, "images\\player\\player_left_"+1+".png");
			swpL++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images\\player\\player_left_"+2+".png");
			swpL++;
			
			if(swpL == 11)
				swpL = 0;					
		}
	}
	
	public void moveRight()
	{
		posX = posX + SPEED;
		if(swpR < 6)
		{
			StdDraw.picture(posX, posY, "images\\player\\player_right_"+1+".png");
			swpR++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images\\player\\player_right_"+2+".png");
			swpR++;
			
			if(swpR == 11)
				swpR = 0;				
		}
	}
	

//	public void setPos(double posX, double posY)
	public void setPos(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}

//	public void setPosX(double posX)
	public void setPosX(int posX)
	{
		this.posX = posX;
	}

//	public void setPosY(double posY)
	public void setPosY(int posY)
	{
		this.posY = posY;
	}
	
	public double getPosX()
	{
		return posX;
	}
	
	public double getPosY()
	{
		return posY;
	}
	
	public boolean getAlive()
	{
		return true;
	}
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
	public int getSpeed()
	{
		return SPEED;
	}
	
}
