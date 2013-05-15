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
	
	Player player1;
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
     * Initialisiert das Spielfeld in einem Array. Sollte später aus
     * einer Datei eingelesen werden können.
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
				else if(feld[i][j] == 's'||feld[i][j] == 'S')
				{
					field[i][j] = new Floor(posX, posY);
					
					player1 = new Player(posX, posY);
					playerX = posX;
					playerY = posY;
				}
			}
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
				field[i][j].drawImg();
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
		while(player1.getAlive()==true)
		{					
			StdDraw.show(10);
			{
				StdDraw.clear(StdDraw.BLACK);
				this.drawField();

				//Kollision und KeyEvents
//				if(StdDraw.isKeyPressed(KeyEvent.VK_UP)==true)
				if(StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && !StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && !StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					if(field[(14-((int) player1.getPosY()+player1.getSpeed())/32)][(int) player1.getPosX()/32].isSolid()==false)
					{
						player1.moveUp();
					}else
						player1.draw();
				}
								
//				if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)==true)
				if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && !StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && !StdDraw.isKeyPressed(KeyEvent.VK_LEFT) )
				{
					if(field[(14-((int) player1.getPosY()-player1.getSpeed())/32)][(int) player1.getPosX()/32].isSolid()==false)
					{
						player1.moveDown();
					}else
						player1.draw();
				}
				
//				if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)==true)
				if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && !StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				{
					if(field[(14-((int) player1.getPosY())/32)][((int) player1.getPosX()-player1.getSpeed())/32].isSolid()==false)
					{
						player1.moveLeft();
					}else
						player1.draw();
				}
				
//				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)==true)
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && !StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN) && !StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					if(field[(14-((int) player1.getPosY())/32)][((int) player1.getPosX()+player1.getSpeed())/32].isSolid()==false)
					{
						player1.moveRight();
					}else
						player1.draw();
				}
	
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)==false && StdDraw.isKeyPressed(KeyEvent.VK_LEFT)==false &&StdDraw.isKeyPressed(KeyEvent.VK_UP)==false && StdDraw.isKeyPressed(KeyEvent.VK_DOWN)==false )
				{
					player1.draw();
				}
								
				
			}
			StdDraw.show();
		}
	}
}
