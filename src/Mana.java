
public class Mana extends Block 
{
	private int posX, posY;
	
	private int nrg;
	
	public Mana(int posX, int posY)
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
		return "energy";
	}
	
	@Override
	void drawImg() 
	{
		if(nrg==1)
			StdDraw.picture(posX, posY, "images/status/manaStatus1.png");
		else if(nrg==2)
			StdDraw.picture(posX, posY, "images/status/manaStatus2.png");
		else if(nrg==3)
			StdDraw.picture(posX, posY, "images/status/manaStatus3.png");
		// TODO Auto-generated method stub
		
	}

	public int getNrg() {
		return nrg;
	}

	public void setNrg(int nrg) {
		this.nrg = nrg;
	}

}
