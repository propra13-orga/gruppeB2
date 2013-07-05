import java.awt.event.*;


public class KeyManager
{
	private GameField field;
	private BattleScreen battle;
	private InGameMenu menu;
	
	long delta;
	
	public KeyManager(GameField field)
	{
		this.field = field;
	}
	
	public KeyManager(BattleScreen battle)
	{
		this.battle = battle;
	}

	public KeyManager(InGameMenu menu)
	{
		this.menu = menu;
	}
	
	public void handleKeyInput()
	{	
		if(field != null)
		{
			delta = field.delta;
			
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_E))
			{
				field.countE++;
			}
			
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
			{
				field.player1.stop();
				field.mapScreen = false;
				new InGameMenu(field, field.snd);
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
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_E))
				battle.pressedE = true;

			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_R))
				battle.pressedR = true;
			
			if(battle.selectionOn)
			{
				if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				{
					if(battle.selection == 2)
						battle.selection = 1;
					else if(battle.selection == 4)
						battle.selection = 3;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
				{
					if(battle.selection == 1)
						battle.selection = 2;
					else if(battle.selection == 3)
						battle.selection = 4;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
				{
					if(battle.selection == 3)
						battle.selection = 1;
					else if(battle.selection == 4)
						battle.selection = 2;
				}
				else if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
				{
					if(battle.selection == 1)
						battle.selection = 3;
					else if(battle.selection == 2)
						battle.selection = 4;
				}
			}
			
			if(battle.angrOn)
			{
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(battle.selection > 1)
						battle.selection--;
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(battle.selection < battle.player.getAttackCount())
						battle.selection++;
				}
			}
			
			if(battle.magicOn)
			{
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(battle.selection > 1)
						battle.selection--;
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(battle.selection < battle.player.getMagicCount())
						battle.selection++;
				}
			}
		}
		
		else if(menu != null)
		{			
			if(menu.mainSelOn)
			{
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ENTER))
				{
					switch(menu.selection)
					{
					case 1:
						menu.mainSelOn = false;
						menu.itemSelOn = true;
						menu.selection = 1;
						menu.lower = 0;

						if(menu.parent.player1.inventory.size() > 4)
							menu.upper = 4;
						else
							menu.upper = menu.parent.player1.inventory.size();
						
						break;
					case 2: break;
					case 3: break;
					case 4: break;
					case 5:
						menu.menuOn = false;
						menu.parent.mapScreen = true;
						menu.parent.player1.go();
						menu.parent.run();
						break;
					}
				}
					
					
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(menu.selection > 1)
						menu.selection--;
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(menu.selection < 5)
						menu.selection++;
				}
				
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
				{
					menu.menuOn = false;
					menu.parent.mapScreen = true;
					menu.parent.player1.go();
					menu.parent.run();
				}
			}
			else if(menu.itemSelOn)
			{
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ENTER))
				{
					int size = menu.parent.player1.inventory.size();
					
					if(size > 0)
					{
						if(menu.parent.player1.inventory.getItemAt(menu.selection - 1).useItem(menu.parent.player1))
							menu.parent.player1.inventory.removeOneItem(menu.parent.player1.inventory.getItemAt(menu.selection - 1));
					}
					
					if(size > menu.parent.player1.inventory.size())
					{
						if(menu.selection > 1)
							menu.selection--;
					
						if(menu.lower == 0)
							menu.upper--;
					}
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(menu.selection > 1)
					{
						menu.selection--;
						
						if(menu.selection > 1 && menu.selection <= menu.parent.player1.inventory.size() - 3)
						{
							menu.lower--;
							menu.upper--;
						}
					}
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(menu.selection < menu.parent.player1.inventory.size())
					{
						menu.selection++;
						
						if(menu.selection > 4 && menu.selection <= menu.parent.player1.inventory.size())
						{
							menu.lower++;
							menu.upper++;
						}
					}
				}
				
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
				{
					menu.itemSelOn = false;
					menu.mainSelOn = true;
					menu.selection = 1;
				}
			}
		}
	}
}
