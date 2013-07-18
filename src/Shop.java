import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Stellt den Shop des Spiels dar. Berechnet Logic und laesst die verschiedenen
 * Layer auf der Zeichenflaeche zeichnen.
 * @author Mike Bechtel
 *
 */
public class Shop 
{
	/**
	 * Speichert, ob der Shop noch aktiv ist
	 */
	boolean shopOn;
	
	/**
	 * Speichert das Spielfeld, auf dem der Shop aufgerufen wurde
	 */
	GameField field;
	/**
	 * Speicher den Sound-Manager fuer die Sound-Wiedergabe
	 */
	Manager_Sound snd;
	/**
	 * Speichert den Key-Manager fuer Tastendruecke
	 */
	Manager_Key key;
	
	/**
	 * Speicher die shopbaren Items
	 */
	Shop_Items sells;
	
	/**
	 * Speichert die Schriftart
	 */
	Font font;
	
	/**
	 * Speichert den Spieler
	 */
	Player player;
	/**
	 * Speichert den Preis des ausgewaehlten Items
	 */
	int price;
	/**
	 * Speichert das ausgewaehlte Item
	 */
	Collectable item;
	
	/**
	 * Speichert die x- und y-Fenstermitte
	 */
	double screenMidX, screenMidY;
	
	/**
	 * Speichert <b>booleans</b> welche Layer gezeichnet werden
	 */
	boolean weaponsOn, armorOn, itemsOn, buyOn, successOn, errorOn;
	
	/**
	 * Speichert, ob der Spieler ein Item kaufen will oder Abbrechen will
	 */
	boolean buy, abort;
	
	/**
	 * Speichert die momentane Auswahl, die maximale Auswahl, das selektierte Tab
	 */
	int selection, maxSelection, selTab;
	
	/**
	 * Hilfsvariable fuer fluessige Animation
	 */
	int time;
	
	/**
	 * Konstruktor des Shops
	 * @param field - GameField in dem der Shop erzeugt wurde
	 * @param snd - Sound-Manager des GameFields
	 */
	public Shop(GameField field, Manager_Sound snd)
	{
		shopOn = true;
		
		this.field = field;
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
		
		player = field.player1;
		price = 0;
		item = null;
		
		buyOn = successOn = errorOn = false;
		
		screenMidX = (field.columns * 40) / 2;
		screenMidY = ((field.rows + 2) * 40 + 80) / 2 - 40;
		
		sells = new Shop_Items(this);
		
		this.key = new Manager_Key(this);
		
		selection = 0;
		maxSelection = 0;
		selTab = 0;
		
		time = 0;
		
		run();
	}
	
	/**
	 * Zeichnet den Shop und dessen Layer
	 */
	public void draw()
	{
		switch(selTab)
		{
		case 0: weaponsOn = true; armorOn = itemsOn = false; break;
		case 1: armorOn = true; weaponsOn = itemsOn = false; break;
		case 2: itemsOn = true; weaponsOn = armorOn = false; break;
		}
		
		StdDraw.picture(screenMidX, screenMidY, "images/shop/bg.png");
		

		StdDraw.setPenColor(Color.WHITE);
		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 25));
		StdDraw.text(screenMidX - 200, screenMidY + 142, "WAFFEN");
		StdDraw.text(screenMidX - 30, screenMidY + 142, "RUESTUNG");
		StdDraw.text(screenMidX + 120, screenMidY + 142, "ITEMS");

		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
		
		StdDraw.textLeft(screenMidX + 180, screenMidY - 139, "" + field.player1.getCoins());

		if(weaponsOn)
		{
			StdDraw.setPenRadius(.005);
			StdDraw.setPenColor(new Color(255,212,41));
			StdDraw.rectangle(screenMidX - 200, screenMidY + 147, 70, 20);
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.setPenRadius();
			
			sells.showWeapons();
		}
		if(armorOn)
		{
			StdDraw.setPenRadius(.005);
			StdDraw.setPenColor(new Color(255,212,41));
			StdDraw.rectangle(screenMidX - 30, screenMidY + 147, 90, 20);
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.setPenRadius();
			
			sells.showArmors();
		}
		if(itemsOn)
		{
			StdDraw.setPenRadius(.005);
			StdDraw.setPenColor(new Color(255,212,41));
			StdDraw.rectangle(screenMidX + 120, screenMidY + 147, 54, 20);
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.setPenRadius();
			
			sells.showItems();
		}
		if(buyOn)
		{
			sells.showConfirmDialog();
			
			if(successOn && time < 50)
				sells.showSuccess();
			else if(errorOn && time < 50)
				sells.showError();

			if(time < 50)
				time++;
			else
			{
				successOn = false;
				errorOn = false;
				time = 0;
			}
		}
	}
	
	/**
	 * Spielschleife des Shops
	 */
	public void run()
	{
		while(shopOn)
		{
			StdDraw.show(5);
			{
				if(!errorOn && !successOn)
					key.handleKeyInput();
				
				field.draw();
				field.player1.draw();
			
				draw();
			}
		}
	}
}
