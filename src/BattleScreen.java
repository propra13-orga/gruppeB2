import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.*;

import javax.swing.JOptionPane;

/**
 * Die Hauptklasse des Kampfsystem. Kuemmert sich um die Logik und laesst die
 * entsprechenden Layer des Kampfes zeichnen. Empfangt ausserdem die Tasteneingaben
 * @author Mike Bechtel
 *
 */
public class BattleScreen 
{	
	/**
	 * Diese drei Variablen dienen zur fluessigen und gleichmaessig schnellen Darstellung
	 * von Animationen
	 */
	long delta, last, fps;
	
	/**
	 * Eltern-Spielfeld, auf dem der Kampf ausgeloest wurde
	 */
	GameField parent;
	
	/**
	 * <b>booleans</b> speichern, welches Layer zu zeichnen ist und welches nicht
	 */
	boolean introPlayed, showIntroDialog, selectionOn, angrOn, magicOn, inventarOn, escapeOn, itemUseOn, winOn, loseOn, lvlOn, newAttOn, newMagOn;

	/**
	 * Speichert, ob der Spieler am Ende des Kampfes gewonnen oder verloren hat
	 */
	boolean playerWins, playerLose;
	
	/**
	 * Speichert, ob gerade ein Angriff des Spielers bzw. des Gegners in Gange ist
	 */
	boolean playerAttacks, enemyAttacks;
	
	/**
	 * Speichert, ob dem Spieler die XP gutgeschrieben wurden
	 */
	boolean gotXP = false;
	
	/**
	 * Speichert die vom Gegner benutzte Attacke / Magie
	 */
	double action = -1;
	
	/**
	 * Speichert, ob der Kampf noch laeuft oder nicht
	 */
	boolean battleOn;
	
	/**
	 * Speichert die Schriftart
	 */
	Font font;
	
	/**
	 * Speichert den Gegner
	 */
	Enemy enemy;
	/**
	 * Speichert den Spieler
	 */
	Player player;
	
	/**
	 * Speichert die Groesse des angezeigen Fensters
	 */
	private double width, heigth;
	
	/**
	 * Speichert den x-Wert der Fenstermitte
	 */
	double screenMidX;
	/**
	 * Speichert den y-Wert der Fenstermitte
	 */
	double screenMidY;
	
	/**
	 * Hilfsvariablen zur Animation
	 */
	double time, anim;
	
	/**
	 * Der Manager, der die Tasteneingaben verarbeitet
	 */
	Manager_Key key;
	/**
	 * Sound-Manager, in dem die verschiedenen Sound gespeichert sind
	 */
	Manager_Sound snd;
	
	/**
	 * Diese Klasse sorgt fuer das Zeichnen auf dem BattleScreen. Ist in verschiedene
	 * Layer aufgeteilt
	 */
	BattleDialog dialogs;
	
	/**
	 * Speichert die ausgewaehlte Selektion bzw. Item-Selektion
	 */
	int selection, itemSel;
	/**
	 * Hilftvariablen zur Darstellung des Items-Menu
	 */
	int lower, upper;

	/**
	 * Speichert die ausgewaehlte Attacke
	 */
	Attack attack;
	/**
	 * Speichert den ausgewaehlten Zauber
	 */
	Magic magic;
	
	/**
	 * Speichert den errechneten Schaden eines Angriffs
	 */
	double dmg;
	
	/**
	 * Konstruktor des Kampfes. Die Schriftart wird eingelesen, die Fenstermitte wird
	 * berechnet und die zu Begin anzuzeigenden Layer werden gesetzt.
	 * @param field - Spielfeld, auf dem der Kampf ausgeloest wurde
	 * @param enemy - Gegner, der den Kampf ausgeloest hat
	 */
	public BattleScreen(GameField field, Enemy enemy)
	{
		battleOn = true;

		delta = last = fps = 0;
		
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
		this.snd = field.snd;
		
		dialogs = new BattleDialog(this);
		
		time = 0;
		anim = 0;
		
		
		
		introPlayed = false;
		showIntroDialog = true;
		
		selectionOn = false;
		
		angrOn = false;
		
		magicOn = false;
		
		inventarOn = false;
		itemUseOn = false;
		
		escapeOn = false;
		
		winOn = loseOn = lvlOn = newAttOn = newMagOn = false;
		
		selection = 1;
		itemSel = 1;
		
		this.playBlending();
		this.run();
	}
	
