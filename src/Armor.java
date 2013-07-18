/**
 * Abstrakte Ruestungsklasse, die die grundlegenden Eigenschaften einer
 * Ruestung beschreibt.
 * 
 * @author Mike Bechtel
 *
 */
public abstract class Armor extends Collectable
{
private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert, ob die Ruestung angelegt ist oder nicht.
	 */
	protected boolean equipped;
	
	/**
	 * Speichert den String-Pfad des darzustellenden Bildes
	 */
	protected String picture;
	
	/**
	 * Speichert den Ruestungstyp
	 */
	protected String type;

	/**
	 * Konstruktor eines abstrakten Armor-Objektes.
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Armor(int posX, int posY) 
	{
		super(posX, posY, 32, 32, 1);
		
		this.equipped = false;
	}

	/**
	 * Konstruktor eines abstrakten Armor-Objektes.
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param anzahl - Groesse des Armor-Stapels
	 */
	public Armor(int posX, int posY, int anzahl) 
	{
		super(posX, posY, 32, 32, anzahl);
		
		this.equipped = false;
	}
	
	/**
	 * Prueft die Kollision des Armor-Objektes mit dem Spieler.
	 * 
	 * @param player - Das Player-Objekt fuer welches die Kollision geprueft wird
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
	 * Liefert eine String-Repraesentation des Armor-Objekts.
	 */
	public abstract String toString(); 

	/**
	 * Zeichnet das zugehoerige Bild des Armor-Objektes auf das Spielfeld.
	 */
	public abstract void drawImg();
	
	/**
	 * Uberschriebene Methode der Oberklasse Collectable.
	 * Nicht benoetigt fuer ein Armor-Objekt.
	 */
	public boolean useItem(Player player) 
	{
		return false;
	}
	
	/**
	 * Gibt den Ruestungsbonus der Armor zurueck.
	 */
	public abstract int getBonus();
	
	/**
	 * Gibt ein <b>boolean</b> zurueck, ob die Ruestung angelegt ist oder nicht.
	 */
	public boolean isEquipped()
	{
		return equipped;
	}
	
	/**
	 * Gibt die Typbeschreibung des Armor-Objektes zurueck.
	 */
	public abstract int type();
	
	/**
	 * Gibt den Ruestungstyp zur Schadensberechnung wieder
	 * @return Ruestungstyp
	 */
	public String getType()
	{
		return this.type;
	}
}
