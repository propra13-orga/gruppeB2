
public class Start 
{
	public static void main(String[] args) 
	{
		Manager_Sound snd = new Manager_Sound();
//		new MainMenu(717, 448);
		StdDraw.setCanvasSize(717, 448);
		new GameField(snd);
	}

}
