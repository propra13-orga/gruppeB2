
public class Door extends Block
{
	private double posX, posY;
	
	public Door(double posX, double posY)
	{
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
}
