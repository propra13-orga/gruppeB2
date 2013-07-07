import java.awt.event.*;


public class Manager_Key
{
	private GameField field;
	private BattleScreen battle;
	private InGameMenu menu;
	private Shop shop;
	
	long delta;
	int selBefore;
	
	public Manager_Key(GameField field)
	{
		this.field = field;
	}
	
	public Manager_Key(BattleScreen battle)
	{
		this.battle = battle;
	}

	public Manager_Key(InGameMenu menu)
	{
		this.menu = menu;
	}
	
	public Manager_Key(Shop shop)
	{
		this.shop = shop;
	}
	
	public void handleKeyInput()
	{	
		if(field != null)
		{
			delta = field.delta;
			
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_I))
			{
				field.player1.stop();
				field.mapScreen = false;
				new Shop(field, field.snd);
			}
			
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
			else if(battle.inventarOn && !battle.itemUseOn)
			{
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ENTER))
				{
					int size = battle.parent.player1.inventory.size();
					
					if(size > 0)
					{
						selBefore = battle.selection;
						battle.itemUseOn = true;
						battle.selection = 1;
					}
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(battle.selection == 1 && battle.itemSel > 1)
					{
						battle.lower--;
						battle.upper--;
					}
					
					if(battle.selection > 1)
						battle.selection--;
					
					if(battle.itemSel > 1)
						battle.itemSel--;
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(battle.selection == 4 && battle.itemSel < battle.parent.player1.inventory.size())
					{
						battle.lower++;
						battle.upper++;
					}
					
					if(battle.selection < 4 && battle.selection < battle.parent.player1.inventory.size())
						battle.selection++;
					
					if(battle.itemSel < battle.parent.player1.inventory.size())
						battle.itemSel++;
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
				{
					battle.inventarOn = false;
					battle.selectionOn = true;
					battle.selection = 3;
					battle.itemSel = 1;
				}
			}
			else if(battle.itemUseOn)
			{
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ENTER))
				{
					int size = battle.parent.player1.inventory.size();
					
					if(size > 0)
					{
						if(battle.selection == 2)
						{
							if(battle.parent.player1.inventory.getItemAt(battle.itemSel - 1) instanceof Weapon)
							{
								Weapon wep = (Weapon)battle.parent.player1.inventory.getItemAt(battle.itemSel - 1);
							
								System.out.println("Vorher: " + battle.parent.player1.getAtt());
							
								if(battle.parent.player1.getEquippedWeapon().type() == wep.type())
									battle.parent.player1.unequipWeapon();
							
								battle.parent.player1.equipWeapon(new Weapon_Faust());
							
								System.out.println("Nachher: " + battle.parent.player1.getAtt());
							}
							
							battle.parent.player1.inventory.removeOneItem(battle.parent.player1.inventory.getItemAt(battle.itemSel - 1));
						}
						else if(battle.selection == 1)
						{
							if(battle.parent.player1.inventory.getItemAt(battle.itemSel - 1).useItem(battle.parent.player1))
								battle.parent.player1.inventory.removeOneItem(battle.parent.player1.inventory.getItemAt(battle.itemSel - 1));
							else if(battle.parent.player1.inventory.getItemAt(battle.itemSel - 1) instanceof Weapon)
							{
								Weapon wep = (Weapon)battle.parent.player1.inventory.getItemAt(battle.itemSel - 1);
								
								System.out.println("Vorher: " + battle.parent.player1.getAtt());
								
								if(battle.parent.player1.getEquippedWeapon().type() != wep.type())
								{
									battle.parent.player1.unequipWeapon();
								
									battle.parent.player1.equipWeapon(wep);
								}
								else
									battle.parent.player1.unequipWeapon();
								
								System.out.println("Nachher: " + battle.parent.player1.getAtt());
							}
						}
					}

					battle.selection = selBefore;
					
					if(size > battle.parent.player1.inventory.size())
					{
						battle.itemSel = 1;
						battle.selection = 1;
						battle.lower = 0;
						
						if(battle.parent.player1.inventory.size() > 3)
							battle.upper = 4;
						else
							battle.upper = battle.parent.player1.inventory.size();
					}
					
					battle.itemUseOn = false;
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(battle.selection > 1)
					{
						battle.selection--;
					}
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(battle.selection < 2)
					{
						battle.selection++;
					}
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
				{
					battle.itemUseOn = false;
					battle.selection = battle.itemSel;
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
			else if(menu.itemSelOn && !menu.itemUseOn)
			{
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ENTER))
				{
					int size = menu.parent.player1.inventory.size();
					
					if(size > 0)
					{
						selBefore = menu.selection;
						menu.itemUseOn = true;
						menu.selection = 1;
					}
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(menu.selection == 1 && menu.itemSel > 1)
					{
						menu.lower--;
						menu.upper--;
					}
					
					if(menu.selection > 1)
						menu.selection--;
					
					if(menu.itemSel > 1)
						menu.itemSel--;
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(menu.selection == 4 && menu.itemSel < menu.parent.player1.inventory.size())
					{
						menu.lower++;
						menu.upper++;
					}
					
					if(menu.selection < 4 && menu.selection < menu.parent.player1.inventory.size())
						menu.selection++;
					
					if(menu.itemSel < menu.parent.player1.inventory.size())
						menu.itemSel++;
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
				{
					menu.itemSelOn = false;
					menu.mainSelOn = true;
					menu.selection = 1;
					menu.itemSel = 1;
				}
			}
			else if(menu.itemUseOn)
			{
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ENTER))
				{
					int size = menu.parent.player1.inventory.size();
					
					if(size > 0)
					{
						if(menu.selection == 2)
						{
							if(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1) instanceof Weapon)
							{
								Weapon wep = (Weapon)menu.parent.player1.inventory.getItemAt(menu.itemSel - 1);
							
								System.out.println("Vorher: " + menu.parent.player1.getAtt());
							
								if(menu.parent.player1.getEquippedWeapon().type() == wep.type())
									menu.parent.player1.unequipWeapon();
							
								menu.parent.player1.equipWeapon(new Weapon_Faust());
							
								System.out.println("Nachher: " + menu.parent.player1.getAtt());
							}
							else if(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1) instanceof Armor)
							{
								Armor arm = (Armor)menu.parent.player1.inventory.getItemAt(menu.itemSel - 1);
							
								System.out.println("Vorher: " + menu.parent.player1.getDef());
							
								if(menu.parent.player1.getEquippedArmor().type() == arm.type())
									menu.parent.player1.unequipArmor();
							
								menu.parent.player1.equipArmor(new Armor_None());
							
								System.out.println("Nachher: " + menu.parent.player1.getDef());
							}
							
							menu.parent.player1.inventory.removeOneItem(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1));
						}
						else if(menu.selection == 1)
						{
							if(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1).useItem(menu.parent.player1))
								menu.parent.player1.inventory.removeOneItem(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1));
							
							else if(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1) instanceof Weapon)
							{
								Weapon wep = (Weapon)menu.parent.player1.inventory.getItemAt(menu.itemSel - 1);
								
								System.out.println("Vorher: " + menu.parent.player1.getAtt());
								
								if(menu.parent.player1.getEquippedWeapon().type() != wep.type())
								{
									menu.parent.player1.unequipWeapon();
								
									menu.parent.player1.equipWeapon(wep);
								}
								else
									menu.parent.player1.unequipWeapon();
								
								System.out.println("Nachher: " + menu.parent.player1.getAtt());
							}
							else if(menu.parent.player1.inventory.getItemAt(menu.itemSel - 1) instanceof Armor)
							{
								Armor arm = (Armor)menu.parent.player1.inventory.getItemAt(menu.itemSel - 1);
								
								System.out.println("Vorher: " + menu.parent.player1.getDef());
								
								if(menu.parent.player1.getEquippedArmor().type() != arm.type())
								{
									menu.parent.player1.unequipArmor();
								
									menu.parent.player1.equipArmor(arm);
								}
								else
									menu.parent.player1.unequipArmor();
								
								System.out.println("Nachher: " + menu.parent.player1.getDef());
							}
						}
					}

					menu.selection = selBefore;
					
					if(size > menu.parent.player1.inventory.size())
					{
						menu.itemSel = 1;
						menu.selection = 1;
						menu.lower = 0;
						
						if(menu.parent.player1.inventory.size() > 3)
							menu.upper = 4;
						else
							menu.upper = menu.parent.player1.inventory.size();
					}
					
					menu.itemUseOn = false;
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
				{
					if(menu.selection > 1)
					{
						menu.selection--;
					}
				}
				else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
				{
					if(menu.selection < 2)
					{
						menu.selection++;
					}
				}
				
				if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
				{
					menu.itemUseOn = false;
					menu.selection = menu.itemSel;
				}
			}
		}
		else if(shop != null)
		{
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_RIGHT))
			{
				if(shop.selTab < 2)
				{
					shop.selTab++;
					shop.selection = 0;
				}
			}
			else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_LEFT))
			{
				if(shop.selTab > 0)
				{
					shop.selTab--;
					shop.selection = 0;
				}
			}
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
			{
				if(shop.selection > 0)
					shop.selection--;
			}
			else if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
			{
				if(shop.selection < shop.maxSelection - 1)
					shop.selection++;
			}
			
			if(StdDraw.isKeyPressedSingle(KeyEvent.VK_ESCAPE))
			{
				shop.shopOn = false;
				shop.field.mapScreen = true;
				shop.field.player1.go();
				shop.field.run();
			}
		}
	}
}
