/**
 * Stellt die Waffe Faust (keine Waffe) dar
 * @author Mike Bechtel
 *
 */
public class Weapon_Faust extends Weapon
{
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor der Faust
	 */
	public Weapon_Faust() 
	{
		super(0, 0);
	}
	
	/**
	 * Gibt eine String-Repraesentation der Waffe zurueck
	 * @return Name der Waffe
	 */
	public String toString() 
	{
		return "Faust";
	}

	/**
	 * Zeichnet die Waffe auf das Spielfeld
	 */
	public void drawImg() 
	{
	}
	
	/**
	 * Gibt den Waffenbonus zurueck
	 * @return Waffenschaden
	 */
	public int getBonus()
	{
		return 0;
	}
	
	/**
	 * Gibt eine Typrepraesentation der Waffe zurueck
	 * @return Typrepraesentation 
	 */
	public int type()
	{
		return CollectableTypes.WEAPON_FAUST;
	}
}
