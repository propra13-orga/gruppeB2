
/**
 * Stellt die einaligen Manapacks des Spielfeldes dar. Erben von der Klasse
 * Item.
 * @author Mike Bechtel
 *
 */
public class Item_Mana extends Item
{
	private static final long serialVersionUID = 1L;

	/**
	 * Speichern x- und y-Position auf dem Spielfeld
	 */
	private int posX, posY;	
	
	/**
	 * Hilfsvariable zur Animation
	 */
	private int anim;

	/**
	 * Konstruktor eines Mana-Objektes
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Item_Mana(int posX, int posY) 
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
		
		anim = 0;
	}

	/**
	 * Prueft die Kollision mit dem Spieler
	 */
	public boolean checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			player.increaseMana(15);
			return true;
		}
		else
			return false;
	}

	/**
	 * Gibt eine String-Repraesentation des Mana-Objekts zurueck
	 */
	public String toString() 
	{
		return "Mana";
	}

	/**
	 * Zeichnet das Mana-Objekt auf dem Spielfeld (mit Animation)
	 */
	public void drawImg() 
	{
		if(anim < 15)
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+1+".png");
			anim++;
		}
		else if(anim < 30)
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+2+".png");
			anim++;
		}
		else if(anim < 45)
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+1+".png");
			anim++;
		}
		else 
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+3+".png");
			anim++;
			
			if(anim == 59)
				anim = 0;
		}
	}
}
