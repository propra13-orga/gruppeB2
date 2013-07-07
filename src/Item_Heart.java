

public class Item_Heart extends Item
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Item_Heart(int posX, int posY) 
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public boolean checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			player.increaseHealth(20);
			return true;
		}
		else
			return false;
	}

	public String toString() 
	{
		return "Heart";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/heart.png");
	}
}
