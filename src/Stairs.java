/**
 *  <i>Stairs</i>. Respaesentiert das Stairs-Objekt, welches den Eingang 
 *  eines neuen Levels darstellt. Eventuell, um zum vorherigen Level
 *  zu kommen.
 */
public class Stairs extends Block
{
	private static final long serialVersionUID = 1L;
	
	//x- und y-Position, an welcher der Eingang sich auf dem Spielfeld befindet.
	private double posX, posY;
	
    /**
     * Konstruktor eines Stairs-Objekts
     *
     * @param posX - die x-Position der Treppe (Rectangle)
     * @param posY - die y-Position der Treppe (Rectangle)
     */
	public Stairs(int posX, int posY)
	{
		//Das Stairs-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX, posY, 32, 32);
		
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
		StdDraw.picture(posX, posY, "images\\arena\\Exit_Tile_"+2+".png");
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
		return "stairs";
	}
}
