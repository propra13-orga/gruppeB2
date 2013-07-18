import java.awt.Rectangle;

/**
 *  Diese abstrakte Klasse stellen die diversen Items des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  Items fuer die Logik des Spiels.
 *  </p>
 */
abstract class Item extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
    /**
     * Konstruktor eines Item-Objekts
     *
     * @param posX - die x-Position (Feld) des Items (Rectangle)
     * @param posY - die y-Position (Feld) des Items (Rectangle)
     * @param sizeX - die x-Groesse des Items fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Items fuer die Kollision(Rectangle)
     */
	public Item(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}

	/**
	 * Gibt eine String-Repraesentation des Item-Objekts zurueck
	 */
	public abstract String toString();
	/**
	 * Zeichnet das Item-Objekt auf dem Spielfeld
	 */
	public abstract void drawImg();
	/**
	 * Prueft die Kollision mit dem Spieler
	 */
	public abstract boolean checkCollision(Player player);
}