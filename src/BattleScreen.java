import java.awt.Color;


public class BattleScreen 
{
	private double width, heigth;
	
	public BattleScreen(GameField field)
	{
		width = field.columns * 40;
		heigth = (field.rows + 2) * 40 + 80;
		
		this.playIntro();
	}
	
	private void playIntro()
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
}
