/**
 *  Respaesentiert das Shop-Objekt, welches Kisten und Faesser
 *  auf dem Spielfeld darstellt (bevorzugt beim Shop-NPC zu finden)
 */
public class Block_Shop extends Block_Block
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
     * Konstruktor eines Shop-Objekts
     *
     * @param posX - die x-Position des Bodens (Rectangle)
     * @param posY - die y-Position des Bodens (Rectangle)
     * @param sizeX - x-Groesse des Objekts
     * @param sizeY - y-Groesse des Objekts
     */
	public Block_Shop(int posX, int posY, int sizeX, int sizeY)
	{		
		//Das Floor-Objekt ist ein Block-Objekt an der Position posX und posY
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
			if(rand < 0.33)
				StdDraw.picture(posX, posY, "images/arena/Shop_"+1+".png");
			else if(rand < 0.66)
				StdDraw.picture(posX, posY, "images/arena/Shop_"+2+".png");
			else
				StdDraw.picture(posX, posY, "images/arena/Shop_"+3+".png");
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
		return "shop";
	}
}
