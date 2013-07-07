
public class Weapon_Berserkerzorn extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Berserkerzorn(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/berserkerzorn.png";
	}

	public Weapon_Berserkerzorn(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/berserkerzorn.png";
	}
	
	public String toString() 
	{
		return "Berserkerzorn";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/berserkerzorn.png");
	}
	
	public int getBonus()
	{
		return 70;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_BERSERKERZORN;
	}
}
