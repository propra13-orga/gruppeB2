import java.awt.Rectangle;

/**
 * Der CheckPoint NPC ist auf dem Spielfeld und ermoeglicht dem Spieler so das Speichern
 * des Spiels an entsprechender Position. Erbt von der Klasse NPC.
 * @author Mike Bechtel
 *
 */
public class NPC_CheckPoint extends NPC
{
	private static final long serialVersionUID = 1L;

	/**
	 * Hilfsvariable zur fluessigen Animation
	 */
	long delay;
	
	/**
	 * Aktionsradius des NPC
	 */
	private final double RANGE = 60;
	
	/**
	 * x- bzw y-Position des CheckPoints auf dem Spielfeld
	 */
	private double posX, posY;	
	
	/**
	 * Speichert den Dateipfad des Avatars
	 */
	private final String AVATAR = "images/npc/checkPoint/avatar.png";
	
	/**
	 * Speichert den Dialog in ein zweidimensionales String-Array
	 */
	private final String [][] DIALOG = new String[][]
			{
			{"Moechten Sie ihren Spielstand", "speichern?", " ", "[J] - Ja     [N] - Nein"},
			{"Spiel gespeichert!"}
			};
	
	/**
	 * Speichert, ob der NPC ein Speicherpunkt ist (Spieler hat bei dem NPC gespeichert)
	 */
	private boolean isSavePoint;
	
	/**
	 * Konstruktor eines CheckPoints
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param delta - Hilfsvariable zur fluessigen Animation
	 */
	public NPC_CheckPoint(int posX, int posY, long delta)
	{
		super(posX, posY, 40, 40);
		
		this.delay = delta;
		
		this.posX = posX;
		this.posY = posY;
		
		isSavePoint = false;
	}

	/**
	 * Konstruktor eine CheckPoints
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param sizeX - Tile-Groesse X
	 * @param sizeY - Tile-Groesse Y
	 * @param delta - Hilfsvariable zur fluessigen Animation
	 */
	public NPC_CheckPoint(int posX, int posY, int sizeX, int sizeY, long delta)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.delay = delta;
		
		this.posX = posX;
		this.posY = posY;
		
		isSavePoint = false;
	}
	
	/**
	 * Zeichnet den NPC
	 */
	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/npc/checkPoint/checkPoint_Down_2.png");
	}

	/**
	 * Prueft Kollisionen mit dem Rectangle <i>rect</i>
	 * @param rect - Rectangle, mit dem die Kollision geprueft wird
	 */
	public int checkCollision(Rectangle rect) 
	{
		if(this.intersects(rect))
		{
			double pX = rect.getCenterX();
			double pY = rect.getCenterY();
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
	 * Gibt zurueck, ob der Spieler sich im Aktionsradius befindet
	 * @param player - Player, der ueberprueft wird
	 */
	public boolean playerInRange(Player player) 
	{
		double abst = Math.sqrt(Math.pow((player.getCenterX() - this.getCenterX()), 2) + Math.pow((player.getCenterY() - this.getCenterY()), 2));
		
		if(abst <= RANGE)
			return true;
		else
			return false;
	}
	
	/**
	 * Gibt die x-Positions auf dem Spielfeld zurueck
	 */
	public double getX()
	{
		return this.posX;
	}

	/**
	 * Gibt die y-Positions auf dem Spielfeld zurueck
	 */
	public double getY()
	{
		return this.posY;
	}
	
	/**
	 * Gibt den Dateipfad des Avatars zurueck
	 */
	public String getAvatar()
	{
		return AVATAR;
	}
	
	/**
	 * Gibt die Dialogseite <i>page</i> zurueck
	 * @param page - Seite, die zurueckgegeben wird
	 */
	public String [] getDialog(int page)
	{
		if(page <= DIALOG.length)
			return DIALOG[page - 1];
		else
			return null;
	}

	/**
	 * Prueft, ob die Dialogseite <i>page</i> ein Auswahldialog ist
	 * @param page - Seite, die ueberprueft wird
	 * @return <b>true</b> wenn die Dialogseite Auswahldialog ist, <b>false</b> sonst
	 */
	public boolean isOptionDialog(int page)
	{
		if(page == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Setzt den CHeckPoint als Speicherpunkt
	 * @param player - Spieler, fuer den gespeichert wird
	 */
	public void setSavePoint(Player player)
	{
		this.isSavePoint = true;
	}
	
	/**
	 * Gibt zurueck, ob der CheckPoint auch ein Speicherpunkt ist
	 * @return <b>true</b> wenn der CheckPoint Speicherpunkt ist, <b>false</b> sonst
	 */
	public boolean isSavePoint()
	{
		return isSavePoint;
	}
}
