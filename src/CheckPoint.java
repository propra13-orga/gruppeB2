
public class CheckPoint 
{
	private int level;
	private double checkPosX, checkPosY;
	
	public CheckPoint(int level, double checkPosX, double checkPosY)
	{
		this.level = level;
		this.checkPosX = checkPosX;
		this.checkPosY = checkPosY;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public double getPosX()
	{
		return checkPosX;
	}
	
	public double getPosY()
	{
		return checkPosY;
	}
	
	public void setLevel(int lvl)
	{
		this.level = lvl;
	}
	
	public void setPosX(double posX)
	{
		this.checkPosX = posX;
	}
	
	public void setPosY(double posY)
	{
		this.checkPosY = posY;
	}
}
