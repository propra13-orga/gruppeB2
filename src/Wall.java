/**
 *  <i>Wall</i>. Respaesentiert das Wand-Objekt, welches die nicht begehbare Wand
 *  dastellt
 */
public class Wall extends Block
{
	private static final long serialVersionUID = 1L;
	
	//Zufallsvariable um jedem Bodenobjekt ein zufaelliges Bild zuzuordnen
	//(damit die Optik nicht allzu monoton aussieht)
	private double rand;
	
	//x- und y-Position, an welcher der Ausgang sich auf dem Spielfeld befindet.
	private double posX, posY;
	
    /**
     * Konstruktor eines Wall-Objekts
     *
     * @param posX - die x-Position der Wand (Rectangle)
     * @param posY - die y-Position der Wand (Rectangle)
     */
	public Wall(int posX, int posY, int sizeX, int sizeY)
	{
		//Das Wall-Objekt ist ein Block-Objekt an der Position posX und posY
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
			if(rand < 0.05)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+5+".png");
			else if(rand < 0.1)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+6+".png");
			else if(rand < 0.15)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+7+".png");
			else if(rand < 0.2)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+8+".png");
			else if(rand < 0.40)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+1+".png");
			else if(rand < 0.60)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+2+".png");
			else if(rand < 0.80)
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+3+".png");
			else
				StdDraw.picture(posX, posY, "images/arena/Wall_Tile_"+4+".png");
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
		return "wall";
	}
}
