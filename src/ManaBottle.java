
public class ManaBottle extends Block
{
	private int posX, posY;
	
	private boolean disapear;
	
	private int sw;
	
	public ManaBottle(int posX, int posY)
	{
		super(posX, posY, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "mana";
	}

	@Override
	void drawImg() 
	{
		if(sw<6)
		{
			StdDraw.picture(posX, posY, "images/items/manaGlas"+1+".png");
			sw++;
		}
		else if(sw<12)
		{
			StdDraw.picture(posX, posY, "images/items/manaGlas"+2+".png");
			sw++;
		}
		else if(sw<18)
		{
			StdDraw.picture(posX, posY, "images/items/manaGlas"+3+".png");
			sw++;
		}
		else 
		{
			StdDraw.picture(posX, posY, "images/items/manaGlas"+2+".png");
			sw++;
			
			if(sw==24)
				sw=0;
		}
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isDisapear() {
		return disapear;
	}

	public void setDisapear(boolean disapear) {
		this.disapear = disapear;
	}

}
