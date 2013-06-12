import java.awt.Rectangle;


public class Boss extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	public double posX, posY;
	
	private final int SPEED = 2;
	
	private int swap;
	
	private boolean colUp, colDown, colRight, colLeft;
	
	public Boss(int posX, int posY)
	{
		super(posX, posY, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public void draw()
	{
		if(swap < 8)
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+1+".png");
			swap++;
		}
		else if(swap < 16)
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+2+".png");
			swap++;
		}
		else if(swap < 24)
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+3+".png");
			swap++;
		}
		else if(swap < 32)
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+4+".png");
			swap++;
		}
		else if(swap < 40)
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+5+".png");
			swap++;
		}
		else if(swap < 48)
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+4+".png");
			swap++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images/enemy/Satan_"+2+".png");
			swap++;
			
			if(swap == 56)
				swap = 0;					
		}
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
	
}
