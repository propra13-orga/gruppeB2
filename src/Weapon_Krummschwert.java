
public class Weapon_Krummschwert extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Krummschwert(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/krummschwert.png";
	}

	public Weapon_Krummschwert(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/krummschwert.png";
	}
	
	public String toString() 
	{
		return "Krummschwert";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/krummschwert.png");
	}
	
	public int getBonus()
	{
		return 26;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_KRUMMSCHWERT;
	}
}
