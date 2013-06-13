
public class Npc extends Block
{
	private int posX, posY;
	
	private boolean help;
	

	public Npc(int posX, int posY) 
	{
		
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;

	}

	@Override
	boolean isSolid() 
	{
		return true;
	}

	@Override
	public String toString() 
	{
		return "npc";
	}

	@Override
	void drawImg()
	{
		if(help==true)
		{
			StdDraw.picture(posX, posY, "images/npc/npc2.png");
		}
		else
			StdDraw.picture(posX, posY, "images/npc/npc.png");
	}
	
	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}
}
