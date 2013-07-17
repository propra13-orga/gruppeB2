/**
 * Stellt die Ruestung "Ledergarnisch" dar.
 * Erbt von der Armor-Klasse.
 * 
 * @author Mike Bechtel
 */
public class Armor_Leder_Harnisch extends Armor
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die x- und y-Positionen auf dem Spielfeld.
	 */
	private double posX, posY;	

	/**
	 * Konstruktor eines Lederharnisch-Objekts.
	 * 
	 * @param posX - x-Position der Armor auf dem Spielfeld
	 * @param posY - y-Position der Armor auf dem Spielfeld
	 */
	public Armor_Leder_Harnisch(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/armor/leder_harnisch.png";

		this.type = Types.normal;
	}

	/**
	 * Konstruktor eines Lederharnisch-Objekts.
	 * 
	 * @param posX - x-Position der Armor auf dem Spielfeld
	 * @param posY - y-Position der Armor auf dem Spielfeld
	 * @param anzahl - Menge der Items im Stapel
	 */
	public Armor_Leder_Harnisch(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/armor/leder_harnisch.png";

		this.type = Types.normal;
	}
	
	/**
	 * Gibt den (moeglicherweise abgekuerzten) Namen der Armor als String zurueck.
	 */
	public String toString() 
	{
		return "Lederharnisch";
	}

	/**
	 * Zeichnet das Image der Armor auf der Zeichenflaeche.
	 */
	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/armor/leder_harnisch.png");
	}
	
	/**
	 * Gibt den Ruestungsbonus der Armor zurueck.
	 */
	public int getBonus()
	{
		return 16;
	}
	
	/**
	 * Typbeschreibung des Armor-Objektes.
	 */
	public int type()
	{
		return CollectableTypes.ARMOR_LEDER_HARNISCH;
	}
}
