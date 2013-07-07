
public abstract class Armor extends Collectable
{
private static final long serialVersionUID = 1L;
	
	protected boolean equipped;
	
	protected String picture;

	public Armor(int posX, int posY) 
	{
		super(posX, posY, 32, 32, 1);
		
		this.equipped = false;
	}

	public Armor(int posX, int posY, int anzahl) 
	{
		super(posX, posY, 32, 32, anzahl);
		
		this.equipped = false;
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

	public abstract String toString(); 

	public abstract void drawImg();
	
	public boolean useItem(Player player) 
	{
		return false;
	}
	
	public abstract int getBonus();
	
	public boolean isEquipped()
	{
		return equipped;
	}
	
	public abstract int type();
}
