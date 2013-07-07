

public class Item_Coin extends Item
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	
	private final int value;

	public Item_Coin(int posX, int posY) 
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
		
		//Zufaelliger Muenzhaufen (zwischen 10 und 100 Muenzen)
		value = (int)(Math.random() * ((100 - 10) + 1) + 10);
	}
	
	public Item_Coin(int posX, int posY, int sizeX, int sizeY, int value)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public boolean checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			player.increaseCoins(value);
			return true;
		}
		else
			return false;
	}

	public String toString() 
	{
		return "Coin";
	}

	public void drawImg() 
	{
		if(value < 50)
			StdDraw.picture(posX, posY, "images/items/coins_small.png");
		else if(value < 80)
			StdDraw.picture(posX, posY, "images/items/coins_mid.png");
		else
			StdDraw.picture(posX, posY, "images/items/coins_big.png");
	}
}
