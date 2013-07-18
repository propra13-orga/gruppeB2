import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Die Klasse Battle-Dialog liefert Methoden, um die einzelnen Dialoge
 * und Layer im Kampfsystem darzustellen
 * @author Mike Bechtel
 *
 */
public class BattleDialog 
{
	/**
	 * Speichert den Eltern-BattleScreen, auf dem die DIaloge gezeichnet werden
	 */
	BattleScreen battle;
	
	/**
	 * Speichert die Schriftart
	 */
	Font font;
	
	/**
	 * Speichert die x-und y-Mitte der Zeichenflaeche
	 */
	double screenMidX, screenMidY;
	
	/**
	 * Hilfsvariable fuer die richtige Auswahl im Item-Menu
	 */
	int posAbs;
	
	/**
	 * Hilfsvariable zur animierten Darstellung bestimmter Dialoge
	 */
	int anim;
	

	/**
	 * Speichert eine neu erlernbare Attacke
	 */
	Attack newAtt = null;
	/**
	 * Speichert einen neu erlernbaren Zauber
	 */
	Magic newMag = null;
	
	/**
	 * Konstruktor eines BattleDialogs.
	 * @param battle - Eltern-BattleScreen, auf dem die Dialoge gezeichnet werden
	 */
	public BattleDialog(BattleScreen battle)
	{
		this.battle = battle;
		
		this.screenMidX = battle.screenMidX;
		this.screenMidY = battle.screenMidY;
		
		anim = 0;
		
		try 
		{
			Font lfont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("fonts/battle_font.ttf"));
			font = lfont.deriveFont(Font.TRUETYPE_FONT, 16);
		} catch (FontFormatException | IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Es trat ein Fehler beim Lesen der Schriftart auf (fonts/battle_font.ttf)");
			System.exit(0);
		}
	}
	
	/**
	 * Zeichnet den Intro-Dialog des Battles (Gegner moechte kaempfen)
	 */
	public void showIntroDialog()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName().toUpperCase());
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "möchte kämpfen");
	}
	
	/**
	 * Zeichnet das Hauptselektionsmenu (Angr, Magic, Items, Flucht)
	 * @param selection - ausgewaehltes Menu-Element
	 */
	public void showSelectionDialog(int selection)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX + 83, screenMidY - 145, "images/battle/logic/selection.png");

		StdDraw.textLeft(screenMidX - 40, screenMidY - 130, "ANGR");
		StdDraw.textLeft(screenMidX + 100, screenMidY - 130, "MAGIE");
		StdDraw.textLeft(screenMidX - 40, screenMidY - 170, "ITEM");
		StdDraw.textLeft(screenMidX + 100, screenMidY - 170, "FLUCHT");
		
		switch(selection)
		{
		case 1: StdDraw.picture(screenMidX - 55, screenMidY - 128, "images/battle/logic/selection_arrow.png"); break;
		case 2: StdDraw.picture(screenMidX + 85, screenMidY - 128, "images/battle/logic/selection_arrow.png"); break;
		case 3: StdDraw.picture(screenMidX - 55, screenMidY - 168, "images/battle/logic/selection_arrow.png"); break;
		case 4: StdDraw.picture(screenMidX + 85, screenMidY - 168, "images/battle/logic/selection_arrow.png"); break;
		}
	}
	
	/**
	 * Zeichnet das Selektionsmenu der moeglichen Angriffe
	 * @param selection - ausgewaehlte Attacke
	 */
	public void showAngrDialog(int selection)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX + 58, screenMidY - 145, "images/battle/logic/selection_angr.png");
		
		for(int i = 1; i < 5; i++)
		{
			if(battle.player.getAttack(i) != null)
				StdDraw.textLeft(screenMidX - 90, screenMidY - 100 - 20 * i, battle.player.getAttack(i).getName());
			else
				StdDraw.textLeft(screenMidX - 90, screenMidY - 100 - 20 * i, "-");
		}
		
		switch(selection)
		{
		case 1: StdDraw.picture(screenMidX - 105, screenMidY - 118, "images/battle/logic/selection_arrow.png"); break;
		case 2: StdDraw.picture(screenMidX - 105, screenMidY - 138, "images/battle/logic/selection_arrow.png"); break;
		case 3: StdDraw.picture(screenMidX - 105, screenMidY - 158, "images/battle/logic/selection_arrow.png"); break;
		case 4: StdDraw.picture(screenMidX - 105, screenMidY - 178, "images/battle/logic/selection_arrow.png"); break;
		}
		
		this.showStats(battle.player.getAttack(selection));
	}
	
	/**
	 * Zeichnet das Selektionsmenu der moeglichen Zauber
	 * @param selection - ausgewaehlter Zauber
	 */
	public void showMagicDialog(int selection)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX + 58, screenMidY - 145, "images/battle/logic/selection_angr.png");
		
		for(int i = 1; i < 5; i++)
		{
			if(battle.player.getMagic(i) != null)
				StdDraw.textLeft(screenMidX - 90, screenMidY - 100 - 20 * i, battle.player.getMagic(i).getName());
			else
				StdDraw.textLeft(screenMidX - 90, screenMidY - 100 - 20 * i, "-");
		}
		
		switch(selection)
		{
		case 1: StdDraw.picture(screenMidX - 105, screenMidY - 118, "images/battle/logic/selection_arrow.png"); break;
		case 2: StdDraw.picture(screenMidX - 105, screenMidY - 138, "images/battle/logic/selection_arrow.png"); break;
		case 3: StdDraw.picture(screenMidX - 105, screenMidY - 158, "images/battle/logic/selection_arrow.png"); break;
		case 4: StdDraw.picture(screenMidX - 105, screenMidY - 178, "images/battle/logic/selection_arrow.png"); break;
		}

		this.showStats(battle.player.getMagic(selection));
	}
	
	/**
	 * Zeichnet das Selektionsmenu der Item-Auswahl
	 * @param selection - ausgewaehltes Item
	 */
	public void showItemDialog(int selection)
	{
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(font);
		
		StdDraw.picture(screenMidX - 100, screenMidY - 40, "images/menu/in_game/items.png");
		
		for(int i = battle.lower; i < battle.upper; i++)
		{
			if(battle.parent.player1.getEquippedWeapon().type() == battle.parent.player1.inventory.getItemAt(i).type())
				StdDraw.setPenColor(Color.YELLOW);
			else
				StdDraw.setPenColor(Color.WHITE);
			
			StdDraw.textLeft(screenMidX - 200, screenMidY + 20 - 40 * (i-battle.lower), battle.parent.player1.inventory.getItemAt(i).toString().toUpperCase());
			StdDraw.picture(screenMidX - 30, screenMidY + 2 - 40 * (i-battle.lower), "images/menu/in_game/times.png");
			StdDraw.textLeft(screenMidX - 20, screenMidY - 40 * (i-battle.lower), "" + battle.parent.player1.inventory.getItemAt(i).getCount());
		}
	
		if(battle.parent.player1.inventory.size() > 3 && !battle.itemUseOn)
		{
			posAbs = battle.upper - battle.itemSel;
			
			StdDraw.picture(screenMidX - 215, screenMidY - 98 + 40 * posAbs, "images/menu/in_game/selection_arrow.png");
		}
		else if(battle.parent.player1.inventory.size() > 0 && !battle.itemUseOn)
		{				
			StdDraw.picture(screenMidX - 215, screenMidY + 22 - 40 * (selection - 1), "images/menu/in_game/selection_arrow.png");
		}
		
		if(battle.parent.player1.inventory.size() == 0)				
			StdDraw.textLeft(screenMidX - 220, screenMidY + 40, "KEINE ITEMS");
		
		if(battle.lower > 0)
			StdDraw.picture(screenMidX + 10, screenMidY + 50, "images/menu/in_game/arrow_up.png");
		if(battle.upper < battle.parent.player1.inventory.size())
			StdDraw.picture(screenMidX + 10, screenMidY - 130, "images/menu/in_game/arrow_down.png");
		
		if(battle.itemUseOn)
		{
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.picture(screenMidX - 127, screenMidY + 96.5 - 40* (battle.key.selBefore - 1), "images/menu/in_game/items_use.png");	

			StdDraw.textLeft(screenMidX - 202, screenMidY + 110 - 40* (battle.key.selBefore - 1), "BENUTZEN");
			StdDraw.textLeft(screenMidX - 202, screenMidY + 70 - 40* (battle.key.selBefore - 1), "MÜLL");			
			
			StdDraw.picture(screenMidX - 217, screenMidY + 112 - 40* (battle.key.selBefore - 1) - 40 * (selection - 1), "images/menu/in_game/selection_arrow.png");
		}
	}
	
	/**
	 * Zeichnet ein Info-Fenster, in dem die Eigenschaften der ausgewaehlten Attacke kurz
	 * beschrieben werden
	 * @param selection - ausgewaehlte Attacke
	 */
	public void showStats(Attack attack)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX - 128, screenMidY - 60, "images/battle/logic/attack_stats.png");

		StdDraw.textLeft(screenMidX - 220, screenMidY - 42, "TYP/");
		StdDraw.textLeft(screenMidX - 210, screenMidY - 62, attack.getType());
		StdDraw.textRight(screenMidX - 26, screenMidY - 82, attack.getDmgType() + " " + (int)attack.getStrength());
	}
	
	/**
	 * Zeichnet ein Info-Fenster, in dem die Eigenschaften des ausgewaehlten Zaubers kurz
	 * beschrieben werden
	 * @param selection - ausgewaehlter Zauber
	 */
	public void showStats(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX - 128, screenMidY - 60, "images/battle/logic/attack_stats.png");

		StdDraw.textLeft(screenMidX - 220, screenMidY - 42, "TYP/");
		StdDraw.textLeft(screenMidX - 210, screenMidY - 62, magic.getType());
		StdDraw.textRight(screenMidX - 26, screenMidY - 82, magic.getDmgType() + " " + (int)magic.getStrength());
	}

	/**
	 * Schreibt in das Textfeld waehrend des Angriffs den Anwender und den Namen der Attacke
	 * @param attack - benutzte Attacke
	 */
	public void showAttack(Attack attack)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(battle.enemyAttacks)
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName());
		else
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());
		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + attack.getName().toUpperCase());	
		
	}
	
	/**
	 * Schreibt in das Textfeld waehrend des Angriffs den Anwender und den Namen des Zaubers
	 * @param attack - benutzter Zauber
	 */
	public void showMagic(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(battle.enemyAttacks)
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName());
		else
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());
		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + magic.getName().toUpperCase());	
	}
	
	/**
	 * Schreibt in das Textfeld waehrend des Angriffs den Effekt der benutzten Attacke
	 * @param attack - benutzte Attacke
	 */
	public void showEffect(Attack attack)
	{		
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(!attack.getEffect().equals("none"))
		{
			if(battle.playerAttacks && !battle.enemyAttacks)
			{
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());
				
				if(attack.getEffect().equals("vert.") && battle.player.tempDef >= 30)
				{		
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "DEF maximal.");
				}
				else if(attack.getEffect().equals("angr.") && battle.player.tempAtt >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ATT maximal.");					
				}
				else if(attack.getEffect().equals("gena") && battle.player.tempGena >= 20)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "GENA maximal.");
				}
				else if(attack.getEffect().equals("spez") && battle.player.tempSpez >= 25)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "SPEZ maximal.");	
				}
				else
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, attack.getDesc());	
			}
			else
			{
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName());
				
				if(attack.getEffect().equals("vert.") && battle.enemy.tempDef >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "DEF maximal.");
				}
				else if(attack.getEffect().equals("angr.") && battle.enemy.tempAtt >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ATT maximal.");						
				}
				else if(attack.getEffect().equals("gena") && battle.enemy.tempGena >= 20)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "GENA maximal.");		
				}
				else if(attack.getEffect().equals("spez") && battle.enemy.tempSpez >= 25)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "SPEZ maximal.");		
				}
				else
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, attack.getDesc());	
			}
		}
		else
		{
			if(battle.enemyAttacks)
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName());
			else
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());
					
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + attack.getName().toUpperCase());	
		}
	}
	
	/**
	 * Schreibt in das Textfeld waehrend des Angriffs den Effekt des benutzten Zaubers
	 * @param attack - benutzter Zauber
	 */
	public void showEffect(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(!magic.getEffect().equals("none"))
		{
			if(battle.enemyAttacks)
			{
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName());
				
				if(magic.getEffect().equals("vert.") && battle.player.tempDef >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "DEF maximal.");	
				}
				else if(magic.getEffect().equals("angr.") && battle.player.tempAtt >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ATT maximal.");					
				}
				else if(magic.getEffect().equals("gena") && battle.player.tempGena >= 20)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "GENA maximal.");	
				}
				else if(magic.getEffect().equals("spez") && battle.player.tempSpez >= 25)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "SPEZ maximal.");	
				}
				else
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, magic.getEffect().toUpperCase() + " steigt.");	
			}
			else
			{
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());
				
				if(magic.getEffect().equals("vert.") && battle.enemy.tempDef >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "DEF maximal.");	
				}
				else if(magic.getEffect().equals("angr.") && battle.enemy.tempAtt >= 30)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ATT maximal.");						
				}
				else if(magic.getEffect().equals("gena") && battle.enemy.tempGena >= 20)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "GENA maximal.");	
				}
				else if(magic.getEffect().equals("spez") && battle.enemy.tempSpez >= 25)
				{
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "SPEZ maximal.");		
				}
				else
					StdDraw.textLeft(screenMidX - 220, screenMidY - 160, magic.getEffect().toUpperCase() + " steigt.");	
			}
		}
		else
		{
			if(battle.enemyAttacks)
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName());
			else
				StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());
				
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + magic.getName().toUpperCase());	
		}
	}
	
	/**
	 * Zeigt an, falls der Spieler einen Zauber auswaehlt, der mehr Mana kostet, als der
	 * Spieler zur Verfuegung hat
	 * @param magic - Magie, die benutzt werden will
	 */
	public void showNoMana(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, "Nicht genug Mana!");		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "Benötigt: " + (int)magic.manaCost());		
	}
	
	/**
	 * Info-Dialog, wenn der Spieler den Gegner besiegt hat (erhaelt Erfahrung und Gold)
	 */
	public void showWinDialog()
	{	
		battle.playerWins = true;
		
		if(anim == 0)
		{
			battle.snd.stopSound(0);
			battle.snd.stopSound(9);
			battle.snd.playSound(7);
		}
		
		if(anim < 25)
		{
			StdDraw.picture(screenMidX - 270 + battle.time + 4*anim, screenMidY + 100, "images/enemy/" + battle.enemy.toString() + "/battle.png");
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledRectangle(screenMidX - 270 + battle.time + 110, screenMidY + 100, 40, 80);
		}

		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		if(anim < 200)
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName().toUpperCase());
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "wurde besiegt.");
			
			anim++;
		}
		else
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName() + " erhält");
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "" + battle.enemy.givesXP() + " XP und " + battle.enemy.givesGold() + " Gold.");
		
			if(anim < 350)
				anim++;
			else if(anim == 350)
			{
				if(battle.player.canLevelUp())
				{
					battle.lvlOn = true;
					battle.winOn = false;
					anim = 150;
				}
			}
		}
	}
	
	/**
	 * Zeichnet den Dialog, wenn der Spieler ein neues Level erreicht
	 */
	public void showLevelUp()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		if(anim == 150)
			battle.player.levelUp();
		
		StdDraw.picture(screenMidX + 150, screenMidY - 50, "images/battle/logic/lvl_up.png");
		StdDraw.textLeft(screenMidX + 90, screenMidY + 0, "ATT: " + battle.player.getAttPure());
		StdDraw.textLeft(screenMidX + 90, screenMidY - 30, "DEF: " + battle.player.getDefPure());
		StdDraw.textLeft(screenMidX + 90, screenMidY - 60, "SPEZ:" + battle.player.getSpez());
		StdDraw.textLeft(screenMidX + 90, screenMidY - 90, "GENA:" + battle.player.getGena());
	
		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName() + " erreicht");
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "Level " + battle.player.getLevel());
	
		if(anim < 350)
			anim++;
		else if(anim == 350)
		{
			if(battle.player.learnsNewAttack())
			{
				battle.newAttOn = true;
				battle.lvlOn = false;
				anim = 150;
			}
			else if(battle.player.learnsNewMagic())
			{
				battle.newMagOn = true;
				battle.lvlOn = false;
				anim = 150;
			}
		}
	}
	
	/**
	 * Zeigt den Dialog an, dass der Spieler eine neue Attacke gelernt hat
	 */
	public void showNewAttack()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		if(anim == 150)
		{
			newAtt = Attacks.learnAtt(battle.player.getLevel());
			battle.player.learnAttack(newAtt);
		}
		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName() + " erlernt");
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "" + newAtt.getName().toUpperCase());
	
		if(anim < 350)
			anim++;
	}
	
	/**
	 * Zeigt den Dialog an, dass der Spieler einen neuen Zauber gelernt hat
	 */
	public void showNewMagic()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		if(anim == 150)
		{
			newMag = Attacks.learnMag(battle.player.getLevel());
			battle.player.learnMagic(newMag);
		}
		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName() + " erlernt");
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "" + newMag.getName().toUpperCase());
	
		if(anim < 350)
			anim++;
	}
	
	/**
	 * Zeichnet den Dialog, wenn der Spieler vom Gegner besiegt wurde
	 */
	public void showLoseDialog()
	{	
		battle.playerLose = true;
		
		if(anim < 25)
		{
			StdDraw.picture(screenMidX + 280 - battle.time - 4*anim, screenMidY - 42, "images/player/battle.png");
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledRectangle(screenMidX + 280 - battle.time - 130, screenMidY - 42, 40, 80);
		}

		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		if(anim < 200)
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName().toUpperCase());
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "besiegt " + battle.player.getPlayerName().toUpperCase());
			
			anim++;
		}
		else
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName() + " wird");
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "zu Tode geswagt.");
		
			if(anim < 350)
				anim++;
		}
	}
	
	/**
	 * Zeichnet die Info, wenn man vor dem Kampf fluechtet
	 */
	public void showEscapeDialog()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, "Du bist entkommen ...");
	}
	
	/**
	 * Zeichnet die Info, wenn der vor dem Kamp fluechtet, dies aber nicht moeglich ist
	 */
	public void showNoEscapeDialog()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(anim < 200)
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, "Du kannst nicht aus");
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "Bosskaempfen fluechten!");
			
			anim++;
		}
		
		if(anim == 200)
		{
			battle.noescapeOn = false;
			battle.selectionOn = true;
			anim = 0;
		}
	}
}
