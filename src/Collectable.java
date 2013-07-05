import java.awt.Rectangle;

/**
 *  <i>Item</i>. Diese abstrakte Klasse stellen die diversen Items des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  Items fuer die Logik des Spiels.
 *  </p>
 */
abstract class Collectable extends Rectangle
{
	private static final long serialVersionUID = 1L;

	private int count;
	
    /**
     * Konstruktor eines Item-Objekts
     *
     * @param posX - die x-Position (Feld) des Items (Rectangle)
     * @param posY - die y-Position (Feld) des Items (Rectangle)
     * @param sizeX - die x-Groesse des Items fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Items fuer die Kollision(Rectangle)
     */
	public Collectable(int posX, int posY, int sizeX, int sizeY, int anzahl)
	{
		super(posX, posY, sizeX, sizeY);
		this.count = anzahl;
	}
	
	public abstract String toString();
	public abstract void drawImg();
	public abstract int checkCollision(Player player);

	public void setCount(int count)
	{
		this.count = count;
	}
	public int getCount()
	{
		return count;
	}
	public void increaseCount(int count)
	{
		this.count = this.count + count;
	}
	public void decreaseCount(int count)
	{
		this.count = this.count - count;
	}
	
	public abstract boolean useItem(Player player);
	
	public abstract int type();
}