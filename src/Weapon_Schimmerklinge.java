
public class Weapon_Schimmerklinge extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Schimmerklinge(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/schimmerklinge.png";
	}

	public Weapon_Schimmerklinge(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/schimmerklinge.png";
	}
	
	public String toString() 
	{
		return "Schimmerkl.";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/schimmerklinge.png");
	}
	
	public int getBonus()
	{
		return 40;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_SCHIMMERKLINGE;
	}
}
