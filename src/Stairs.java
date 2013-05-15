
public class Stairs extends Block
{
	private double posX, posY;
	
	public Stairs(int posX, int posY)
	{
		super(posX, posY, 32, 32);
		
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
	
	public boolean isExit()
	{
		return false;
	}
	
	public String toString()
	{
		return "stairs";
	}
}
