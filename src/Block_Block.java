import java.awt.Rectangle;

/**
 *  Diese abstrakte Klasse stellt die Spielbloecke des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen zu realisieren.
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften des
 *  speziellen Blocks fuer die Logik des Spiels.
 *  </p>
 */
abstract class Block_Block extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
    /**
     * Konstruktor eines Block-Objekts
     *
     * @param posX - die x-Position des Blocks (Rectangle)
     * @param posY - die y-Position des Blocks (Rectangle)
     * @param sizeX - die x-Groesse des Blocks fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Blocks fuer die Kollision(Rectangle)
     */
	public Block_Block(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}
	
	/**
	 * Prueft die Kollision des Block-Objektes mit dem uebergebenen Rectangle.
	 * 
	 * @param rect - Das Rectangle-Objekt fuer welches die Kollision geprueft wird
	 */
	public int checkCollision(Rectangle rect)
	{	
		if(this.intersects(rect) && this.isSolid())
		{
			double pX = rect.getCenterX();
			double pY = rect.getCenterY();
			double fX = this.getCenterX();
			double fY = this.getCenterY();
			
			if(pX > fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_LEFT;
			if(pX < fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_RIGHT;
			if(pY > fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_DOWN;
			if(pY < fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_UP;
			else
				return Direction.NO_COLLISION;
		}
		else
			return Direction.NO_COLLISION;
	}
	
	//-------------------------------------------------------------------------
	
	/**
	 * Gibt zurueck, ob der Block als solide gilt
	 * @return <b>true</b> wenn der Block solide ist, <b>false</b> wenn nicht
	 */
	public abstract boolean isSolid();
	
	/**
	 * Gibt eine String-Repraesentation des Blocks zurueck
	 */
	public abstract String toString();
	
	/**
	 * Zeichnet den Block auf das Spielfeld
	 */
	public abstract void drawImg();
}