	/**
	 * Methode, die den Uebergang vom Spielfeld zum Kampf ausfuehrt
	 */
	private void playBlending()
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
	
	/**
	 * Zeichnet den Intro-Dialog solange bis der Spieler weiter gedrueckt hat
	 */
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
	
	/**
	 * Zeichnet den Kampf
	 */
	private void drawBattle()
	{		
		
		
		StdDraw.picture(screenMidX, screenMidY, "images/battle/intro/intro_screen.png");
	
		// Zeichne den Gegner
		if(playerAttacks || enemy.getHealth() > 0)
			StdDraw.picture(screenMidX - 270 + time, screenMidY + 100, "images/enemy/" + enemy.toString() + "/battle.png");
		
		// Zeichne den Spieler
		if(enemyAttacks || player.getHealth() > 0)
			StdDraw.picture(screenMidX + 280 - time, screenMidY - 42, "images/player/battle.png");

//		drawStatus();		
		

		drawStatus();	
		
		
		if(playerAttacks)
			executeAttack(selection);
		else if(enemyAttacks && !winOn)
			executeEnemyAttack();

		else if(winOn)
			dialogs.showWinDialog();
		else if(loseOn)
			dialogs.showLoseDialog();
		else if(lvlOn)
			dialogs.showLevelUp();
		else if(newAttOn)
			dialogs.showNewAttack();
		else if(newMagOn)
			dialogs.showNewMagic();
		
		else if(showIntroDialog)
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
	
	/**
	 * Zeichnet die Statusleisten des Spieler und des Gegners
	 * (Lebenspunkte, Mana)
	 */
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
	
	/**
	 * Methode, die den Angriff des Players ausfuehrt und zeichnen laesst.
	 * @param selection - ausgewaehlte Attacke / Magie
	 */
	public void executeAttack(int selection)
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
			{
				dmg = computeDamage(attack);
				
				enemy.decreaseHealth(dmg / 50);
			}
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
				enemyAttacks = true;
				anim = 0;
				
				if(enemy.getHealth() <= 0)
				{
					winOn = true;
					enemyAttacks = false;
					
					if(!this.gotXP)
					{
						player.increaseXP(enemy.givesXP());
						gotXP = true;
					}
				}
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
			{
				dmg = computeDamage(magic);
				
				enemy.decreaseHealth(dmg / 50);
			}
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
				enemyAttacks = true;
				anim = 0;
				
				if(enemy.getHealth() <= 0)
				{
					winOn = true;
					enemyAttacks = false;
					
					if(!this.gotXP)
					{
						player.increaseXP(enemy.givesXP());
						gotXP = true;
					}
				}
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
	
	/**
	 * Methode, die den Angriff des Gegners ausfuehrt und zeichnen laesst.
	 */
	private void executeEnemyAttack()
	{
		if(action == -1)
		{
			action = (int)(Math.random() * ((2 - 1) + 1) + 1);
			
			if(enemy.getEnemyMagic() != null)
			{
				if(action == 2 && enemy.getMana() >= enemy.getEnemyMagic().manaCost())
					magic = enemy.getEnemyMagic();
				else
					attack = enemy.getEnemyAttack();
			}
			else
				attack = enemy.getEnemyAttack();
		}
		
		if(anim < 99)
		{
			anim++;
		}
		else if(attack != null)
		{
			if(anim == 100)
				snd.playSound(attack.getSound());
			
			if(anim < 120 && attack.dealDmg())
				StdDraw.picture(screenMidX + 100 - 12*(anim - 100), screenMidY + 100 - 8*(anim - 100), attack.getImageSrc());
			else if(anim < 140 && !attack.dealDmg())
				StdDraw.picture(screenMidX - 270 + time, screenMidY + 100, attack.getImageSrc());
			
			if(anim > 249 && attack.dealDmg())
			{
				dmg = computeEnemyDamage(attack);
				
				player.decreaseHealth(dmg / 50);
			}
			else if(!attack.dealDmg())
				enemy.handleAttack(attack);
			
			if(anim < 199)
				dialogs.showAttack(attack);
			else
				dialogs.showEffect(attack);
			
			anim++;
			
			if(anim > 299)
			{
				attack = null;
				enemyAttacks = false;
				action = -1;
				anim = 0;				
				
				if(player.getHealth() <= 0)
					loseOn = true;
			}

		}
		else if(magic != null)
		{
			if(anim == 100)
				snd.playSound(magic.getSound());
			
			if(anim < 120 && magic.dealDmg())
				StdDraw.picture(screenMidX + 100 - 12*(anim - 100), screenMidY + 100 - 8*(anim - 100), magic.getImageSrc());
			else if(anim < 30 && !magic.dealDmg())
				StdDraw.picture(screenMidX - 270 + time, screenMidY + 100, magic.getImageSrc());
			
			if(anim > 249 && magic.dealDmg())
			{
				dmg = computeEnemyDamage(magic);
				
				player.decreaseHealth(dmg / 50);
			}
			else if(!magic.dealDmg())
				enemy.handleMagic(magic);
			
			if(anim < 199)
				dialogs.showMagic(magic);
			else
				dialogs.showEffect(magic);
			
			anim++;
			
			if(anim > 299)
			{
				enemy.decreaseMana(magic.manaCost());
				magic = null;
				enemyAttacks = false;
				action = -1;
				anim = 0;				
				
				if(player.getHealth() <= 0)
					loseOn = true;
			}
		}
	}
	
	/**
	 * Spielschleife des Kampfes. Wird solange ausgefuehrt, wie der Kampf noch
	 * aktiv ist. </br></br>
	 * Nach dem Kampf wird wieder zum Spielfeld gewechselt und die Spielschleife des
	 * Spielfeldes wird wieder aufgerufen.
	 */
	private void run()
	{
		while(battleOn)
		{			
			computeDelta();
			
			if(!introPlayed)
				playIntro();
			else
			{
				StdDraw.show(5);
				{
					StdDraw.clear(Color.black);
					
					if(!playerAttacks && !enemyAttacks)
					{
						if((winOn || loseOn || lvlOn || newAttOn || newMagOn) && dialogs.anim >= 350)
							key.handleKeyInput();
						else if(!winOn && !loseOn && !lvlOn && !newAttOn && !newMagOn)
							key.handleKeyInput();
					}
					
					drawBattle();
				}
			}
		}

		if(playerWins)
			player.increaseCoins(enemy.givesGold());
			
		parent.player1.resetTemps();
		parent.mapScreen = true;
		parent.battleScreen = false;
		parent.enemy.remove(enemy);
		snd.stopSound(0);
		parent.player1.go();
		parent.run();

	}
	
	/**
	 * Berechnet die fuer den letzten Spielschleifendurchlauf benoetigte Zeit in
	 * Nanosekunden (fuer fluessige Animationen)
	 */
	private void computeDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		
		fps = ((long) 1e9)/delta;
	}
	
