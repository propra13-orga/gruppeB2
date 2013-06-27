import java.awt.Rectangle;


public class CheckPointNPC extends NPC
{
	private static final long serialVersionUID = 1L;

	long delay;
	private final double RANGE = 60;
	
	boolean moves;
	int moveDirection;
	
	private double posX, posY;	
	
	private final String AVATAR = "images/npc/checkPoint/avatar.png";
	
	private final String [][] DIALOG = new String[][]
			{
			{"Moechten Sie ihren Spielstand", "speichern?", " ", "[J] - Ja     [N] - Nein"},
			{"Spiel gespeichert!"}
			};
	
	private boolean isSavePoint;
	
	public CheckPointNPC(int posX, int posY, long delta)
	{
		super(posX, posY, 40, 40);
		
		this.delay = delta;
		
		this.posX = posX;
		this.posY = posY;
		
		isSavePoint = false;
	}

	public CheckPointNPC(int posX, int posY, int sizeX, int sizeY, long delta)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.delay = delta;
		
		this.posX = posX;
		this.posY = posY;
		
		isSavePoint = false;
	}

	public CheckPointNPC(int posX, int posY, int sizeX, int sizeY, int moveDirection, long delay)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.delay = delay;
		
		this.posX = posX;
		this.posY = posY;
		
		moves = true;
		this.moveDirection = moveDirection;
		
		isSavePoint = false;
	}

	public void drawImg() 
	{
		StdDraw.picture(posX, posY, "images/npc/checkPoint/checkPoint_Down_2.png");
	}

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

	public boolean playerInRange(Player player) 
	{
		double abst = Math.sqrt(Math.pow((player.getCenterX() - this.getCenterX()), 2) + Math.pow((player.getCenterY() - this.getCenterY()), 2));
		
		if(abst <= RANGE)
			return true;
		else
			return false;
	}

	public String interactWithPlayer() 
	{
		if(StdDraw.isKeyPressedSingle('j'))
			return Dialog.APPROVE;
		else if(StdDraw.isKeyPressedSingle('n'))
			return Dialog.ABORT;
		else
			return Dialog.NO_OPT;
	}
	
	public double getX()
	{
		return this.posX;
	}
	
	public double getY()
	{
		return this.posY;
	}
	
	public String getAvatar()
	{
		return AVATAR;
	}
	
	public String [] getDialog(int page)
	{
		if(page <= DIALOG.length)
			return DIALOG[page - 1];
		else
			return null;
	}
	
	public boolean isOptionDialog(int page)
	{
		if(page == 1)
			return true;
		else
			return false;
	}
	
	public void setSavePoint(Player player)
	{
		this.isSavePoint = true;
	}
	
	public boolean isSavePoint()
	{
		return isSavePoint;
	}
}
