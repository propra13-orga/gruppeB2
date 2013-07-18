
public class MapEditCursor extends Block_Block
{
	private static final long serialVersionUID = 1L;

	private double posX, posY;

	public MapEditCursor(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY,sizeX, sizeY);

		this.posX = posX;
		this.posY = posY;
	}

	public MapEditCursor(int posX, int posY)
	{
		super(posX, posY, 40, 40);

		this.posX = posX;
		this.posY = posY;
	}

	public void drawImg()
	{
		StdDraw.picture(posX, posY, "images/mapedit/cursor.png");
	}

	@Override
	public boolean isSolid()
	{
		return false;
	}

	@Override
	public String toString()
	{
		return "cursor";
	}

	public void moveX(int x)
	{
		this.posX += x;
	}

	public void moveY(int y)
	{
		this.posY += y;
	}

	public void setPos(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
	}

	public double getPosX()
	{
		return posX;
	}

	public double getPosY()
	{
		return posY;
	}

}