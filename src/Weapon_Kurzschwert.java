
public class Weapon_Kurzschwert extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Kurzschwert(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/kurzschwert.png";
	}

	public Weapon_Kurzschwert(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/kurzschwert.png";
	}
	
	public String toString() 
	{
		return "Kurzschwert";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/kurzschwert.png");
	}
	
	public int getBonus()
	{
		return 10;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_KURZSCHWERT;
	}
}
