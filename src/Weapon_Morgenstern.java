
public class Weapon_Morgenstern extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Morgenstern(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/morgenstern.png";
	}

	public Weapon_Morgenstern(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/morgenstern.png";
	}
	
	public String toString() 
	{
		return "Morgenstern";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/morgenstern.png");
	}
	
	public int getBonus()
	{
		return 30;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_MORGENSTERN;
	}
}
