
/**
 * Stellt die einsammelbaren Muenzen des Spielfeldes dar. Erben von der Klasse
 * Item.
 * @author Mike Bechtel
 *
 */
public class Item_Coin extends Item
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichern x- und y-Position auf dem Spielfeld
	 */
	private double posX, posY;	
	
	/**
	 * Speichert den Muenzwert des Coins-Objekts
	 */
	private final int value;

	/**
	 * Konstruktor eines Coin-Objektes
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Item_Coin(int posX, int posY) 
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
		
		//Zufaelliger Muenzhaufen (zwischen 10 und 100 Muenzen)
		value = (int)(Math.random() * ((100 - 10) + 1) + 10);
	}
	
	/**
	 * Konstruktor eines Coin-Objektes
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param sizeX - x-Groesse des Objekts
	 * @param sizeY - y-Groesse des Objekts
	 * @param value - Muenzwert der Coins
	 */
	public Item_Coin(int posX, int posY, int sizeX, int sizeY, int value)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.value = value;
	}
	
	/**
	 * Gibt den Muenzwert der Coins zurueck
	 * @return Muenzwert
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Prueft die Kollision mit dem Spieler
	 */
	public boolean checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			player.increaseCoins(value);
			return true;
		}
		else
			return false;
	}

	/**
	 * Gibt eine String-Repraesentation des Coin-Objekts zurueck
	 */
	public String toString() 
	{
		return "Coin";
	}

	/**
	 * Zeichnet das Coins-Objekt (je nach Wert ein anderes Bild)
	 */
	public void drawImg() 
	{
		if(value < 50)
			StdDraw.picture(posX, posY, "images/items/coins_small.png");
		else if(value < 80)
			StdDraw.picture(posX, posY, "images/items/coins_mid.png");
		else
			StdDraw.picture(posX, posY, "images/items/coins_big.png");
	}
}
