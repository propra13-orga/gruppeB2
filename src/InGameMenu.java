import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Stellt das In-Game-Menu dar (wenn der Spieler waehrend des Spiels die ESC
 * Taste drueckt.
 * @author Mike Bechtel
 *
 */
public class InGameMenu 
{
	/**
	 * Speichert, ob das Menu noch geoeffnet ist
	 */
	boolean menuOn;
	
	/**
	 * Speichert, welche Layer gezeichnet werden sollen
	 */
	boolean mainSelOn, itemSelOn, itemUseOn;
	
	/**
	 * Speichert das Eltern-Spielfeld, auf welchem das Menu gezeichnet wird
	 */
	GameField parent;
	/**
	 * Speichert den Sound-Manager
	 */
	Manager_Sound snd;
	
	/**
	 * Seichert die Schriftart
	 */
	Font font;
	
	/**
	 * Speichert die x-Mitte des Fensters
	 */
	double screenMidX;
	/**
	 * Speichert die y-Mitte des Fensters
	 */
	double screenMidY;
	
	/**
	 * Speichert den Tasten-Manager
	 */
	Manager_Key key;
	
	/**
	 * Speichert die momentane Auswahl bzw. Item-Auswahl
	 */
	int selection, itemSel;
	/**
	 * Hilfsvariablen zur Darstellung des Item-Auswahlmenus
	 */
	int upper, lower, posAbs;
	
	/**
	 * Konstruktor des InGameMenus
	 * @param field - Eltern-Spielfeld, auf dem das InGameMenu gezeichnet wird
	 * @param snd - Sound-Manager wird vom Spielfeld uebergeben
	 */
	public InGameMenu(GameField field, Manager_Sound snd)
	{
		menuOn = true;
		
		this.parent = field;
		this.snd = snd;
		
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
		
		screenMidX = (field.columns * 40) / 2;
		screenMidY = ((field.rows + 2) * 40 + 80) / 2 - 40;
		
		this.key = new Manager_Key(this);
		
		selection = 1;
		itemSel = 1;
		
		mainSelOn = true;
		itemSelOn = false;
		itemUseOn = false;
		
		run();
	}
	
	/**
	 * Zeichnet das Menu und die entsprechenden Layer
	 */
	public void draw()
	{
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(font);
	
		StdDraw.picture(2 * screenMidX - 100, 2 * screenMidY - 125, "images/menu/in_game/bg.png");
	
		StdDraw.textLeft(2 * screenMidX - 180, 2 * screenMidY - 30, "ITEM");	
		StdDraw.textLeft(2 * screenMidX - 180, 2 * screenMidY - 70, "STATUS");	
		StdDraw.textLeft(2 * screenMidX - 180, 2 * screenMidY - 110, "SICHERN");	
		StdDraw.textLeft(2 * screenMidX - 180, 2 * screenMidY - 150, "OPTIONEN");	
		StdDraw.textLeft(2 * screenMidX - 180, 2 * screenMidY - 190, "ZURÜCK");
		
		if(mainSelOn)
		{
			StdDraw.picture(2 * screenMidX - 195, 2 * screenMidY - 28 - 40 * (selection - 1), "images/menu/in_game/selection_arrow.png");
		}
		else if(itemSelOn)
		{
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.setFont(font);
			
			StdDraw.picture(2 * screenMidX - 130, 2 * screenMidY - 150, "images/menu/in_game/items.png");
			
			for(int i = lower; i < upper; i++)
			{
				if(parent.player1.getEquippedWeapon().type() == parent.player1.inventory.getItemAt(i).type())
					StdDraw.setPenColor(Color.YELLOW);
				else if(parent.player1.getEquippedArmor().type() == parent.player1.inventory.getItemAt(i).type())
					StdDraw.setPenColor(Color.CYAN);
				else
					StdDraw.setPenColor(Color.WHITE);
				
				StdDraw.textLeft(2 * screenMidX - 230, 2 * screenMidY - 90 - 40 * (i-lower), parent.player1.inventory.getItemAt(i).toString().toUpperCase());
				StdDraw.picture(2 * screenMidX - 60, 2 * screenMidY - 108 - 40 * (i-lower), "images/menu/in_game/times.png");
				StdDraw.textLeft(2 * screenMidX - 50, 2 * screenMidY - 110 - 40 * (i-lower), "" + parent.player1.inventory.getItemAt(i).getCount());
			}
		
			if(parent.player1.inventory.size() > 3 && !itemUseOn)
			{
				posAbs = upper - itemSel;
				
				StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 208 + 40 * posAbs, "images/menu/in_game/selection_arrow.png");
			}
			else if(parent.player1.inventory.size() > 0 && !itemUseOn)
			{
				StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 88 - 40 * (selection - 1), "images/menu/in_game/selection_arrow.png");
			}
			
			if(parent.player1.inventory.size() == 0)				
				StdDraw.textLeft(2 * screenMidX - 250, 2 * screenMidY - 70, "KEINE ITEMS");
			
			if(lower > 0)
				StdDraw.picture(2 * screenMidX - 20, 2 * screenMidY - 60, "images/menu/in_game/arrow_up.png");
			if(upper < parent.player1.inventory.size())
				StdDraw.picture(2 * screenMidX - 20, 2 * screenMidY - 240, "images/menu/in_game/arrow_down.png");
			
			if(itemUseOn)
			{
				StdDraw.setPenColor(Color.WHITE);
				StdDraw.picture(2 * screenMidX - 157, 2 * screenMidY - 153.5 - 40* (key.selBefore - 1), "images/menu/in_game/items_use.png");	

				StdDraw.textLeft(2 * screenMidX - 232, 2 * screenMidY - 140 - 40* (key.selBefore - 1), "BENUTZEN");
				StdDraw.textLeft(2 * screenMidX - 232, 2 * screenMidY - 180 - 40* (key.selBefore - 1), "MÜLL");			
				
				StdDraw.picture(2 * screenMidX - 247, 2 * screenMidY - 138 - 40* (key.selBefore - 1) - 40 * (selection - 1), "images/menu/in_game/selection_arrow.png");
			}
		}
	}
	
	/**
	 * Spielschleife des Menus
	 */
	public void run()
	{
		while(menuOn)
		{
			StdDraw.show(5);
			{
				key.handleKeyInput();
				
				parent.draw();
				parent.player1.draw();
			
				draw();
			}
		}
	}
}
