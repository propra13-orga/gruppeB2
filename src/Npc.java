
import java.awt.Rectangle;

/**
 *  Diese abstrakte Klasse stellen die NPCs des Spiels dar.
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
	
	/**
	 * Zeichnet den NPC auf das Spielfeld
	 */
	public abstract void drawImg();

	/**
	 * Prueft Kollisionen mit dem Rectangle <i>rect</i>
	 * @param rect - Rectangle, mit dem die Kollision geprueft wird
	 */
	public abstract int checkCollision(Rectangle rect);
	/**
	 * Gibt zurueck, ob der Spieler sich im Aktionsradius befindet
	 * @param player - Player, der ueberprueft wird
	 */
	public abstract boolean playerInRange(Player player);

	/**
	 * Gibt den Dateipfad des Avatars zurueck
	 */
	public abstract String getAvatar();	
	/**
	 * Gibt die Dialogseite <i>page</i> zurueck
	 * @param page - Seite, die zurueckgegeben wird
	 */
	public abstract String [] getDialog(int page);
	/**
	 * Prueft, ob die Dialogseite <i>page</i> ein Auswahldialog ist
	 * @param page - Seite, die ueberprueft wird
	 * @return <b>true</b> wenn die Dialogseite Auswahldialog ist, <b>false</b> sonst
	 */
	public abstract boolean isOptionDialog(int page);
}