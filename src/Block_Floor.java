/**
 *  <i>Floor</i>. Respaesentiert das Boden-Objekt, welches den begehbaren
 *  Untergrund des Spielfeldes darstellt.
 */
public class Block_Floor extends Block_Block
{
	private static final long serialVersionUID = 1L;
	
	//Zufallsvariable um jedem Bodenobjekt ein zufaelliges Bild zuzuordnen
	//(damit die Optik nicht allzu monoton aussieht)
	private double rand;
	
	//x- und y-Position, an welcher der Ausgang sich auf dem Spielfeld befindet.
	private double posX, posY;
	
    /**
     * Konstruktor eines Floor-Objekts
     *
     * @param posX - die x-Position des Bodens (Rectangle)
     * @param posY - die y-Position des Bodens (Rectangle)
     */
	public Block_Floor(int posX, int posY, int sizeX, int sizeY)
	{		
		//Das Floor-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX, posY, sizeX, sizeY);
		
		this.posX = posX;
		this.posY = posY;
		
		//Wird als versteckt initialisiert
		hidden = true;	
		
		//Jedes Objekt bekommt ein zufaelliges Bild
		rand = Math.random();
	}
	
    /**
     * Methode zum Zeichnen des Objekts. Welches Bild das Objekt bekommt haengt
     * von der Zufallsvariable <i>rand</i> ab.
     */
	public void drawImg()
	{
		//if(!this.hidden)
			if(rand < 0.25)
				StdDraw.picture(posX, posY, "images/arena/Ground_Tile_"+1+".png");
			else if(rand < 0.5)
				StdDraw.picture(posX, posY, "images/arena/Ground_Tile_"+2+".png");
			else if(rand < 0.75)
				StdDraw.picture(posX, posY, "images/arena/Ground_Tile_"+3+".png");
			else
				StdDraw.picture(posX, posY, "images/arena/Ground_Tile_"+4+".png");
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
		return "floor";
	}
}
