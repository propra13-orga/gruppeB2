import java.awt.Rectangle;


public class NPC_Shop extends NPC
{
	private static final long serialVersionUID = 1L;

	private final double RANGE = 50;
	
	boolean moves;
	int moveDirection;
	
	private double posX, posY;	
	
	private final String AVATAR = "images/npc/shop/avatar.png";
	
	private String direction;
	
	private final String [][] DIALOG = new String[][]
			{
			{"Moechtest du einen Blick auf", "meine Waren werfen?", " ", "[J] - Ja     [N] - Nein"}
			};
	
	public NPC_Shop(int posX, int posY, String direction)
	{
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		
		this.direction = direction;
	}

	public NPC_Shop(int posX, int posY, int sizeX, int sizeY, String direction)
	{
		super(posX, posY, sizeX, sizeY);
		
		this.posX = posX;
		this.posY = posY;
		
		this.direction = direction;
	}

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
}
