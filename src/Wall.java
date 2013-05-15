
public class Wall extends Block
{
	private double rand;
	private double posX, posY;
	
	public Wall(int posX, int posY)
	{
		super(posX, posY, 32, 32);
		
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
	
	public boolean isSolid()
	{
		return true;
	}
	
	public boolean isExit()
	{
		return false;
	}
	
	public String toString()
	{
		return "wall";
	}
}
