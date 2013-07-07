
public class Weapon_Krummsaebel extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Krummsaebel(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/krummsaebel.png";
	}

	public Weapon_Krummsaebel(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/krummsaebel.png";
	}
	
	public String toString() 
	{
		return "Krummsaebel";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/krummsaebel.png");
	}
	
	public int getBonus()
	{
		return 23;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_KRUMMSAEBEL;
	}
}
