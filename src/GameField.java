import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameField extends JFrame
{
	private static final long serialVersionUID = 9136636406585847854L;
	
	String [] lvlArray;
	int currentLvl;
	
	char [][] feld;
	Block[][] field;
	
	ReadLevel lvl;
	
	int rows, columns;
	int collDir, collDir2;
	
	boolean collideLeft, collideRight, collideUp, collideDown;
	
	
	Player player1;
	double pX, pY, fX, fY;
	int playerX, playerY;
	
	boolean isAlive = true;
	
	public GameField()
	{
		lvlArray = new String[] {
			"lvl1.txt", "lvl2.txt", "lvl3.txt"
		};
		
		lvl = new ReadLevel();

		currentLvl = 0;
		this.loadLevel(lvlArray[currentLvl]);
		
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
				else if(feld[i][j] == 'r'||feld[i][j] == 'R') //Trap hinzugefuegt
					field[i][j] = new Trap(posX, posY);
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
		pX = player1.getCenterX();
		pY = player1.getCenterY();
		
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
			{					
				field[i][j].drawImg();

				fX = field[i][j].getCenterX();
				fY = field[i][j].getCenterY();
				
				if(player1.intersects(field[i][j]) && field[i][j].isSolid())
				{
					if(pX > fX && pY >= fY - 16 && pY <= fY + 16)
						collideLeft = true;
					if(pX < fX && pY >= fY - 16 && pY <= fY + 16)
						collideRight = true;
					if(pY > fY && pX >= fX - 16 && pX <= fX + 16)
						collideDown = true;
					if(pY < fY && pX >= fX - 16 && pX <= fX + 16)
						collideUp = true;
				}
				else if(player1.intersects(field[i][j]) && (field[i][j].toString().equals("door")))
				{
					if(currentLvl < lvlArray.length)
					{
						currentLvl++;
						this.loadLevel(lvlArray[currentLvl]);
						this.initField();						
					}
					
					//StdDraw.show();
					
				//Kollisionsabfrage mit Trap und direkter Neustart	
				}else if(player1.intersects(field[i][j]) && (field[i][j].toString().equals("trap")))
				{
					isAlive = false;
					new GameField();
				}
				/*else if(player1.intersects(field[i][j]) && (field[i][j].toString().equals("stairs")))
				{					
					if(currentLvl > 0)
					{
						currentLvl--;
						this.loadLevel(lvlArray[currentLvl]);
						this.initField();				
					}
				}*/
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
		boolean noMove;
		
		while(isAlive)
		{					
			StdDraw.show(10);
			{
				StdDraw.clear(StdDraw.BLACK);
				this.drawField();
				
				noMove = true;
				
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
				{
					if(!collideRight)
					{
						playerX = playerX + 4;
						player1.setPosX(playerX);
					}
					
					if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
						player1.swapImg(Direction.RIGHT);
					
					noMove = false;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					if(!collideLeft)
					{
						playerX = playerX - 4;
						player1.setPosX(playerX);
					}
					
					if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
						player1.swapImg(Direction.LEFT);
					
					noMove = false;
				}
				if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
				{
					if(!collideUp)
					{
						playerY = playerY + 4;
						player1.setPosY(playerY);
					}
					
					player1.swapImg(Direction.UP);
					
					noMove = false;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				{
					if(!collideDown)
					{
						playerY = playerY - 4;
						player1.setPosY(playerY);
					}
					
					player1.swapImg(Direction.DOWN);
					
					noMove = false;
				}

				if(noMove)
					player1.draw();

				collideRight = false;
				collideLeft = false;
				collideDown = false;
				collideUp = false;
			}
			StdDraw.show();
		}
	}
}
