/**
 * Die Klasse Magic stellt die Zauber des Spiels dar.
 * @author Mike Bechtel
 *
 */
public class Magic 
{	
	/**
	 * Speichert den Namen des Zaubers
	 */
	private String name;
	/**
	 * Speichert den Angrifftypen des Zaubers
	 */
	private String type;
	/**
	 * Speichert den moeglichen Effekt des Zaubers
	 */
	private String effect;

	/**
	 * Speichert die Staerke des Zaubers
	 */
	private double strength;
	private double manaCost;
	/**
	 * Speichert den Sound (die Referenz) des Zaubers
	 */
	private int sound;
	
	private boolean dealDmg;
	/**
	 * Speichert, ob der Zauber Schaden verursacht oder nicht
	 */

	/**
	 * Konstruktor eines Magic-Objekts.
	 * @param name - Name des Zaubers
	 * @param type - Schadenstyp des Zaubers
	 * @param dealDmg - Verursacht der Zauber Schaden?
	 * @param manaCost - Manakosten des Zaubers
	 * @param sound - Sound-Referenz des Zaubers
	 */
	public Magic(String name, String type, boolean dealDmg, double manaCost, int sound)
	{
		this.name = name;
		this.type = type;
		this.dealDmg = dealDmg;
		this.manaCost = manaCost;
		this.sound = sound;
		effect = "none";
	}

	/**
	 * Konstruktor eines Magic-Objekts.
	 * @param name - Name des Zaubers
	 * @param type - Schadenstyp des Zaubers
	 * @param dealDmg - Verursacht der Zauber Schaden?
	 * @param manaCost - Manakosten des Zaubers
	 * @param sound - Sound-Referenz des Zaubers
	 * @param effect - Effekt des Zaubers
	 */
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

	/**
	 * Setzt die Staerke des Zaubers
	 * @param str - Staerke des Zaubers
	 */
	public void setStrength(double str)
	{
		this.strength = str;
	}

	/**
	 * Gibt die Staerke des Zaubers zurueck
	 * @return Staerke des Zaubers
	 */
	public double getStrength()
	{
		return strength;
	}
	
	//-----------------------------------------------------------------------------------------

	/**
	 * Gibt den Namen des Zaubers als String zurueck
	 * @return Name des Zaubers
	 */
	public String getName()
	{
		return name.toUpperCase();
	}

	/**
	 * Gibt den Typ des Zaubers als String zurueck
	 * @return Typ des Zaubers
	 */
	public String getType()
	{
		return type.toUpperCase();
	}

	/**
	 * Gibt einen String zurueck, ob der Zauber Schaden verursacht oder nicht
	 * @return <b>DMG</b> wenn der Zauber Schaden verursacht, <b>DEF</b> wenn nicht
	 */
	public String getDmgType()
	{
		if(dealDmg)
			return "DMG";
		else
			return "DEF";
	}

	/**
	 * Gibt zurueck, ob der Zauber Schaden verursacht
	 * @return <b>true</b> wenn der Zauber Schaden verursacht, <b>false</b> wenn nicht
	 */
	public boolean dealDmg()
	{
		return dealDmg;
	}
	
	/**
	 * Gibt die Manakosten des Zaubers zurueck
	 * @return Manakosten
	 */
	public double manaCost()
	{
		return manaCost;
	}

	/**
	 * Gibt den Effekt des Zaubers zurueck
	 * @return Effekt des Zaubers
	 */
	public String getEffect()
	{
		return effect;
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
		return "images/battle/magic/" + name.toLowerCase() + ".png";
	}
}
