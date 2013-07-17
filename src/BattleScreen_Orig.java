import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;

import javax.swing.JOptionPane;


public class BattleScreen_Orig 
{	
	long delta = 0;
	long last = 0;
	long fps = 0;
	
	GameField parent;
	
	boolean introPlayed, showIntroDialog, selectionOn, angrOn, magicOn, inventarOn, escapeOn, itemUseOn;
	boolean pressedE, pressedR;

	boolean playerAttacks, enemyAttacks;
	boolean animationFinished;
	
	boolean battleOn;
	
	Font font;
	
	Enemy enemy;
	Player player;
	
	private double width, heigth;
	
	double screenMidX;
	double screenMidY;
	
	double time, anim;
	
	Manager_Key key;
	Manager_Sound snd;
	
	BattleDialog dialogs;
	
	int selection, itemSel;
	int lower, upper;

	Attack attack;
	Magic magic;
	
	public BattleScreen_Orig(GameField field, Manager_Sound snd, Enemy enemy)
	{
		parent = field;
		
		try 
		{
			Font lfont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("fonts/battle_font.ttf"));
			font = lfont.deriveFont(Font.TRUETYPE_FONT, 16);
		} 
		catch (FontFormatException | IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Es trat ein Fehler beim Lesen der Schriftart auf (fonts/battle_font.ttf)");
			System.exit(0);
		}
		
		this.enemy = enemy;
		player = field.player1;
		
		width = field.columns * 40;
		heigth = (field.rows + 2) * 40 + 80;
		
		screenMidX = width / 2;
		screenMidY = heigth / 2 - 40;
		
		this.key = new Manager_Key(this);
		this.snd = snd;
		
		dialogs = new BattleDialog(this);
		
		time = 0;
		anim = 0;
		
		introPlayed = false;
		showIntroDialog = true;
		selectionOn = false;
		angrOn = false;
		magicOn = false;
		inventarOn = false;
		escapeOn = false;
		itemUseOn = false;
		
		pressedE = false;
		pressedR = false;
		
		selection = 1;
		itemSel = 1;
		
		battleOn = true;
		
