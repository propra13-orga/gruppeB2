
public class Inventar extends Block
{
	private static final long serialVersionUID = 1L;

	private int posX, posY;
	
	private int inventarSwrd;
	private int inventarArm;
	
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
		if(inventarSwrd+inventarArm==2)
			StdDraw.picture(posX, posY, "images/status/inventar1.3.png");
		else if(inventarArm == 1)
			StdDraw.picture(posX, posY, "images/status/inventar1.2.png");
		else if(inventarSwrd==1)
			StdDraw.picture(posX, posY, "images/status/inventar1.1.png");
		else 
			StdDraw.picture(posX, posY, "images/status/inventar1.png");		
	}

	public int getInventarSwrd() {
		return inventarSwrd;
	}

	public void setInventarSwrd(int inventarSwrd) {
		this.inventarSwrd = inventarSwrd;
	}
	
	public int getInventarArm() {
		return inventarArm;
	}

	public void setInventarArm(int inventarArm) {
		this.inventarArm = inventarArm;
	}


}
