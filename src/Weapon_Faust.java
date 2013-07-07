
public class Weapon_Faust extends Weapon
{
	private static final long serialVersionUID = 1L;

	public Weapon_Faust() 
	{
		super(0, 0);
	}
	
	public String toString() 
	{
		return "Faust";
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
		return CollectableTypes.WEAPON_FAUST;
	}
}
