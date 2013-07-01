import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;


public class BattleScreen 
{	
	long delta = 0;
	long last = 0;
	long fps = 0;
	
	boolean introPlayed, showIntroDialog, selectionOn, angrOn, magicOn;
	boolean pressedE;
	
	Font font;
	Font fontBold;
	
	DecimalFormat df;
	
	Enemy enemy;
	Player player;
	
	private double width, heigth;
	
	double screenMidX;
	double screenMidY;
	
	double time;
	
	KeyManager key;
	SoundManager snd;
	
	BattleDialog dialogs;
	
	int selection;
	
	public BattleScreen(GameField field, SoundManager snd, Enemy enemy)
	{
		try 
		{
			Font lfont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("fonts/battle_font.ttf"));
			font = lfont.deriveFont(Font.TRUETYPE_FONT, 16);
			fontBold = lfont.deriveFont(Font.BOLD, 16);
		} catch (FontFormatException | IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Es trat ein Fehler beim Lesen der Schriftart auf (fonts/battle_font.ttf)");
			System.exit(0);
		}
		
		df = new DecimalFormat("0.0");
		
		this.enemy = enemy;
		player = field.player1;
		
		width = field.columns * 40;
		heigth = (field.rows + 2) * 40 + 80;
		
		screenMidX = width / 2;
		screenMidY = heigth / 2 - 40;
		
		this.key = new KeyManager(this);
		this.snd = snd;
		
		dialogs = new BattleDialog(this);
		
		time = 0;
		
		introPlayed = false;
		showIntroDialog = true;
		selectionOn = false;
		angrOn = false;
		magicOn = false;
		
		pressedE = false;
		
		selection = 1;
		
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
		if(pressedE)
		{
			showIntroDialog = false;

			if(!selectionOn)
			{
				angrOn = false;
				magicOn = false;
				selectionOn = true;
				
				selection = 1;
			}
			else if(!angrOn && selection == 1)
			{
				selectionOn = false;
				angrOn = true;
				
				selection = 1;
			}
			else if(!magicOn && selection == 2)
			{
				selectionOn = false;
				magicOn = true;
				
				selection = 1;
			}
			
			pressedE = false;
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
	
	private void run()
	{
		while(true)
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
					
					key.handleKeyInput();
					
					drawBattle();
					drawStatus();
					
					if(showIntroDialog)
						dialogs.showIntroDialog();
					else if(selectionOn)
						dialogs.showSelectionDialog(selection);
					else if(angrOn)
						dialogs.showAngrDialog(selection);
					else if(magicOn)
						dialogs.showMagicDialog(selection);
				}
			}
		}
	}
	
	private void computeDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
		fps = ((long) 1e9)/delta;
	}
}
