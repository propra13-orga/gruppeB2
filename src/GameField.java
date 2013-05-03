import javax.swing.*;
import java.awt.*;

public class GameField extends JFrame
{
	char [][] feld;
	double [][] randField;
	ReadLevel lvl;
	
	int rows, columns;
			
	public GameField()
	{
		lvl = new ReadLevel();
		
		this.loadLevel("test.txt");
		this.initField();
		this.run();
	}
	
	public void loadLevel(String level)
	{
		feld = lvl.readLevel(level);
		
		if(feld == null)
		{
			JOptionPane.showMessageDialog(this, "Die geladene Datei beinhaltet kein quadratisches Spielfeld!");
			System.exit(0);
		}
	}
	
    /**
     * Initialisiert das Spielfeld in einem Array. Sollte später aus
     * einer Datei eingelesen werden können.
     *
     */
	public void initField()
	{	
		columns = feld[0].length;
		rows = feld.length;
		
		System.out.println("Col: " +columns+" Rows: "+rows);
		
		randField = new double[rows][columns];
		
		StdDraw.setCanvasSize(32 * columns, 32 * rows);
		StdDraw.setXscale(0, 32 * columns);
		StdDraw.setYscale(0, 32 * rows);
		
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < columns; j++)
			{
				if(feld[i][j] == 'x'||feld[i][j] == 'X')
					randField[i][j] = 1 + Math.random();
				else if(feld[i][j] == ' '||feld[i][j] == ' ')
					randField[i][j] = Math.random();
				
				System.out.print((int)randField[i][j]);
			}
			System.out.println();
		}
	}
	
    /**
     * Methode, die das Spielfeld mit StdDraw zeichnet. Dabei wird das Array "field"
     * systematisch abgearbeitet, damit StdDraw weiß, welcher Block an welcher Stelle
     * gezeichnet werden soll.
     *
     */	
	public void drawField()
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
			{
				int posX = j * 32 + 16;
				int posY = (32 * rows)-(i * 32 + 16);
				
				if(randField[i][j] < 0.25)
					StdDraw.picture(posX, posY, "images\\arena\\Ground_Tile_"+1+".png");
				else if(randField[i][j] < 0.5)
					StdDraw.picture(posX, posY, "images\\arena\\Ground_Tile_"+2+".png");
				else if(randField[i][j] < 0.75)
					StdDraw.picture(posX, posY, "images\\arena\\Ground_Tile_"+3+".png");
				else if(randField[i][j] < 1)
					StdDraw.picture(posX, posY, "images\\arena\\Ground_Tile_"+4+".png");				
				else if(randField[i][j] < 1.25)
					StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+1+".png");
				else if(randField[i][j] < 1.5)
					StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+2+".png");
				else if(randField[i][j] < 1.75)
					StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+3+".png");
				else if(randField[i][j] < 2)
					StdDraw.picture(posX, posY, "images\\arena\\Wall_Tile_"+4+".png");
			
			}				
	}
	
    /**
     * Spielschleife. Wird während des Spiels permanent durchlaufen. Hier werden
     * Animationen realisiert und spielbezogene (interne) Werte geprüft und ggf.
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
			}
			StdDraw.show();
		}
	}
}
