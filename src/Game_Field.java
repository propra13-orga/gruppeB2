import javax.swing.*;
import java.awt.*;

public class Game_Field extends JFrame
{
	double [][] field;
			
	public Game_Field()
	{
		StdDraw.setCanvasSize(640,640);
		StdDraw.setXscale(0, 19);
		StdDraw.setYscale(0, 19);
		this.initField();
		this.run();
	}
	
    /**
     * Initialisiert das Spielfeld in einem Array. Sollte später aus
     * einer Datei eingelesen werden können.
     *
     */
	public void initField()
	{
		field = new double[20][20];
		
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++)
			{
				field[i][j] = Math.random();
				
				if(i == 0 || i == 19)
					field[i][j] = 1 + Math.random();
				
				if(j == 0 || j == 19)
					field[i][j] = 1 + Math.random();
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
		
		for(int i = 0; i < 20; i++)
			for(int j = 0; j < 20; j++)
			{
				if(field[i][j] < 0.25)
					StdDraw.picture(i, j, "Ground_Tile_"+1+".png");
				else if(field[i][j] < 0.5)
					StdDraw.picture(i, j, "Ground_Tile_"+2+".png");
				else if(field[i][j] < 0.75)
					StdDraw.picture(i, j, "Ground_Tile_"+3+".png");
				else if(field[i][j] < 1)
					StdDraw.picture(i, j, "Ground_Tile_"+4+".png");
				else if(field[i][j] < 1.25)
					StdDraw.picture(i, j, "Wall_Tile_"+1+".png");
				else if(field[i][j] < 1.5)
					StdDraw.picture(i, j, "Wall_Tile_"+2+".png");
				else if(field[i][j] < 1.75)
					StdDraw.picture(i, j, "Wall_Tile_"+3+".png");
				else if(field[i][j] < 2)
					StdDraw.picture(i, j, "Wall_Tile_"+4+".png");
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
				this.drawField();
			}
			StdDraw.show();
		}
	}
}
