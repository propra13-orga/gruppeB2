
public class Armor_Bauernkleidung extends Armor
{
	private static final long serialVersionUID = 1L;
	
	private double posX, posY;	

	public Armor_Bauernkleidung(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/armor/bauer.png";
	}

	public Armor_Bauernkleidung(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/armor/bauer.png";
	}
	
	public String toString() 
	{
		return "Bauernkl.";
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/armor/bauer.png");
	}
	
	public int getBonus()
	{
		return 8;
	}
	
	public int type()
	{
		return CollectableTypes.ARMOR_BAUER;
	}
}
