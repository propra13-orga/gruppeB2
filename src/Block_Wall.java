/**
 *  Respaesentiert das Wand-Objekt, welches die nicht begehbare Wand
 *  dastellt
 */
public class Block_Wall extends Block_Block
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert fuer jedes Floor-Objekt eine Zufallszahl, damit das dargestellte
	 * Image variiert
	 */
	private double rand;
	
	/**
	 * Speichert die x- und y-Position des Blocks auf dem Spielfeld
	 */
	private double posX, posY;
	
    /**
     * Konstruktor eines Wall-Objekts
     *
     * @param posX - die x-Position der Wand (Rectangle)
     * @param posY - die y-Position der Wand (Rectangle)
     * @param sizeX - x-Groesse des Objekts
     * @param sizeY - y-Groesse des Objekts
     */
	public Block_Wall(int posX, int posY, int sizeX, int sizeY)
	{
		//Das Wall-Objekt ist ein Block-Objekt an der Position posX und posY
		//mit der Groesse 32x32 Pixel (Rectangle)
		super(posX, posY, sizeX, sizeY);
		
		this.posX = posX;
		this.posY = posY;
		
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
