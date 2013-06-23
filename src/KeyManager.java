import java.awt.event.*;


public class KeyManager
{
	private GameField field;
	
	public KeyManager(GameField field)
	{
		this.field = field;
	}

	public void handleKeyInput()
	{		
		if(StdDraw.isKeyPressedSingle('e'))
		{
			field.countE++;
		}
		
		//-----------------------------Movement-----------------------------

		//prueft, ob sich der Spieler in dem Schleifendurchlauf bewegt hat
		//oder nicht
		boolean noMove = true;
		
		/*
		
				if(setBack > 0)
				{
					if(!collideLeft && !collideRight)
					{						
						//if(Math.abs(diffX) < 16)
							//playerX = playerX + (int)(diffX * 2);
						//else
							playerX = playerX + (int)(diffX * 0.10);						
					}
					
					if(!collideUp && !collideDown)
					{
						//if(Math.abs(diffY) < 16)
							//playerY = playerY + (int)(diffY * 2);
						//else
							playerY = playerY + (int)(diffY * 0.10);
					}

					player1.setPos(playerX, playerY);
					
					setBack--;
					
				}
		 */
		
		if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
		{
			if(StdDraw.isKeyPressed(KeyEvent.VK_S))
			{

			}		
			if(!field.collideRight)
			{
				field.playerX = field.playerX + field.player1.getSpeed();
				field.player1.setPosX(field.playerX);
			}
	
			if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				field.player1.swapImg(Direction.RIGHT);
		
			noMove = false;
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
		{
			if(StdDraw.isKeyPressed(KeyEvent.VK_S))
			{

			}
			if(!field.collideLeft)
			{
				field.playerX = field.playerX - field.player1.getSpeed();
				field.player1.setPosX(field.playerX);
			}
		
			if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				field.player1.swapImg(Direction.LEFT);
		
			noMove = false;
		}
		if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
		{
			if(StdDraw.isKeyPressed(KeyEvent.VK_S))
			{

			}
			if(!field.collideUp)
			{
				field.playerY = field.playerY + field.player1.getSpeed();
				field.player1.setPosY(field.playerY);
			}
		
			field.player1.swapImg(Direction.UP);
		
			noMove = false;
		}
		else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
		{
			if(StdDraw.isKeyPressed(KeyEvent.VK_S))
			{

			}
			if(!field.collideDown)
			{
				field.playerY = field.playerY - field.player1.getSpeed();
				field.player1.setPosY(field.playerY);
			}
		
			field.player1.swapImg(Direction.DOWN);
		
			noMove = false;
		}

		//Wenn der Benutzer den Spieler nicht bewegt hat zeichne ein
		//festes Bild fuer die Spielfigur
		if(noMove)
			field.player1.draw();

		//setze die Kollisionswerte nach jedem Schleifendurchlauf wieder
		//zurueck
		field.collideRight = false;
		field.collideLeft = false;
		field.collideDown = false;
		field.collideUp = false;	
	}
}
