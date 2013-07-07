
public class Weapon_Prunkschwert extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Prunkschwert(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/prunkschwert.png";
	}

	public Weapon_Prunkschwert(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/prunkschwert.png";
	}
	
	public String toString() 
	{
		return "Prunkschwert";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/prunkschwert.png");
	}
	
	public int getBonus()
	{
		return 47;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_PRUNKSCHWERT;
	}
}
