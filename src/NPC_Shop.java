import java.awt.Rectangle;

/**
 * Der Shop NPC ist auf dem Spielfeld und ermoeglicht dem Spieler das Einkaufen von
 * Items, Waffen und Ruestungen. Erbt von der Klasse NPC.
 * @author Mike Bechtel
 *
 */
public class NPC_Shop extends NPC
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Aktionsradius des NPC
	 */
	private final double RANGE = 50;
	
	/**
	 * x- bzw y-Position des Shops auf dem Spielfeld
	 */
	private double posX, posY;	
	
	/**
	 * Speichert den Dateipfad des Avatars
	 */
	private final String AVATAR = "images/npc/shop/avatar.png";
	
	/**
	 * Blickrichtung des Shop-NPCs
	 */
	private String direction;
	
	/**
	 * Speichert den Dialog in ein zweidimensionales String-Array
	 */	
	private final String [][] DIALOG = new String[][]
			{
			{"Moechtest du einen Blick auf", "meine Waren werfen?", " ", "[J] - Ja     [N] - Nein"}
			};
	
	/**
	 * Konstruktor eines Shops
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param delta - Hilfsvariable zur fluessigen Animation
	 */
	public NPC_Shop(int posX, int posY, String direction)
	{
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		
		this.direction = direction;
	}


	/**
	 * Konstruktor eine Shops
	 * @param posX - x-Position auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 * @param sizeX - Tile-Groesse X
	 * @param sizeY - Tile-Groesse Y
	 * @param delta - Hilfsvariable zur fluessigen Animation
	 */
	public NPC_Shop(int posX, int posY, int sizeX, int sizeY, String direction)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.direction = direction;
	}

	/**
	 * Zeichnet den Shop in Abhaengigkeit der Blickrichtung
	 */
	public void drawImg() 
	{
		switch(direction)
		{
		case Direction.DOWN: StdDraw.picture(posX, posY, "images/npc/shop/shop_down.png"); break;
		case Direction.UP: StdDraw.picture(posX, posY, "images/npc/shop/shop_uo.png"); break;
		case Direction.LEFT: StdDraw.picture(posX, posY, "images/npc/shop/shop_left.png"); break;
		case Direction.RIGHT: StdDraw.picture(posX, posY, "images/npc/shop/shop_right.png"); break;
		}
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
}
