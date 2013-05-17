
public class Trap extends Block {

	private double posX, posY;
	
	public Trap(int posX, int posY)
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
		
		hidden = true;
	}
	
	@Override
	boolean isSolid()
	{
		return false;
	}

	@Override
	boolean isExit()
	{	
		return false;
	}

	@Override
	public String toString()
	{
		return "trap";
	}

	@Override
	void drawImg()
	{
		StdDraw.picture(posX, posY, "images\\arena\\Trap.png");		
	}
	
	

}
