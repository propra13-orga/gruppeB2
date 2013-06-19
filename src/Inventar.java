
public class Inventar extends Block
{
	private static final long serialVersionUID = 1L;

	private int posX, posY;
	
	private int inventar;
	
	public Inventar(int posX, int posY)
	{
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		
	}
	
	@Override
	boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "inventar";
	}


	@Override
	void drawImg() 
	{
		if(inventar<1)
			StdDraw.picture(posX, posY, "images/status/inventar1.png");
		else if(inventar>0)
			StdDraw.picture(posX, posY, "images/status/inventar1.1.png");
	}

	public int getInventar() {
		return inventar;
	}

	public void setInventar(int inventar) {
		this.inventar = inventar;
	}


}
