
public class Weapon_Streitkolben extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Streitkolben(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
	}

	public Weapon_Streitkolben(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public String toString() 
	{
		return "Streitkolben";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/streitkolben.png");
	}
	
	public void equipWeapon(Player player)
	{
		this.equipped = true;
		player.increaseAtt(15);
	}
	
	public void unequipWeapon(Player player)
	{
		this.equipped = false;
		player.decreaseAtt(15);
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_STREITKOLBEN;
	}
}
