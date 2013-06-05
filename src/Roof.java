/**
 *  <i>Roof</i>. Respaesentiert die Daecher
 */
public class Roof extends Block
{
	private static final long serialVersionUID = 1L;
	
	//x- und y-Position, an welcher der Ausgang sich auf dem Spielfeld befindet.
	private double posX, posY;
	
	//Ausrichtung des Daches (Linke Ecke, Rechte Ecke, Mittelteil, etc...)
	private int pos;
	
    /**
     * Konstruktor eines Wall-Objekts
     *
     * @param posX - die x-Position der Wand (Rectangle)
     * @param posY - die y-Position der Wand (Rectangle)
     */
	public Roof(int posX, int posY, int pos)
	{
		//Das Wall-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		
		//Wird als versteckt initialisiert
		hidden = true;	
		
		//Jedes Objekt bekommt eine Ausrichtung
		this.pos = pos;
	}
	
    /**
     * Methode zum Zeichnen des Objekts. Welches Bild das Objekt bekommt haengt
     * von der Zufallsvariable <i>rand</i> ab.
     */
	public void drawImg()
	{
		if(pos == 1)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_RM.png");
		else if(pos == 2)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_CL.png");
		else if(pos == 3)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_OM.png");
		else if(pos == 4)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_CR.png");
		else if(pos == 5)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_LM.png");
		else if(pos == 6)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_LRM.png");
		else if(pos == 7)
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_LRO.png");
		else
			StdDraw.picture(posX, posY, "images/arena/Roof_Tile_M.png");			
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
		return "roof";
	}
}
