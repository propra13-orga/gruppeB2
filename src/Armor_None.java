/**
 * Stellt das Objekt dar, wenn der Spieler keine Ruestung traegt.
 * Erbt von der Armor-Klasse.
 * 
 * @author Mike Bechtel
 */
public class Armor_None extends Armor
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Konstruktor des Objekts. Hat keine Positionsangaben, da es nicht auf dem
	 * Spielfeld gezeichnet werden kann.
	 */
	public Armor_None() 
	{
		super(0, 0);
		this.type = Types.normal;
	}
	
	/**
	 * Gibt den (moeglicherweise abgekuerzten) Namen der Armor als String zurueck.
	 */
	public String toString() 
	{
		return "Keine Ruestung";
	}

	/**
	 * Das Objekt muss nicht gezeichnet werden.
	 */
	public void drawImg() 
	{
	}
	
	/**
	 * Keine Ruestung heisst keinen Bonus. =)
	 */
	public int getBonus()
	{
		return 0;
	}
	
	/**
	 * Typbeschreibung des Armor-Objektes.
	 */
	public int type()
	{
		return CollectableTypes.ARMOR_NONE;
	}
}
