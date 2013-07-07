import java.awt.Rectangle;

/**
 *  <i>Door</i>. Respaesentiert das Tuer-Objekt zum Wechsel zwischen den
 *  einzelnen Leveln.
 */
public class Block_Door extends Block_Block
{
	private static final long serialVersionUID = 1L;
	
	//x- und y-Position, an welcher der Ausgang sich auf dem Spielfeld befindet.
	private double posX, posY;
	
    /**
     * Konstruktor eines Door-Objekts
     *
     * @param posX - die x-Position der Tuer (Rectangle)
     * @param posY - die y-Position der Tuer (Rectangle)
     */
	public Block_Door(int posX, int posY, int sizeX, int sizeY)
	{
		//Das Tuer-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX, posY, sizeX, sizeY);
		
		this.posX = posX;
		this.posY = posY;		
	}
	
	public int checkCollision(Rectangle rect)
	{
		if(this.intersects(rect) && rect instanceof Player)
		{
			return Direction.COLLIDE_DOOR;
		}
		else
			return Direction.NO_COLLISION;
	}
	
    /**
     * Methode zum Zeichnen des Objekts
     */
	public void drawImg()
	{
		StdDraw.picture(posX, posY, "images/arena/Exit_Tile_"+1+".png");
	}
	
    /**
     * Methode, die zurueckgibt, ob das Objekt ein solides Objekt ist
     * (Zur Kollisionsabfrage)
     */
	public boolean isSolid()
	{
		return false;
	}
	
    /**
     * Gibt einen String-Namen des Objekts zurueck. (Kollisionsabfrage und Logik)
     */
	public String toString()
	{
		return "door";
	}
}
