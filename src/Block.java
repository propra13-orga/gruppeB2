import java.awt.Rectangle;

/**
 *  <i>Block</i>. Diese abstrakte Klasse stellt die Spielbloecke des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen zu realisieren.
 *  Die Methoden <i>isHidden</i> und <i>setHidden</i> werden hier direkt implementiert,
 *  da ausnahmslos jeder Block diese Methoden besitzen muss (zur korrekten Spielfeld-
 *  zeichnung).
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften des
 *  speziellen Blocks fuer die Logik des Spiels.
 *  </p>
 */
abstract class Block extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	//Speichert einen Wahrheitswert, ob dieser Block gerade auf dem Spielfeld zu sehen
	//ist (gezeichnet werden soll) oder nicht
	protected boolean hidden;
	
    /**
     * Konstruktor eines Block-Objekts
     *
     * @param posX - die x-Position des Blocks (Rectangle)
     * @param posY - die y-Position des Blocks (Rectangle)
     * @param sizeX - die x-Groesse des Blocks fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Blocks fuer die Kollision(Rectangle)
     */
	public Block(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}
	
	public int checkCollision(Rectangle rect, long delta)
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
	
	public abstract boolean isSolid();
	public abstract String toString();
	public abstract void drawImg();
}
