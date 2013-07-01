
public class Attack 
{
	private String name;
	private String type;
	
	private double strength;
	
	private boolean dealDmg;
	
	public Attack(String name, String type, boolean dealDmg)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
	}
	
	//-----------------------------------------------------------------------------------------
	
	public void setStrength(double str)
	{
		this.strength = str;
	}
	
	public double getStrength()
	{
		return strength;
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
	
	public String getDmgType()
	{
		if(dealDmg)
			return "DMG";
		else
			return "DEF";
	}
	
	public boolean dealDmg()
	{
		return dealDmg;
	}
}