		this.playBlending(0);
		this.run();
	}
	
	private void playBlending(int type)
	{	
		for(int i = 1; i <= 50; i++)
		{
			StdDraw.show(50);
			{
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.filledRectangle(width / 2, heigth / 2, i*(width / 80), i*(heigth / 80));
			}
		}
		
		last = System.nanoTime();
	}
	
	private void playIntro()
	{
		StdDraw.show(5);
		{		
				StdDraw.picture(screenMidX, screenMidY, "images/battle/intro/intro_screen.png");
				
				if(time < 440)
					time = time + 250 * (delta/1e9);
				else
				{
					introPlayed = true;
					snd.playSound(1);
				}
				
				// Zeichne den Gegner von links nach rechts
				StdDraw.picture(screenMidX - 270 + time, screenMidY + 100, "images/enemy/" + enemy.toString() + "/battle.png");
			
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.filledRectangle(screenMidX - 290, screenMidY, 50, heigth / 2);
				
				
				// Zeichne den Spieler (von rechts nach links)
				StdDraw.picture(screenMidX + 280 - time, screenMidY - 42, "images/player/battle.png");
				
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.filledRectangle(screenMidX + 290, screenMidY, 50, heigth / 2);
		}
	}
	
	private void drawBattle()
	{		
		if(pressedE && !playerAttacks)
		{
			showIntroDialog = false;

			/*if(!selectionOn)
			{
				angrOn = false;
				magicOn = false;
				selectionOn = true;
				
				selection = 1;
			}
			else*/ 
			if(selectionOn)
			{
				if(selection == 1)
				{
					selectionOn = false;
					angrOn = true;
				
					selection = 1;
				}
				else if(selection == 2)
				{
					selectionOn = false;
					magicOn = true;
				
					selection = 1;
				}
				else if(selection == 3)
				{
					selectionOn = false;
					inventarOn = true;
					
					selection = 1;
					lower = 0;						
					
					if(parent.player1.inventory.size() > 4)
						upper = 4;
					else
						upper = parent.player1.inventory.size();
				}
				else if(selection == 4)
				{					
					selectionOn = false;
					escapeOn = true;
				}
			}
			else if(angrOn || magicOn)
				playerAttacks = true;
			else if(inventarOn)
			{			
			}
			else if(escapeOn)
				battleOn = false;
			else
				selectionOn = true;
			
			pressedE = false;
		}
		else if(pressedR && !playerAttacks)
		{
			if(angrOn)
			{
				angrOn = false;
				selection = 1;
				selectionOn = true;
			}
			if(magicOn)
			{
				magicOn = false;
				selection = 2;
				selectionOn = true;
			}
			
			pressedR = false;
		}
		
		StdDraw.picture(screenMidX, screenMidY, "images/battle/intro/intro_screen.png");
		
		// Zeichne den Gegner
		StdDraw.picture(screenMidX - 270 + time, screenMidY + 100, "images/enemy/" + enemy.toString() + "/battle.png");
		
		// Zeichne den Spieler
		StdDraw.picture(screenMidX + 280 - time, screenMidY - 42, "images/player/battle.png");
	}
	
	private void drawStatus()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.BLACK);

		StdDraw.textLeft(screenMidX - 200, screenMidY + 165, "" + enemy.getName().toUpperCase());
		StdDraw.setPenColor(Color.red);
		StdDraw.filledRectangle(screenMidX - 148.5 + enemy.getHealth() * (65.0 / enemy.getMaxHealth()), screenMidY + 127, enemy.getHealth() * (65.0 / enemy.getMaxHealth()), 3);
		StdDraw.setPenColor(Color.black);
		StdDraw.setPenColor(Color.blue);
		StdDraw.filledRectangle(screenMidX - 148.5 + enemy.getMana() * (65.0 / enemy.getMaxMana()), screenMidY + 112, enemy.getMana() * (65.0 / enemy.getMaxMana()), 3);
		StdDraw.setPenColor(Color.black);
		StdDraw.picture(screenMidX - 140, screenMidY + 150, "images/battle/status/lvl.png");
		StdDraw.picture(screenMidX - 100, screenMidY + 115, "images/battle/status/status.png");
		StdDraw.textLeft(screenMidX - 128, screenMidY + 146, "" + enemy.getLevel());


		
		StdDraw.textLeft(screenMidX + 30, screenMidY, "" + player.getPlayerName().toUpperCase());
		StdDraw.setPenColor(Color.red);
		StdDraw.filledRectangle(screenMidX + 70.5 + player.getHealth() * (65.0 / player.getMaxHealth()), screenMidY - 38, player.getHealth() * (65.0 / player.getMaxHealth()), 3);
		StdDraw.setPenColor(Color.black);
		StdDraw.setPenColor(Color.blue);
		StdDraw.filledRectangle(screenMidX + 70.5 + player.getMana() * (65.0 / player.getMaxMana()), screenMidY - 53, player.getMana() * (65.0 / player.getMaxMana()), 3);
		StdDraw.setPenColor(Color.black);
		StdDraw.picture(screenMidX + 110, screenMidY - 15, "images/battle/status/lvl.png");
		StdDraw.picture(screenMidX + 110, screenMidY - 50, "images/battle/status/status_player.png");
		StdDraw.textLeft(screenMidX + 122, screenMidY - 19, "" + player.getLevel());
		StdDraw.text(screenMidX + 120, screenMidY - 75, (int)player.getHealth() + "/" + (int)player.getMaxHealth()); 
	}
	
	private void doLogic()
	{
		/*if(enemy.getHealth() == 0)
			battleOn = false;*/
	}
	
	private void executeAttack(int selection)
	{
		if(angrOn)
			attack = player.getAttack(selection);
		else if(magicOn)
			magic = player.getMagic(selection);
		
		if(attack != null)
		{
			if(anim == 0)
				snd.playSound(attack.getSound());
			
			if(anim < 20 && attack.dealDmg())
				StdDraw.picture(screenMidX - 100 + 12*anim, screenMidY - 25 + 8*anim, attack.getImageSrc());
			else if(anim < 40 && !attack.dealDmg())
				StdDraw.picture(screenMidX + 280 - time, screenMidY - 42, attack.getImageSrc());
			
			if(anim > 149 && attack.dealDmg())
				enemy.decreaseHealth(attack.getStrength() * (player.getAtt() / 10) / 50);
			else if(!attack.dealDmg())
				player.handleAttack(attack);
			
			if(anim < 99)
				dialogs.showAttack(attack);
			else
				dialogs.showEffect(attack);
			
			anim++;
			
			if(anim > 199)
			{
				attack = null;
				playerAttacks = false;
				anim = 0;
			}

		}
		else if(magic != null && player.getMana() >= magic.manaCost())
		{
			if(anim == 0)
				snd.playSound(magic.getSound());
			
			if(anim < 20 && magic.dealDmg())
				StdDraw.picture(screenMidX - 100 + 12*anim, screenMidY - 25 + 8*anim, magic.getImageSrc());
			else if(anim < 30 && !magic.dealDmg())
				StdDraw.picture(screenMidX + 280 - time, screenMidY - 42, magic.getImageSrc());
			
			if(anim > 149 && magic.dealDmg())
				enemy.decreaseHealth(magic.getStrength() / 50);
			else if(!magic.dealDmg())
				player.handleMagic(magic);
			
			if(anim < 99)
				dialogs.showMagic(magic);
			else
				dialogs.showEffect(magic);
			
			anim++;
			
			if(anim > 199)
			{
				player.decreaseMana(magic.manaCost());
				magic = null;
				playerAttacks = false;
				anim = 0;
			}
		}
		else
		{
			dialogs.showNoMana(magic);
			
			anim++;
			
			if(anim > 100)
			{
				magic = null;
				playerAttacks = false;
				anim = 0;
			}
		}
	}
	
	private void run()
	{
		while(battleOn)
		{			
			computeDelta();
			
			if(!introPlayed)
			{
				playIntro();
			}
			else
			{
				StdDraw.show(5);
				{
					StdDraw.clear(Color.black);
					
					if(!playerAttacks)
						key.handleKeyInput();
					
					doLogic();
					
					drawBattle();
					drawStatus();

					if(playerAttacks)
						executeAttack(selection);
					else
					{
						if(showIntroDialog)
							dialogs.showIntroDialog();
						else if(selectionOn)
							dialogs.showSelectionDialog(selection);
						else if(angrOn)
							dialogs.showAngrDialog(selection);
						else if(magicOn)
							dialogs.showMagicDialog(selection);
						else if(inventarOn)
						{
							dialogs.showSelectionDialog(3);
							dialogs.showItemDialog(selection);
						}		
						else if(escapeOn)
							dialogs.showEscapeDialog();
					}
				}
			}
		}

		parent.mapScreen = true;
		parent.battleScreen = false;
		parent.enemy.remove(enemy);
		snd.stopSound(0);
		parent.player1.go();
		parent.run();

	}
	
	private void computeDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
		fps = ((long) 1e9)/delta;
	}
}