	/**
	 * Berechnetes den von einer Attacke verursachten Schaden
	 * @param attack - Benutzte Attacke
	 * @return Verursachter Schaden
	 */
	private double computeDamage(Attack attack)
	{
		//Grundschaden eines Angriffs
		double damage = (attack.getStrength() * (player.getAtt() / (10.0 + (enemy.getDef() / 30))) + 1);
		
		if(attack.getType().equalsIgnoreCase("Normal"))
		{
			if(enemy.getType().equalsIgnoreCase("Hip"))
				damage = damage * 1.3;
			else if(enemy.getType().equalsIgnoreCase("Cool"))
				damage = damage * 0.85;
			else if(enemy.getType().equalsIgnoreCase("Swag"))
				damage = damage * 0.8;
		}
			
		else if(attack.getType().equalsIgnoreCase("True"))
		{
			if(enemy.getType().equalsIgnoreCase("Swag"))
				damage = damage * 1.6;
			else if(enemy.getType().equalsIgnoreCase("Whore"))
				damage = damage * 0.65;
			else if(enemy.getType().equalsIgnoreCase("Duckf."))
				damage = damage * 0.8;
		}
			
		else if(attack.getType().equalsIgnoreCase("Knowl."))
		{
			if(enemy.getType().equalsIgnoreCase("Swag"))
				damage = damage * 1.45;
			else if(enemy.getType().equalsIgnoreCase("Duckf."))
				damage = damage * 1.3;
			else if(enemy.getType().equalsIgnoreCase("Dumb"))
				damage = damage * 0.7;
		}
			
		else if(attack.getType().equalsIgnoreCase("Brutal"))
		{
			if(enemy.getType().equalsIgnoreCase("Dumb"))
				damage = damage * 1.7;
			else if(enemy.getType().equalsIgnoreCase("Whore"))
				damage = damage * 1.4;
			else if(enemy.getType().equalsIgnoreCase("Hip"))
				damage = damage * 1.275;
			else if(enemy.getType().equalsIgnoreCase("Star"))
				damage = damage * 0.75;
		}
			
		else if(attack.getType().equalsIgnoreCase("Psy."))
		{
			if(enemy.getType().equalsIgnoreCase("Star"))
				damage = damage * 1.25;
			else if(enemy.getType().equalsIgnoreCase("Whore"))
				damage = damage * 1.3;
			else if(enemy.getType().equalsIgnoreCase("Dumb"))
				damage = damage * 0.8;
		}
			
		else if(attack.getType().equalsIgnoreCase("Abzug"))
		{
			if(enemy.getType().equalsIgnoreCase("Cool"))
				damage = damage * 1.5;
			else if(enemy.getType().equalsIgnoreCase("Duckf."))
				damage = damage * 1.2;
			else if(enemy.getType().equalsIgnoreCase("Hip"))
				damage = damage * 0.75;
		}
	
		
		return damage;
	}
	
	
	/**
	 * Berechnetes den von einem Zauber verursachten Schaden
	 * @param attack - Benutzter Zauber
	 * @return Verursachter Schaden
	 */
	private double computeDamage(Magic attack)
	{
		//Grundschaden eines Zaubers
		double damage = (attack.getStrength() * (1.4 * player.getSpez() / (10.0 + (enemy.getSpez() / 30))) + 1);
		
		if(attack.getType().equalsIgnoreCase("Magic"))
		{
			if(enemy.getType().equalsIgnoreCase("Hip"))
				damage = damage * 1.35;
			else if(enemy.getType().equalsIgnoreCase("Cool"))
				damage = damage * 1.5;
			else if(enemy.getType().equalsIgnoreCase("Swag"))
				damage = damage * 0.6;
		}
			
		else if(attack.getType().equalsIgnoreCase("Feuer"))
		{
			if(enemy.getType().equalsIgnoreCase("Whore"))
				damage = damage * 1.6;
			else if(enemy.getType().equalsIgnoreCase("Duckf."))
				damage = damage * 1.55;
			else if(enemy.getType().equalsIgnoreCase("Dumb"))
				damage = damage * 0.8;
		}
			
		else if(attack.getType().equalsIgnoreCase("Wasser"))
		{
			if(enemy.getType().equalsIgnoreCase("Whore"))
				damage = damage * 1.4;
			else if(enemy.getType().equalsIgnoreCase("Swag"))
				damage = damage * 1.25;
			else if(enemy.getType().equalsIgnoreCase("Hip"))
				damage = damage * 0.8;
			else if(enemy.getType().equalsIgnoreCase("Cool"))
				damage = damage * 0.75;
		}
			
		else if(attack.getType().equalsIgnoreCase("Chemisch"))
		{
			if(enemy.getType().equalsIgnoreCase("Dumb"))
				damage = damage * 1.65;
			else if(enemy.getType().equalsIgnoreCase("Cool"))
				damage = damage * 1.4;
			else if(enemy.getType().equalsIgnoreCase("Whore"))
				damage = damage * 0.75;
			else if(enemy.getType().equalsIgnoreCase("Duckf."))
				damage = damage * 0.675;
		}
			
		else if(attack.getType().equalsIgnoreCase("Tod"))
		{
			if(enemy.getType().equalsIgnoreCase("Star"))
				damage = damage * 1.2;
			else
				damage = damage * 1.75;
		}
			
		else if(attack.getType().equalsIgnoreCase("Goettl."))
		{
			damage = damage * 2;
		}
	
		
		return damage;
	}
	
