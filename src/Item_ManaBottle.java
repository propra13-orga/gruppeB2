

public class Item_ManaBottle extends Collectable
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Item_ManaBottle(int posX, int posY) 
	{
		super(posX, posY, 32, 32, 1);
		
		this.posX = posX;
		this.posY = posY;
	}

	public Item_ManaBottle(int posX, int posY, int anzahl) 
	{
		super(posX, posY, 32, 32, anzahl);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public int checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			double pX = player.getCenterX();
			double pY = player.getCenterY();
			double fX = this.getCenterX();
			double fY = this.getCenterY();
			
			if(pX > fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_LEFT;
			if(pX < fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_RIGHT;
			if(pY > fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_DOWN;
			if(pY < fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_UP;
			else
				return Direction.NO_COLLISION;
		}
		else
			return Direction.NO_COLLISION;
	}

	public String toString() 
	{
		return "Manatrank";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/mana_bottle.png");
	}
	
	public boolean useItem(Player player) 
	{
		if(player.getMana() < player.getMaxMana())
		{
			player.increaseMana(0.5 * player.getMaxMana());
			return true;
		}
		else
			return false;
	}
	
	public int type()
	{
		return CollectableTypes.MANA_BOTTLE;
	}

	public boolean isEquipped() 
	{
		return false;
	}
}
