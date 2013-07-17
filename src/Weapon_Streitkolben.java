/**
 * Stellt die Waffe Streitkolben dar
 * @author Mike Bechtel
 *
 */
public class Weapon_Streitkolben extends Weapon
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
	public Weapon_Streitkolben(int posX, int posY) 
	{
		super(posX, posY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/streitkolben.png";
	}

	/**
	 * Konstruktor der Waffe
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param anzahl - Stapelgroesse
	 */
	public Weapon_Streitkolben(int posX, int posY, int anzahl) 
	{
		super(posX, posY, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/weapons/streitkolben.png";
	}
	
	/**
	 * Gibt eine String-Repraesentation der Waffe zurueck
	 * @return Name der Waffe
	 */
	public String toString() 
	{
		return "Streitkolben";
	}

	/**
	 * Zeichnet die Waffe auf das Spielfeld
	 */
	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/weapons/streitkolben.png");
	}
	
	/**
	 * Gibt den Waffenbonus zurueck
	 * @return Waffenschaden
	 */
	public int getBonus()
	{
		return 15;
	}
	
	/**
	 * Gibt eine Typrepraesentation der Waffe zurueck
	 * @return Typrepraesentation 
	 */
	public int type()
	{
		return CollectableTypes.WEAPON_STREITKOLBEN;
	}
}
