
public class Coins extends Block
{
	private int posX, posY;
	
	private boolean disapear;
	
	public Coins(int posX, int posY) {
		
		super(posX, posY, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8130296753230054074L;

	@Override
	boolean isSolid() 
	{
		return false;
	}

	@Override
	public String toString() 
	{
		return "coin";
	}

	@Override
	void drawImg()
	{
		if(disapear==false)
		{
			StdDraw.picture(posX, posY, "images\\items\\coins.png");
		}else
			StdDraw.picture(posX, posY, "images\\arena\\Ground_Tile_4.png");
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
