
public class Attack 
{
	private String name;
	private String type;
	
	private double damage;
	private double defense;
	
	public Attack(String name, String type)
	{
		this.name = name;
		this.type = type;
	}
	
	//-----------------------------------------------------------------------------------------
	
	public void setDamage(double dmg)
	{
		this.damage = dmg;
	}
	
	public double getDamage()
	{
		return damage;
	}
	
	public void setDefense(double def)
	{
		this.defense = def;
	}
	
	public double getDefense()
	{
		return defense;
	}
	
	//-----------------------------------------------------------------------------------------
	
	public String getName()
	{
		return name.toUpperCase();
	}
	
	public String getType()
	{
		return type.toUpperCase();
	}
}
