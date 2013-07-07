
public class Weapon_Krummsaebel extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Krummsaebel(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
	}

	public Weapon_Krummsaebel(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public String toString() 
	{
		return "Krummsaebel";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/krummsaebel.png");
	}
	
	public void equipWeapon(Player player)
	{
		this.equipped = true;
		player.increaseAtt(23);
	}
	
	public void unequipWeapon(Player player)
	{
		this.equipped = false;
		player.decreaseAtt(23);
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_KRUMMSAEBEL;
	}
}
