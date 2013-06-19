
public class Energy extends Block 
{
	private static final long serialVersionUID = 1L;

	private int posX, posY;
	
	private int nrg;
	
	public Energy(int posX, int posY)
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
			StdDraw.picture(posX, posY, "images/status/energyStatus1.png");
		else if(nrg==2)
			StdDraw.picture(posX, posY, "images/status/energyStatus2.png");
		else if(nrg==3)
			StdDraw.picture(posX, posY, "images/status/energyStatus3.png");
		else if(nrg==4)
			StdDraw.picture(posX, posY, "images/arena/unseen.png");
		
	}

	public int getNrg() {
		return nrg;
	}

	public void setNrg(int nrg) {
		this.nrg = nrg;
	}


}
