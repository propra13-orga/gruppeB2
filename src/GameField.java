import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameField extends JFrame
{
	private static final long serialVersionUID = 9136636406585847854L;
	
	char [][] feld;
	Block[][] field;
	
	ReadLevel lvl;
	
	int rows, columns;
	
	int playerX, playerY;
			
	public GameField()
	{
		lvl = new ReadLevel();
		
		this.loadLevel("lvl1.txt");
		this.initField();
		this.run();
	}
	
	public void loadLevel(String level)
	{
		feld = lvl.readLevel(level);
		
		if(feld == null)
		{
			JOptionPane.showMessageDialog(this, "Die Datei konnte nicht geladen werden oder beinhaltet kein quadratisches Spielfeld.");
			System.exit(0);
		}
	}
	
    /**
     * Initialisiert das Spielfeld in einem Array. Sollte sp�ter aus
     * einer Datei eingelesen werden k�nnen.
     *
     */
	public void initField()
	{	
		columns = feld[0].length;
		rows = feld.length;
		
		field = new Block[rows][columns];
		
		StdDraw.setCanvasSize(32 * columns, 32 * rows);
		StdDraw.setXscale(0, 32 * columns);
		StdDraw.setYscale(0, 32 * rows);
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				int posX = j * 32 + 16;
				int posY = (32 * rows)-(i * 32 + 16);
				
				if(feld[i][j] == ' ')
					field[i][j] = new Floor(posX, posY);
				else if(feld[i][j] == 'x'||feld[i][j] == 'X')
					field[i][j] = new Wall(posX, posY);
				else if(feld[i][j] == 'e'||feld[i][j] == 'E')
					field[i][j] = new Door(posX, posY);
				else if(feld[i][j] == 't'||feld[i][j] == 'T')
					field[i][j] = new Stairs(posX, posY);
			}
		}
		
		playerX = 16;
		playerY = 16;
	}
	
    /**
     * Methode, die das Spielfeld mit StdDraw zeichnet. Dabei wird das Array "field"
     * systematisch abgearbeitet, damit StdDraw wei�, welcher Block an welcher Stelle
     * gezeichnet werden soll.
     *
     */	
	public void drawField()
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
			{					
				field[i][j].drawImg();
			}				
	}
	
    /**
     * Spielschleife. Wird w�hrend des Spiels permanent durchlaufen. Hier werden
     * Animationen realisiert und spielbezogene (interne) Werte gepr�ft und ggf.
     * aktualisiert.
     *
     */
	public void run()
	{		
		while(true)
		{					
			StdDraw.show(10);
			{
				StdDraw.clear(StdDraw.BLACK);
				this.drawField();
				
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledCircle(playerX, playerY, 16);
				
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
					playerX = playerX + 5;
				else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
					playerX = playerX - 5;
				if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
					playerY = playerY + 5;
				else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
					playerY = playerY - 5;
			}
			StdDraw.show();
		}
	}
}
