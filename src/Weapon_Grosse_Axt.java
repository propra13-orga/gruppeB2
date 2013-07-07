
public class Weapon_Grosse_Axt extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Weapon_Grosse_Axt(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/grosse_axt.png";
	}

	public Weapon_Grosse_Axt(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/grosse_axt.png";
	}
	
	public String toString() 
	{
		return "Grosse Axt";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/grosse_axt.png");
	}
	
	public int getBonus()
	{
		return 38;
	}
	
	public int type()
	{
		return CollectableTypes.WEAPON_GROSSE_AXT;
	}
}
