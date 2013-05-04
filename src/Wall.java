
public class Wall extends Block
{
	private boolean hidden;
	private double rand;
	private double posX, posY;
	
	public Wall(double posX, double posY)
	{
		this.posX = posX;
		this.posY = posY;
		
		hidden = true;		
		rand = Math.random();
	}
	
	public void drawImg()
	{
		if(rand < 0.25)
			StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+1+".png");
		else if(rand < 0.5)
			StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+2+".png");
		else if(rand < 0.75)
			StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+3+".png");
		else
			StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+4+".png");
	}
	
	public boolean isHidden()
	{
		return hidden;
	}
	
	public void setHidden(boolean status)
	{
		hidden = status;
	}
	
	public boolean isSolid()
	{
		return true;
	}
}
