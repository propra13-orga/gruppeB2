
public class Stairs extends Block
{
	private double posX, posY;
	
	public Stairs(double posX, double posY)
	{
		this.posX = posX;
		this.posY = posY;

		hidden = true;
	}
	
	public void drawImg()
	{
		StdDraw.picture(posX, posY, "images\\arena\\Exit_Tile_"+2+".png");
	}
	
	public boolean isSolid()
	{
		return false;
	}
}
