
import java.awt.Rectangle;

/**
 *  <i>NPC</i>. Diese abstrakte Klasse stellen die NPCs des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  NPC fuer die Logik des Spiels.
 *  </p>
 */
abstract class NPC extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
    /**
     * Konstruktor eines NPC-Objekts
     *
     * @param posX - die x-Position (Feld) des NPC (Rectangle)
     * @param posY - die y-Position (Feld) des NPC (Rectangle)
     * @param sizeX - die x-Groesse des NPC fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des NPC fuer die Kollision(Rectangle)
     */
	public NPC(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}
	
	public abstract void drawImg();
	public abstract int checkCollision(Rectangle rect);
	public abstract boolean playerInRange(Player player);
	
	public abstract String getAvatar();
	public abstract String [] getDialog(int page);
	public abstract boolean isOptionDialog(int page);
}