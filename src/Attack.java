
public class Attack 
{
	private String name;
	private String type;
	private String effect;
	private String desc;
	
	private double strength;
	private int sound;
	
	private boolean dealDmg;
	
	public Attack(String name, String type, boolean dealDmg, int sound)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
		this.sound = sound;
		this.desc = "none";
		this.effect = "none";
	}
	
	public Attack(String name, String type, String effect, boolean dealDmg, int sound, String desc)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
		this.sound = sound;
		this.effect = effect;
		this.desc = desc;
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
	
	public String getEffect()
	{
		return effect;
	}
	
	public String getDesc()
	{
		return desc;
	}
	
	//-----------------------------------------------------------------------------------------
	
	public int getSound()
	{
		return sound;
	}
	
	public String getImageSrc()
	{
		return "images/battle/attacks/" + name.toLowerCase() + ".png";
	}
}
