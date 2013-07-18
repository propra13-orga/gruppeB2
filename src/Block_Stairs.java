//import java.awt.Rectangle;

/**
 *  Respaesentiert das Stairs-Objekt, welches den Eingang 
 *  eines neuen Raums darstellt. Auch, um zum vorherigen Raum
 *  zu kommen.
 */
public class Block_Stairs extends Block_Block
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die x- und y-Position des Blocks auf dem Spielfeld
	 */
	private double posX, posY;
	
    /**
     * Konstruktor eines Stairs-Objekts
     *
     * @param posX - die x-Position der Treppe (Rectangle)
     * @param posY - die y-Position der Treppe (Rectangle)
     * @param sizeX - x-Groesse des Objekts
     * @param sizeY - y-Groesse des Objekts
     */
	public Block_Stairs(int posX, int posY, int sizeX, int sizeY)
	{
		//Das Stairs-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX, posY, sizeX, sizeY);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Prueft die Kollision des Stair-Objektes mit dem uebergebenen Rectangle.
	 * 
	 * @param rect - Das Rectangle-Objekt fuer welches die Kollision geprueft wird
	 * @return ein <b>int</b>, ob eine Kollision stattfindet oder nicht
	 */
	/*public int checkCollision(Rectangle rect)
	{
		if(this.intersects(rect) && rect instanceof Player)
		{
			return Direction.COLLIDE_BACK;
		}
		else
			return Direction.NO_COLLISION;
	}*/
	
    /**
     * Methode zum Zeichnen des Objekts
     */
	public void drawImg()
	{
		StdDraw.picture(posX, posY, "images/arena/Exit_Tile_"+2+".png");
	}
	
    /**
     * Methode, die zurueckgibt, ob das Objekt ein solides Objekt ist
     * (Zur Kollisionsabfrage)
     */
	public boolean isSolid()
	{
		return true;
	}
	
    /**
     * Gibt einen String-Namen des Objekts zurueck. (Kollisionsabfrage und Logik)
     */
	public String toString()
	{
		return "stairs";
	}
}
