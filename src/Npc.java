import java.awt.event.KeyEvent;


public class Npc extends Block
{
	private int posX, posY, pX, pY;
	
	private boolean help;
	
	private boolean helpDisplay;

	public Npc(int posX, int posY) 
	{
		
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
		pX = 0;
		pY = 0;
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
			if(helpDisplay==true)
				{
					StdDraw.picture(pX-64, pY, "images/npc/npcActive.png");
					if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
						StdDraw.picture(pX-64, pY, "images/npc/npcActive2.png");
				}
				
		}
		else
			StdDraw.picture(posX, posY, "images/npc/npc.png");
	}
	
	public boolean isHelp()
	{
		return help;
	}

	public void setHelp(boolean help
			) {
		this.help = help;
	}
	
	public boolean isHelpDisplay() 
	{
		return helpDisplay;
	}

	public void setHelpDisplay(boolean helpDisplay, int posX, int posY)
	{
		this.helpDisplay = helpDisplay;
		this.pX = posX;
		this.pY = posY;
	}
}
