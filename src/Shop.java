public class Shop extends Block
{
	private static final long serialVersionUID = 1L;

	private int posX, posY;
	
//	private int sword = 1;
//	public static final int SWORD_PRICE = 3;
	
	private int armor = 1;
	public static final int ARMOR_PRICE = 3;
	
	public Shop(int posX, int posY)
	{
		
		super(posX, posY, 26, 26);
		
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

	public int getArmor()
	{
		return  armor;
	}

}



