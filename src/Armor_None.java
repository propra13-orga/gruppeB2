
public class Armor_None extends Armor
{
	private static final long serialVersionUID = 1L;
	
	public Armor_None() 
	{
		super(0, 0);
	}
	
	public String toString() 
	{
		return "Keine Ruestung";
	}

	public void drawImg() 
	{
	}
	
	public int getBonus()
	{
		return 0;
	}
	
	public int type()
	{
		return CollectableTypes.ARMOR_NONE;
	}
}
