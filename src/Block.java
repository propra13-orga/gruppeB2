import java.awt.Rectangle;


abstract class Block extends Rectangle
{
	protected boolean hidden;
	
	public Block(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}
	
	public boolean isHidden()
	{
		return hidden;
	}
	
	public void setHidden(boolean status)
	{
		hidden = status;
	}
	
	abstract boolean isSolid();
	abstract boolean isExit();
	public abstract String toString();
	abstract void drawImg();
}
