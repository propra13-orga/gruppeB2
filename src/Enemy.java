
import java.awt.Rectangle;

/**
 *  <i>NPC</i>. Diese abstrakte Klasse stellen die Enemys des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  Enemy fuer die Logik des Spiels.
 *  </p>
 */
abstract class Enemy extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
    /**
     * Konstruktor eines Enemy-Objekts
     *
     * @param posX - die x-Position (Feld) des Enemy (Rectangle)
     * @param posY - die y-Position (Feld) des Enemy (Rectangle)
     * @param sizeX - die x-Groesse des Enemy fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Enemy fuer die Kollision(Rectangle)
     */
	public Enemy(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}
	
	public abstract void drawImg();
	public abstract boolean playerInLine(Player player);
	public abstract void moveToPlayer(Player player);
	public abstract int checkCollision(Rectangle rect);
	
	public abstract String getAvatar();
	public abstract String [] getDialog(int page);
}