import javax.swing.JOptionPane;
public class MainMenu
{
	/**
	 * Speichert die x- bzw. y-Position der Maus
	 */
	private double mouseX, mouseY;
	/**
	 * Speichert den Sound-Manager, der die Sound laedt und wiedergibt
	 */
	Manager_Sound snd;
	
	/**
	 * Konstruktor des MainMenu
	 * @param w - Fensterbreite
	 * @param h - Fensterhoehe
	 */
	public MainMenu(int w, int h)
	{
		snd = new Manager_Sound();
		StdDraw.setCanvasSize(w, h);
//		this.intro();
		this.run();
	}
	
	/**
	 * Zeichnet das Menu
	 */
	public void draw()
	{
		if(mouseX < 0.5 && mouseX > 0.28 && mouseY < 0.93 && mouseY > 0.88)
			StdDraw.picture(0.5, 0.5, "images/menu/main/Main_NewGame.png");
		else if(mouseX < 0.496 && mouseX > 0.28 && mouseY < 0.797 && mouseY > 0.752)
			StdDraw.picture(0.5, 0.5, "images/menu/main/Main_Load.png");
		else if(mouseX < 0.488 && mouseX > 0.281 && mouseY < 0.67 && mouseY > 0.622)
			StdDraw.picture(0.5, 0.5, "images/menu/main/Main_Settings.png");
		else if(mouseX < 0.485 && mouseX > 0.289 && mouseY < 0.544 && mouseY > 0.492)
			StdDraw.picture(0.5, 0.5, "images/menu/main/Main_End.png");
		else
			StdDraw.picture(0.5, 0.5, "images/menu/main/Main.png");
	}
	
	/**
	 * Spielschleife des MainMenu
	 */
	public void run()
	{
		while(true)
		{
			this.doLogic();
			this.draw();
		}
	}
	
	/**
	 * Behandelt die Logik des Menu (Mausklicks)
	 */
	public void doLogic()
	{
		mouseX = StdDraw.mouseX();
		mouseY = StdDraw.mouseY();
		
		if(mouseX < 0.5 && mouseX > 0.28 && mouseY < 0.93 && mouseY > 0.88 && StdDraw.mouseClicked())
		{
			snd.stopSound(6);
			new GameField(snd);
		}
		if(mouseX < 0.496 && mouseX > 0.28 && mouseY < 0.797 && mouseY > 0.752 && StdDraw.mouseClicked())
		{ 
			
		}
		if(mouseX < 0.488 && mouseX > 0.281 && mouseY < 0.67 && mouseY > 0.622 && StdDraw.mouseClicked())
		{ 
			new MapEditor();
		}
		if(mouseX < 0.485 && mouseX > 0.289 && mouseY < 0.544 && mouseY > 0.492 && StdDraw.mouseClicked())
			System.exit(0);
	}
	
	/**
	 * Spielt das Intro und den Sound ab
	 */
	public void intro()
	{
		try
		{
			for(int i = 1; i < 21; i++)
			{
				StdDraw.picture(0.5, 0.5, "images/intro/Sponge"+i+".png");
				Thread.sleep(100);
			}
			Thread.sleep(3000);
			for(int i = 20; i > 0; i--)
			{
				StdDraw.picture(0.5, 0.5, "images/intro/Sponge"+i+".png");
				Thread.sleep(100);
				
				if(i == 5)
					snd.playSound(6);
			}
		}catch(InterruptedException e)
		{
			JOptionPane.showMessageDialog(null, "Es trat ein unerwarteter Fehler auf.");
			System.exit(0);
		}
	}
}
