
public class Door extends Block
{
	private double posX, posY;
	
	public Door(int posX, int posY)
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;		
		
		hidden = true;
	}
	
	public void drawImg()
	{
		StdDraw.picture(posX, posY, "images\\arena\\Exit_Tile_"+1+".png");
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public boolean isExit()
	{
		return true;
	}
	
	public String toString()
	{
		return "door";
	}
}