	/**
	 * Berechnetes den von einer Attacke verursachten Schaden des Gegners
	 * @param attack - Benutzte Attacke
	 * @return Verursachter Schaden
	 */
	private double computeEnemyDamage(Attack attack)
	{
		//Grundschaden eines Angriffs
		double damage = (attack.getStrength() * (enemy.getAtt() / (10.0 + (player.getDef() / 30))) + 1);
		
		if(attack.getType().equalsIgnoreCase("Hip"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 1.4;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Brutal"))
				damage = damage * 1.25;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 0.6;
		}
			
		else if(attack.getType().equalsIgnoreCase("Swag"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Psy."))
				damage = damage * 1.35;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Normal"))
				damage = damage * 1.5;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 0.7;
		}
			
		else if(attack.getType().equalsIgnoreCase("Whore"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Psy."))
				damage = damage * 1.45;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Knowl."))
				damage = damage * 1.3;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 0.7;
		}
			
		else if(attack.getType().equalsIgnoreCase("Duckf."))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Normal"))
				damage = damage * 1.7;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 1.4;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Brutal"))
				damage = damage * 0.75;
		}
			
		else if(attack.getType().equalsIgnoreCase("Dumb"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 1.25;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 1.3;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Knowl."))
				damage = damage * 0.8;
		}
			
		else if(attack.getType().equalsIgnoreCase("Cool"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Knowl."))
				damage = damage * 1.5;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 1.2;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Brutal"))
				damage = damage * 0.75;
		}
	
		
		return damage;
	}
	
	
	/**
	 * Berechnetes den von einem Zauber verursachten Schaden des Gegners
	 * @param attack - Benutzter Zauber
	 * @return Verursachter Schaden
	 */
	private double computeEnemyDamage(Magic attack)
	{
		//Grundschaden eines Zaubers
		double damage = (attack.getStrength() * (1.25 * enemy.getSpez() / (10.0 + (player.getSpez() / 30))) + 1);
		
		if(attack.getType().equalsIgnoreCase("Hip"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 1.4;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Brutal"))
				damage = damage * 1.25;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 0.6;
		}
			
		else if(attack.getType().equalsIgnoreCase("Swag"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Psy."))
				damage = damage * 1.35;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Normal"))
				damage = damage * 1.5;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 0.7;
		}
			
		else if(attack.getType().equalsIgnoreCase("Whore"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Psy."))
				damage = damage * 1.45;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Knowl."))
				damage = damage * 1.3;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 0.7;
		}
			
		else if(attack.getType().equalsIgnoreCase("Duckf."))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Normal"))
				damage = damage * 1.7;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 1.4;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Brutal"))
				damage = damage * 0.75;
		}
			
		else if(attack.getType().equalsIgnoreCase("Dumb"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 1.25;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("True"))
				damage = damage * 1.3;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Knowl."))
				damage = damage * 0.8;
		}
			
		else if(attack.getType().equalsIgnoreCase("Cool"))
		{
			if(player.getEquippedArmor().getType().equalsIgnoreCase("Knowl."))
				damage = damage * 1.5;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Abzug"))
				damage = damage * 1.2;
			else if(player.getEquippedArmor().getType().equalsIgnoreCase("Brutal"))
				damage = damage * 0.75;
		}
	
		
		return damage;
	}
}
