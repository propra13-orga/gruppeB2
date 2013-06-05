import java.awt.Rectangle;


public class Enemy extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public int posX, posY;
	
	private boolean colUp, colDown, colRight, colLeft;
	
	
	private final int SPEED = 2;
	
	private int swap;

	public Enemy(int posX, int posY)
	{
		super(posX, posY, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public void draw()
	{
		if(swap < 10)
		{
			StdDraw.picture(posX, posY, "images/enemy/enemy"+1+".png");
			swap++;
		}
		else if(swap < 20)
		{
			StdDraw.picture(posX, posY, "images/enemy/enemy"+2+".png");
			swap++;
		}
		else if(swap < 30)
		{
			StdDraw.picture(posX, posY, "images/enemy/enemy"+3+".png");
			swap++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images/enemy/enemy"+4+".png");
			swap++;
			
			if(swap == 40)
				swap = 0;					
		}			
	}
	
	public void move(String direction)
	{
		if(direction.equalsIgnoreCase(Direction.DOWN))
			{
				posY -= SPEED;
				this.draw();
			}			
		if(direction.equalsIgnoreCase(Direction.LEFT))
			{
				posX -= SPEED;
				this.draw();
			}
		if(direction.equalsIgnoreCase(Direction.RIGHT))
			{
				posX += SPEED;
				this.draw();
			}
		if(direction.equalsIgnoreCase(Direction.UP))
			{
				posY += SPEED;
				this.draw();
			}
		this.setLocation(posX, posY);
	}
	
	public double getPosX()
	{
		return posX;
	}

	public void setPosX(int posX)
	{
		this.posX = posX;
	}

	public double getPosY()
	{
		return posY;
	}

	public void setPosY(int posY)
	{
		this.posY = posY;
	}
	
	public boolean isSolid()
	{
		return true;
	}
	
	public boolean isColUp() {
		return colUp;
	}

	public void setColUp(boolean colUp) {
		this.colUp = colUp;
	}

	public boolean isColDown() {
		return colDown;
	}

	public void setColDown(boolean colDown) {
		this.colDown = colDown;
	}

	public boolean isColRight() {
		return colRight;
	}

	public void setColRight(boolean colRight) {
		this.colRight = colRight;
	}

	public boolean isColLeft() {
		return colLeft;
	}

	public void setColLeft(boolean colLeft) {
		this.colLeft = colLeft;
	}

}
