
public class Heart extends Block {

	private int posX, posY;
	
	private boolean disapear;
	
	public Heart(int posX, int posY)
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
		return "heart";
	}

	@Override
	void drawImg() {
		StdDraw.picture(posX, posY, "images/items/heart.png");
		// TODO Auto-generated method stub
		
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
