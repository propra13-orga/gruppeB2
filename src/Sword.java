
public class Sword extends Block
{
	private static final long serialVersionUID = 1L;

	private int posX, posY;
	
	private boolean disapear;
	
	public Sword(int posX, int posY) {
		
		super(posX, posY, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	boolean isSolid() 
	{
		return true;
	}

	@Override
	public String toString() 
	{
		return "sword";
	}

	@Override
	void drawImg()
	{
		if(disapear==false)
		{
			StdDraw.picture(posX, posY, "images/items/sword.png");
		}else
			StdDraw.picture(posX, posY, "images/arena/Ground_Tile_4.png");
	}

	public boolean isDisapear()
	{
		return disapear;
	}
	
	public void setDisapear(boolean disapear)
	{
		this.disapear = disapear;
	}	

}
