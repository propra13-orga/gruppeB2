import java.awt.event.*;

public class MainMenu
{
	private static final long serialVersionUID = 1L;
	
	private double mouseX, mouseY;
	
	public MainMenu(int w, int h)
	{
		StdDraw.setCanvasSize(w, h);
		this.run();
	}
	
	public void draw()
	{
		if(mouseX < 0.5 && mouseX > 0.28 && mouseY < 0.93 && mouseY > 0.88)
			StdDraw.picture(0.5, 0.5, "images\\menu\\main\\Main_NewGame.png");
		else if(mouseX < 0.496 && mouseX > 0.28 && mouseY < 0.797 && mouseY > 0.752)
			StdDraw.picture(0.5, 0.5, "images\\menu\\main\\Main_Load.png");
		else if(mouseX < 0.488 && mouseX > 0.281 && mouseY < 0.67 && mouseY > 0.622)
			StdDraw.picture(0.5, 0.5, "images\\menu\\main\\Main_Settings.png");
		else if(mouseX < 0.485 && mouseX > 0.289 && mouseY < 0.544 && mouseY > 0.492)
			StdDraw.picture(0.5, 0.5, "images\\menu\\main\\Main_End.png");
		else
			StdDraw.picture(0.5, 0.5, "images\\menu\\main\\Main.png");
	}
	
	public void run()
	{
		while(true)
		{
			this.doLogic();
			this.draw();
		}
	}
	
	public void doLogic()
	{
		mouseX = StdDraw.mouseX();
		mouseY = StdDraw.mouseY();
		
		if(mouseX < 0.5 && mouseX > 0.28 && mouseY < 0.93 && mouseY > 0.88 && StdDraw.mouseClicked())
		{
			new GameField();
		}
		if(mouseX < 0.496 && mouseX > 0.28 && mouseY < 0.797 && mouseY > 0.752 && StdDraw.mouseClicked())
		{ 
			
		}
		if(mouseX < 0.488 && mouseX > 0.281 && mouseY < 0.67 && mouseY > 0.622 && StdDraw.mouseClicked())
		{ 
			
		}
		if(mouseX < 0.485 && mouseX > 0.289 && mouseY < 0.544 && mouseY > 0.492 && StdDraw.mouseClicked())
			System.exit(0);
	}
}
