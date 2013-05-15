import java.awt.Rectangle;


public class Player extends Rectangle
{
	private int posX, posY;
	
	int swpL = 0;
	int swpR = 0;
	int swpU = 0;
	int swpD = 0;
	
	public Player(int posX, int posY)
	{
		super(posX + 3, posY - 6, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
	}
	
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
	
	public void draw()
	{
		StdDraw.picture(posX, posY, "images\\player\\player_down_"+1+".png");
	}

	public void setPos(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
		
		this.setLocation(posX + 3, posY - 6);
	}

	public void setPosX(int posX)
	{
		this.posX = posX;
		this.setLocation(posX + 3, posY - 6);
	}

	public void setPosY(int posY)
	{
		this.posY = posY;
		this.setLocation(posX + 3, posY - 6);
	}
	
	public double getPosX()
	{
		return posX;
	}
	
	public double getPosY()
	{
		return posY;
	}
}
