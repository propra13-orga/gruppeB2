import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;


public class InGameMenu 
{
	boolean menuOn;
	
	boolean mainSelOn, itemSelOn;
	
	GameField parent;
	SoundManager snd;
	
	Font font;
	
	double screenMidX;
	double screenMidY;
	
	KeyManager key;
	boolean pressedE;
	boolean pressedESC;
	
	int selection;
	int upper, lower, posAbs;
	
	public InGameMenu(GameField field, SoundManager snd)
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
		
		this.key = new KeyManager(this);
		
		pressedE = false;
		pressedESC = false;
		
		selection = 1;
		mainSelOn = true;
		itemSelOn = false;
		
		run();
	}
	
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
			switch(selection)
			{
			case 1: StdDraw.picture(2 * screenMidX - 195, 2 * screenMidY - 28, "images/menu/in_game/selection_arrow.png"); break;
			case 2: StdDraw.picture(2 * screenMidX - 195, 2 * screenMidY - 68, "images/menu/in_game/selection_arrow.png"); break;
			case 3: StdDraw.picture(2 * screenMidX - 195, 2 * screenMidY - 108, "images/menu/in_game/selection_arrow.png"); break;
			case 4: StdDraw.picture(2 * screenMidX - 195, 2 * screenMidY - 148, "images/menu/in_game/selection_arrow.png"); break;
			case 5: StdDraw.picture(2 * screenMidX - 195, 2 * screenMidY - 188, "images/menu/in_game/selection_arrow.png"); break;
			}
		}
		else if(itemSelOn)
		{
			StdDraw.setPenColor(Color.WHITE);
			StdDraw.setFont(font);
			
			StdDraw.picture(2 * screenMidX - 130, 2 * screenMidY - 150, "images/menu/in_game/items.png");
			
			/*for(int i = 0; i < parent.player1.inventory.size(); i++)
			{
				StdDraw.textLeft(2 * screenMidX - 230, 2 * screenMidY - 90 - 40 * i, parent.player1.inventory.getItemAt(i).toString().toUpperCase());
				StdDraw.picture(2 * screenMidX - 60, 2 * screenMidY - 108 - 40 * i, "images/menu/in_game/times.png");
				StdDraw.textLeft(2 * screenMidX - 50, 2 * screenMidY - 110 - 40 * i, "" + parent.player1.inventory.getItemAt(i).getCount());
			}*/
			
			for(int i = lower; i < upper; i++)
			{
				StdDraw.textLeft(2 * screenMidX - 230, 2 * screenMidY - 90 - 40 * (i-lower), parent.player1.inventory.getItemAt(i).toString().toUpperCase());
				StdDraw.picture(2 * screenMidX - 60, 2 * screenMidY - 108 - 40 * (i-lower), "images/menu/in_game/times.png");
				StdDraw.textLeft(2 * screenMidX - 50, 2 * screenMidY - 110 - 40 * (i-lower), "" + parent.player1.inventory.getItemAt(i).getCount());
			}
		
			if(parent.player1.inventory.size() > 3)
			{
				posAbs = upper - selection;
				
				StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 208 + 40 * posAbs, "images/menu/in_game/selection_arrow.png");
			}
			else if(parent.player1.inventory.size() > 0)
			{
				switch(selection)
				{
				case 1: StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 88, "images/menu/in_game/selection_arrow.png"); break;
				case 2: StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 128, "images/menu/in_game/selection_arrow.png"); break;
				case 3: StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 168, "images/menu/in_game/selection_arrow.png"); break;
				case 4: StdDraw.picture(2 * screenMidX - 245, 2 * screenMidY - 208, "images/menu/in_game/selection_arrow.png"); break;			
				}
			}
			
			if(parent.player1.inventory.size() == 0)				
				StdDraw.textLeft(2 * screenMidX - 250, 2 * screenMidY - 70, "KEINE ITEMS");
		}
	}
	
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
