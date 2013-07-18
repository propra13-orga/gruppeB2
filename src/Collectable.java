import java.awt.Rectangle;

/**
 *  Diese abstrakte Klasse stellen die diversen aufsammelbaren Items des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 *  Zudem wird das Interface Equipable implementiert, da die (meisten) aufnehmbaren
 *  Items ausgeruestet werden koennen.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  Collectables fuer die Logik des Spiels.
 *  </p>
 */
abstract class Collectable extends Rectangle implements Equipable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Speichert die Stapelgroesse des Collectable
	 */
	private int count;
	
	/**
	 * Speichert den Dateipfad des Bildes
	 */
	protected String picture;
	
    /**
     * Konstruktor eines Collectable-Objekts
     *
     * @param posX - die x-Position (Feld) des Collectable (Rectangle)
     * @param posY - die y-Position (Feld) des Collectable (Rectangle)
     * @param sizeX - die x-Groesse des Items fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Items fuer die Kollision(Rectangle)
     * @param anzahl - Groesse des Stapels
     */
	public Collectable(int posX, int posY, int sizeX, int sizeY, int anzahl)
	{
		super(posX, posY, sizeX, sizeY);
		this.count = anzahl;
	}
	
	/**
	 * Gibt eine String-Repraesentation des Collectable zurueck
	 */
	public abstract String toString();
	
	/**
	 * Zeichnet das Bild
	 */
	public abstract void drawImg();
	
	/**
	 * Ueberprueft die Kollision mit dem Spieler
	 * @param player - Das Spieler-Objekt, mit dem die Kollision geprueft wird
	 * @return - Ein <b>int</b>, die die Seite darstellt, an der eine Kollision auftrat
	 */
	public abstract int checkCollision(Player player);

	/**
	 * Setzt die Stapelgroesse
	 * @param count - Neue Stapelgroesse
	 */
	public void setCount(int count)
	{
		this.count = count;
	}
	
	/**
	 * Gibt die Stapelgroesse zurueck
	 * @return Stapelgroesse
	 */
	public int getCount()
	{
		return count;
	}
	
	/**
	 * Erhoeht die Stapelgroesse
	 * @param count - Erhoehe Stapel um Anzahl <i>count</i>
	 */
	public void increaseCount(int count)
	{
		this.count = this.count + count;
	}
	
	/**
	 * Verkleiner die Stapelgroesse
	 * @param count - Verkleiner Stapel um Anzahl <i>count</i>
	 */
	public void decreaseCount(int count)
	{
		this.count = this.count - count;
	}
	
	/**
	 * Gibts zurueck, ob das Item vom Player benutzt wurde
	 * @param player - Spieler, welcher das Item besitzt
	 * @return - <b>true</b> wenn das Item benutzt wurde, <b>false</b> sonst
	 */
	public abstract boolean useItem(Player player);
	
	/**
	 * Gibt den (moeglichen) Bonus des Items zurueck
	 * @return Bonus des Items
	 */
	public abstract int getBonus();
	
	/**
	 * Gibt eine Typbeschreibung des Items zurueck
	 * @return Typbeschreibung des Items
	 */
	public abstract int type();
}