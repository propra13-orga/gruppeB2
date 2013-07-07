
public class Weapon_Kleine_Axt extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Kleine_Axt(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
	}

	public Weapon_Kleine_Axt(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	public String toString() 
	{
		return "Kleine Axt";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/kleine_axt.png");
	}
	
	public void equipWeapon(Player player)
	{
		this.equipped = true;
		player.increaseAtt(17);
	}
	
	public void unequipWeapon(Player player)
	{
		this.equipped = false;
		player.decreaseAtt(17);
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_KLEINE_AXT;
	}
}
