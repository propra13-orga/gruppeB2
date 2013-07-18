
/**
 * Stellt die einmaligen Heilpacks des Spielfeldes dar. Erben von der Klasse
 * Item.
 * @author Mike Bechtel
 *
 */
public class Item_Heart extends Item
{
	private static final long serialVersionUID = 1L;

	/**
	 * Speichern x- und y-Position auf dem Spielfeld
	 */
	private double posX, posY;	

	/**
	 * Konstruktor eines Heart-Objektes
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Item_Heart(int posX, int posY) 
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Prueft die Kollision mit dem Spieler
	 */
	public boolean checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			player.increaseHealth(20);
			return true;
		}
		else
			return false;
	}

	/**
	 * Gibt eine String-Repraesentation des Heart-Objekts zurueck
	 */
	public String toString() 
	{
		return "Heart";
	}

	/**
	 * Zeichnet das Heart-Objekt auf dem Spielfeld
	 */
	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/heart.png");
	}
}
