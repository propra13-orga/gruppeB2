
public class Magic 
{
	private String name;
	private String type;
	private String effect;
	
	private double strength;
	private double manaCost;
	private int sound;
	
	private boolean dealDmg;
	
	public Magic(String name, String type, boolean dealDmg, double manaCost, int sound)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
		this.manaCost = manaCost;
		this.sound = sound;
		effect = "none";
	}
	
	public Magic(String name, String type, boolean dealDmg, double manaCost, int sound, String effect)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
		this.manaCost = manaCost;
		this.sound = sound;
		this.effect = effect;
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
	
	public double manaCost()
	{
		return manaCost;
	}
	
	public String getEffect()
	{
		return effect;
	}
	
	//-----------------------------------------------------------------------------------------
	
	public int getSound()
	{
		return sound;
	}
	
	public String getImageSrc()
	{
		return "images/battle/magic/" + name.toLowerCase() + ".png";
	}
}
