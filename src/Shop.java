import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;


public class Shop 
{
	boolean shopOn;
	
	GameField field;
	Manager_Sound snd;
	Manager_Key key;
	
	Shop_Items sells;
	
	Font font;
	Font fontBold;
	
	Player player;
	int price;
	Collectable item;

	double screenMidX, screenMidY;
	
	boolean weaponsOn, armorOn, itemsOn, buyOn, successOn, errorOn;
	
	boolean buy, abort;
	
	int selection, maxSelection, selTab, time;
	
	public Shop(GameField field, Manager_Sound snd)
	{
		shopOn = true;
		
		this.field = field;
		this.snd = snd;
		
		try 
		{
			Font lfont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("fonts/battle_font.ttf"));
			font = lfont.deriveFont(Font.TRUETYPE_FONT, 16);
			fontBold = lfont.deriveFont(Font.BOLD, 16);
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
	
	public void run()
	{
		while(shopOn)
		{
			StdDraw.show(5);
			{
				key.handleKeyInput();
				
				field.draw();
				field.player1.draw();
			
				draw();
			}
		}
	}
}
