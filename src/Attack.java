/**
 * Die Klasse Attack stellt die Angriffe des Spiels dar.
 * @author Mike Bechtel
 *
 */
public class Attack 
{
	/**
	 * Speichert den Namen der Attacke
	 */
	private String name;
	
	/**
	 * Speichert den Angrifftypen der Attacke
	 */
	private String type;
	
	/**
	 * Speichert den moeglichen Effekt der Attacke
	 */
	private String effect;
	
	/**
	 * Speichert die Beschreibung der Attacke
	 */
	private String desc;
	
	/**
	 * Speichert die Staerke der Attacke
	 */
	private double strength;
	
	/**
	 * Speichert den Sound (die Referenz) der Attacke
	 */
	private int sound;
	
	/**
	 * Speichert, ob die Attacke Schaden verursacht oder nicht
	 */
	private boolean dealDmg;
	
	/**
	 * Konstruktor eines Attacken-Objekts.
	 * @param name - Name der Attacke
	 * @param type - Schadenstyp der Attacke
	 * @param dealDmg - Verursacht die Attacke Schaden?
	 * @param sound - Sound-Referenz der Attacke
	 */
	public Attack(String name, String type, boolean dealDmg, int sound)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
		this.sound = sound;
		this.desc = "none";
		this.effect = "none";
	}
	
	/**
	 * Konstruktor eines Attacken-Objekts.
	 * @param name - Name der Attacke
	 * @param type - Schadenstyp der Attacke
	 * @param effect - Effekt der Attacke
	 * @param dealDmg - Verursacht die Attacke Schaden?
	 * @param sound - Sound-Referenz der Attacke
	 * @param desc - Beschreibung der Attacke
	 */
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
	
	/**
	 * Setzt die Staerke der Attacke
	 * @param str - Staerke der Attacke
	 */
	public void setStrength(double str)
	{
		this.strength = str;
	}
	
	/**
	 * Gibt die Staerke der Attacke zurueck
	 * @return Staerke der Attacke
	 */
	public double getStrength()
	{
		return strength;
	}
	
	//-----------------------------------------------------------------------------------------
	
	/**
	 * Gibt den Namen der Attacke als String zurueck
	 * @return Name der Attacke
	 */
	public String getName()
	{
		return name.toUpperCase();
	}
	
	/**
	 * Gibt den Typ der Attacke als String zurueck
	 * @return Typ der Attacke
	 */
	public String getType()
	{
		return type.toUpperCase();
	}
	
	/**
	 * Gibt einen String zurueck, ob die Attacke Schaden verursacht oder nicht
	 * @return <b>DMG</b> wenn die Attacke Schaden verursacht, <b>DEF</b> wenn nicht
	 */
	public String getDmgType()
	{
		if(dealDmg)
			return "DMG";
		else
			return "DEF";
	}
	
	/**
	 * Gibt zurueck, ob die Attacke Schaden verursacht
	 * @return <b>true</b> wenn die Attacke Schaden verursacht, <b>false</b> wenn nicht
	 */
	public boolean dealDmg()
	{
		return dealDmg;
	}	
	
	/**
	 * Gibt den Effekt der Attacke zurueck
	 * @return Effekt der Attacke
	 */
	public String getEffect()
	{
		return effect;
	}
	
	/**
	 * Gibt die Beschreibung der Attacke zurueck
	 * @return Attackenbeschreibung
	 */
	public String getDesc()
	{
		return desc;
	}
	
	//-----------------------------------------------------------------------------------------
	
	/**
	 * Gibt die Sound-Referenz zurueck
	 * @return Sound-Referenz
	 */
	public int getSound()
	{
		return sound;
	}
	
	/**
	 * Gibt den Pfadnamen des Bildes zurueck
	 * @return Pfadnamen des Bildes
	 */
	public String getImageSrc()
	{
		return "images/battle/attacks/" + name.toLowerCase() + ".png";
	}
}
