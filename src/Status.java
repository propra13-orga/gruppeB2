
public class Status extends Block{

	private int posX, posY;
	
	public Status(int posX, int posY){
		
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
	}

	@Override
	boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "status";
	}

	@Override
	void drawImg() {
		// TODO Auto-generated method stub
		StdDraw.picture(posX, posY, "images/arena/unseen.png");
	}
}
