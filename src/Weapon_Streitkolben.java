
public class Weapon_Streitkolben extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Streitkolben(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/streitkolben.png";
	}

	public Weapon_Streitkolben(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/streitkolben.png";
	}
	
	public String toString() 
	{
		return "Streitkolben";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/streitkolben.png");
	}
	
	public int getBonus()
	{
		return 15;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_STREITKOLBEN;
	}
}