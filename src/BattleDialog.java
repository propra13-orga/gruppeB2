import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;


public class BattleDialog 
{
	BattleScreen battle;
	
	Font font;
	Font fontBold;
	
	double screenMidX, screenMidY;
	
	int posAbs;
	
	public BattleDialog(BattleScreen battle)
	{
		this.battle = battle;
		
		this.screenMidX = battle.screenMidX;
		this.screenMidY = battle.screenMidY;
		
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
	
	public void showIntroDialog()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.enemy.getName().toUpperCase());
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "möchte kämpfen");
	}
	
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
	
	public void showStats(Attack attack)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX - 128, screenMidY - 60, "images/battle/logic/attack_stats.png");

		StdDraw.textLeft(screenMidX - 220, screenMidY - 42, "TYP/");
		StdDraw.textLeft(screenMidX - 210, screenMidY - 62, attack.getType());
		StdDraw.textRight(screenMidX - 26, screenMidY - 82, attack.getDmgType() + " " + (int)attack.getStrength());
	}
	
	public void showStats(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);
		
		StdDraw.picture(screenMidX - 128, screenMidY - 60, "images/battle/logic/attack_stats.png");

		StdDraw.textLeft(screenMidX - 220, screenMidY - 42, "TYP/");
		StdDraw.textLeft(screenMidX - 210, screenMidY - 62, magic.getType());
		StdDraw.textRight(screenMidX - 26, screenMidY - 82, magic.getDmgType() + " " + (int)magic.getStrength());
	}

	public void showAttack(Attack attack)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + attack.getName().toUpperCase());	
		
	}
	public void showMagic(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + magic.getName().toUpperCase());	
	}
	public void showEffect(Attack attack)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(!attack.getEffect().equals("none"))
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());		
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, attack.getDesc());	
		}
		else
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());		
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + attack.getName().toUpperCase());	
		}
	}
	public void showEffect(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		if(!magic.getEffect().equals("none"))
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());		
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, magic.getEffect().toUpperCase() + " steigt.");	
		}
		else
		{
			StdDraw.textLeft(screenMidX - 220, screenMidY - 130, battle.player.getPlayerName());		
			StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "ben. " + magic.getName().toUpperCase());	
		}
	}
	public void showNoMana(Magic magic)
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, "Nicht genug Mana!");		
		StdDraw.textLeft(screenMidX - 220, screenMidY - 160, "Benötigt: " + (int)magic.manaCost());		
	}
	
	public void showEscapeDialog()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.white);

		StdDraw.textLeft(screenMidX - 220, screenMidY - 130, "Du bist entkommen ...");
	}
}
