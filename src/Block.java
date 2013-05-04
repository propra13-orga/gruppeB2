
abstract class Block 
{
	protected boolean hidden;
	
	public boolean isHidden()
	{
		return hidden;
	}
	
	public void setHidden(boolean status)
	{
		hidden = status;
	}
	
	abstract boolean isSolid();
	abstract void drawImg();
}
