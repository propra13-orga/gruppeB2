import java.awt.Rectangle;


public class Fireball extends Rectangle
{

	private int posX, posY;
	
	private int animate;
	
	private boolean active;
	
	public Fireball(int posX, int posY)
	{
		super(posX, posY, 40, 40);
		this.posX = posX;
		this.posY = posY;
	}
	
	public void draw()
	{
		if(animate<5)
		{
			StdDraw.picture(posX, posY, "images/player/fire"+1+".png");
			animate++;
		}
		else if(animate<10)
		{
			StdDraw.picture(posX, posY, "images/player/fire"+2+".png");
			animate++;
		}
		else if(animate<15)
		{
			StdDraw.picture(posX, posY, "images/player/fire"+3+".png");
			animate++;
		}
		else if(animate<20)
		{
			StdDraw.picture(posX, posY, "images/player/fire"+4+".png");
			animate++;
			
			if(animate==20)
				animate=0;
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}
