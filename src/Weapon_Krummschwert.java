/**
 * Stellt die Waffe Krummschwert dar
 * @author Mike Bechtel
 *
 */
public class Weapon_Krummschwert extends Weapon
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die x- bzw. y-Position auf dem Spielfeld
	 */
	private double posX, posY;	

	/**
	 * Konstruktor der Waffe
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Weapon_Krummschwert(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/krummschwert.png";
	}

	/**
	 * Konstruktor der Waffe
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param anzahl - Stapelgroesse
	 */
	public Weapon_Krummschwert(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/krummschwert.png";
	}
	
	/**
	 * Gibt eine String-Repraesentation der Waffe zurueck
	 * @return Name der Waffe
	 */
	public String toString() 
	{
		return "Krummschwert";
	}

	/**
	 * Zeichnet die Waffe auf das Spielfeld
	 */
	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/krummschwert.png");
	}
	
	/**
	 * Gibt den Waffenbonus zurueck
	 * @return Waffenschaden
	 */
	public int getBonus()
	{
		return 26;
	}
	
	/**
	 * Gibt eine Typrepraesentation der Waffe zurueck
	 * @return Typrepraesentation 
	 */
	public int type()
	{
		return CollectableTypes.WEAPON_KRUMMSCHWERT;
	}
}
