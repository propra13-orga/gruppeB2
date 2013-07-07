
public class Armor_Leder_Harnisch extends Armor
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Armor_Leder_Harnisch(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/armor/leder_harnisch.png";
	}

	public Armor_Leder_Harnisch(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/armor/leder_harnisch.png";
	}
	
	public String toString() 
	{
		return "Lederharnisch";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/armor/leder_harnisch.png");
	}
	
	public int getBonus()
	{
		return 16;
	}
	
	public int type()
	{
		return CollectableTypes.ARMOR_LEDER_HARNISCH;
	}
}
