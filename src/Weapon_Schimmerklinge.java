
public class Weapon_Schimmerklinge extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Schimmerklinge(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
	}

	public Weapon_Schimmerklinge(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public String toString() 
	{
		return "Schimmerkl.";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/schimmerklinge.png");
	}
	
	public void equipWeapon(Player player)
	{
		this.equipped = true;
		player.increaseAtt(40);
	}
	
	public void unequipWeapon(Player player)
	{
		this.equipped = false;
		player.decreaseAtt(40);
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_SCHIMMERKLINGE;
	}
}
