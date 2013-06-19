
public class Shop extends Block
{

	private int posX, posY;
	
	public Shop(int posX, int posY) {
		
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	boolean isSolid() 
	{
		return true;
	}

	@Override
	public String toString() 
	{
		return "shop";
	}

	@Override
	void drawImg()
	{

			StdDraw.picture(posX, posY, "images/npc/haendler.png");
	}

}



