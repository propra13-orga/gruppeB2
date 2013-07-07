
public class Weapon_Berserkerwut extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Berserkerwut(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/berserkerwut.png";
	}

	public Weapon_Berserkerwut(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/berserkerwut.png";
	}
	
	public String toString() 
	{
		return "Berserkerwut";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/berserkerwut.png");
	}
	
	public int getBonus()
	{
		return 57;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_BERSERKERWUT;
	}
}
