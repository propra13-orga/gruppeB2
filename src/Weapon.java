/**
 * Stellt ein Waffen-Objekt im Spiel dar
 * @author Mike Bechtel
 *
 */
public abstract class Weapon extends Collectable
{
private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert, ob die Waffe ausgereustet ist oder nicht
	 */
	protected boolean equipped;
	
	/**
	 * Speichert den Dateipfad des Bildes
	 */
	protected String picture;

	/**
	 * Konstruktor einer Waffe
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public Weapon(int posX, int posY) 
	{
		super(posX, posY, 32, 32, 1);
		
		this.equipped = false;
	}

	/**
	 * Konstruktor einer Waffe
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param anzahl - Stapelgroesse
	 */
	public Weapon(int posX, int posY, int anzahl) 
	{
		super(posX, posY, 32, 32, anzahl);
		
		this.equipped = false;
	}
	
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

	public abstract String toString(); 

	public abstract void drawImg();
	
	public boolean useItem(Player player) 
	{
		return false;
	}
	
	public abstract int getBonus();
	
	public boolean isEquipped()
	{
		return equipped;
	}
	
	public abstract int type();
}
