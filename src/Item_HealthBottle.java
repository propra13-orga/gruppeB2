
/**
 * Stellt die einsammelbaren Lebensportionen des Spielfeldes dar. Erben von der Klasse
 * Item.
 * @author Mike Bechtel
 *
 */
public class Item_HealthBottle extends Collectable
{
	private static final long serialVersionUID = 1L;

	/**
	 * Speichern x- und y-Position auf dem Spielfeld
	 */
	private double posX, posY;	

	/**
	 * Konstruktor eines HealthBottle-Objektes
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Item_HealthBottle(int posX, int posY) 
	{
		super(posX, posY, 32, 32, 1);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/health_bottle.png";
	}
	
	/**
	 * Konstruktor eines HealthBottle-Objektes
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param anzahl - Stapelgroesse
	 */
	public Item_HealthBottle(int posX, int posY, int anzahl) 
	{
		super(posX, posY, 32, 32, anzahl);
		
		this.posX = posX;
		this.posY = posY;
		
		this.picture = "images/items/health_bottle.png";
	}
	
	/**
	 * Prueft die Kollision mit dem Spieler
	 */
	public int checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			double pX = player.getCenterX();
			double pY = player.getCenterY();
			double fX = this.getCenterX();
			double fY = this.getCenterY();
			
			if(pX > fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_LEFT;
			if(pX < fX && pY >= fY - 20 && pY <= fY + 20)
				return Direction.COLLIDE_RIGHT;
			if(pY > fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_DOWN;
			if(pY < fY && pX >= fX - 20 && pX <= fX + 20)
				return Direction.COLLIDE_UP;
			else
				return Direction.NO_COLLISION;
		}
		else
			return Direction.NO_COLLISION;
	}

	/**
	 * Gibt eine String-Repraesentation des HealthBottle-Objekts zurueck
	 */
	public String toString() 
	{
		return "Heiltrank";
	}

	/**
	 * Zeichnet das HealthBottle-Objekt auf dem Spielfeld
	 */
	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/items/health_bottle.png");
	}

	/**
	 * Gibt zurueck, ob das Objekt vom Spieler benutzt wurde und fuehrt auch
	 * ggf. direkt eine entsprechende Aktion aus
	 */
	public boolean useItem(Player player) 
	{
		if(player.getHealth() < player.getMaxHealth())
		{
			player.increaseHealth(0.5 * player.getMaxHealth());
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Gibt den Bonus des Objektes zurueck
	 */
	public int getBonus()
	{
		return 50;
	}

	/**
	 * Gibt eine Typbeschreibung des Objektes zurueck
	 */
	public int type()
	{
		return CollectableTypes.HEALTH_BOTTLE;
	}
	
	/**
	 * Gibt zurueck, ob das Item ausgeruestet ist.
	 */
	public boolean isEquipped() 
	{
		return false;
	}
}
