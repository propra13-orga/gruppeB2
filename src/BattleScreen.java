import java.awt.Color;


public class BattleScreen 
{
	Enemy enemy;
	
	private double width, heigth;
	double time;
	
	KeyManager key;
	
	public BattleScreen(GameField field, Enemy enemy)
	{
		this.enemy = enemy;
		
		width = field.columns * 40;
		heigth = (field.rows + 2) * 40 + 80;
		
		key = new KeyManager(this);
		
		time = 0;
		
		this.playBlending(0);
		this.run();
	}
	
	private void playBlending(int type)
	{	
		for(int i = 1; i <= 50; i++)
		{
			StdDraw.show(50);
			{
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.filledRectangle(width / 2, heigth / 2, i*(width / 80), i*(heigth / 80));
			}
		}
	}
	
	private void playIntro()
	{
		StdDraw.show(5);
		{
			StdDraw.picture(width / 2, heigth / 2 - 40, "images/battle/intro/intro_screen.png");
			
			if(time < 440)
				time = time + 3;
				
			StdDraw.picture(width / 2 - 270 + time, heigth / 2 + 60, "images/enemy/" + enemy.toString() + "/battle.png");
			
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.filledRectangle(width / 2 - 270, heigth / 2 - 40, 30.5, heigth / 2);
		}
	}
	
	private void run()
	{
		while(true)
		{
			key.handleKeyInput();
			this.playIntro();
		}
	}
}
