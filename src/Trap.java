/**
 *  <i>Trap</i>. Respaesentiert das Fallen-Objekt, welches eine einfache
 *  Bodenfalle auf dem Spielfeld darstellt.
 */
public class Trap extends Block 
{
	private static final long serialVersionUID = 1L;
	
	//x- und y-Position, an welcher der Eingang sich auf dem Spielfeld befindet.
	private double posX, posY;
	
    /**
     * Konstruktor eines Trap-Objekts
     *
     * @param posX - die x-Position der Falle (Rectangle)
     * @param posY - die y-Position der Falle (Rectangle)
     */
	public Trap(int posX, int posY, int sizeX, int sizeY)
	{
		//Das Trap-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX + 3, posY - 6, sizeX - (sizeX / 5), sizeY - (sizeY / 5));
		
		this.posX = posX;
		this.posY = posY;

		//Wird als versteckt initialisiert
		hidden = true;
	}
	
    /**
     * Methode zum Zeichnen des Objekts
     */	
	public void drawImg()
	{
		//if(!this.hidden)
			StdDraw.picture(posX, posY, "images/arena/Trap.png");		
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
		return "trap";
	}	
}
