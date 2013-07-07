
public class Weapon_Langschwert extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Langschwert(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/langschwert.png";
	}

	public Weapon_Langschwert(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/langschwert.png";
	}
	
	public String toString() 
	{
		return "Langschwert";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/langschwert.png");
	}
	
	public int getBonus()
	{
		return 20;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_LANGSCHWERT;
	}
}
