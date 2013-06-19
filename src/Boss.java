import java.awt.Rectangle;


public class Boss extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	
	private double posX, posY;
	
	private Fireball fire;
	
	private final int SPEED = 2;
	
	private int swap;
	
	//noch ohne Auswirkung
	private boolean exists;

	//noch ohne Auswirkung
	private double health = 100.0;
	
	//noch ohne Auswirkung
//	private boolean colUp, colDown, colRight, colLeft;
	
	public Boss(int posX, int posY)
	{
		super(posX, posY, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
		
		fire = new Fireball(posX, posY);
	}
	
	public void draw()
	{
		if(swap < 8) 
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+1+".png");
			swap++;
		}
		else if(swap < 16)
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+2+".png");
			swap++;
		}
		else if(swap < 24)
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+3+".png");
			swap++;
		}
		else if(swap < 32)
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+4+".png");
			swap++;
		}
		else if(swap < 40)
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+5+".png");
			swap++;
		}
		else if(swap < 48)
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+4+".png");
			swap++;
		}
		else
		{
			StdDraw.picture(posX, posY, "images/enemy/satan_"+2+".png");
			swap++;
			
			if(swap == 56)
				swap = 0;					
		}
	}
		
		public double getPosX()
		{
			return posX;
		}

		public void setPosX(int posX)
		{
			this.posX = posX;
		}

		public double getPosY()
		{
			return posY;
		}

		public void setPosY(int posY)
		{
			this.posY = posY;
		}
		
		public boolean isSolid()
		{
			return true;
		}

		public Fireball getFire() {
			return fire;
		}

		public void setFire(Fireball fire) {
			this.fire = fire;
		}

		public boolean isExists() {
			return exists;
		}

		public void setExists(boolean exists) {
			this.exists = exists;
		}
		
		public double getHealth() {
			return health;
		}

		public void setHealth(double health) {
			this.health = health;
		}

		public int getSPEED() {
			return SPEED;
		}
}
