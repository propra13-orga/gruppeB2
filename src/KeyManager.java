import java.awt.event.*;


public class KeyManager
{
	private GameField field;
	private BattleScreen battle;
	
	
	long delta;
	
	public KeyManager(GameField field)
	{
		this.field = field;
	}
	
	public KeyManager(BattleScreen battle)
	{
		this.battle = battle;
	}

	public void handleKeyInput()
	{	
		//TestZweck
		if(StdDraw.isKeyPressedSingle('z'))
			field.battleScreen = false;
		
		
		if(field != null)
		{
			delta = field.delta;
					
			if(StdDraw.isKeyPressedSingle('e'))
			{
				field.countE++;
			}
		
			//-----------------------------Movement-----------------------------

			//prueft, ob sich der Spieler in dem Schleifendurchlauf bewegt hat
			//oder nicht
			boolean noMove = true;
		
		
			if(field.player1.canMove())
			{
				if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
				{	
					if(!field.collideRight)
					{
						field.playerX = field.playerX + field.player1.getSpeed() * (delta/1e9);
						field.player1.setPosX(field.playerX);
					}	
	
					if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
						field.player1.swapImg(Direction.RIGHT, delta);
		
					noMove = false;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					if(!field.collideLeft)
					{
						field.playerX = field.playerX - field.player1.getSpeed() * (delta/1e9);
						field.player1.setPosX(field.playerX);
					}
		
					if(!StdDraw.isKeyPressed(KeyEvent.VK_UP) && !StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
						field.player1.swapImg(Direction.LEFT, delta);
		
					noMove = false;
				}
				
			
				if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
				{
					if(!field.collideUp)
					{
						field.playerY = field.playerY + field.player1.getSpeed() * (delta/1e9);
						field.player1.setPosY(field.playerY);
					}
		
					field.player1.swapImg(Direction.UP, delta);
		
					noMove = false;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				{
					if(!field.collideDown)
					{
						field.playerY = field.playerY - field.player1.getSpeed() * (delta/1e9);
						field.player1.setPosY(field.playerY);
					}
		
					field.player1.swapImg(Direction.DOWN, delta);
					
					noMove = false;
				}
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
		
		else if(battle != null)
		{
			if(StdDraw.isKeyPressedSingle('e'))
				System.out.println("LOL");
		}
	}
}
