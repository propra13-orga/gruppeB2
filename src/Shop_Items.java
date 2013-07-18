import java.awt.Color;
import java.awt.Font;

/**
 * Diese Klasse enthaelt alle shopbaren Items
 * @author Mike Bechtel
 *
 */
public class Shop_Items 
{
	/**
	 * Speichert den geoeffneten Shop
	 */
	Shop shop;
	/**
	 * Speichert die Item-Beschreibungen
	 */
	Shop_Descriptions desc;
	
	/**
	 * Speichert die x- und y-Fenstermitte
	 */
	double screenMidX, screenMidY;
	
	/**
	 * Speichert die Schriftart
	 */
	Font font;
	
	/**
	 * Konstruktor der Shop-Items
	 * @param shop - Zugehoeriger Shop
	 */
	public Shop_Items(Shop shop)
	{
		this.shop = shop;
		this.desc = new Shop_Descriptions(shop);
		
		this.screenMidX = shop.screenMidX;
		this.screenMidY = shop.screenMidY;
		
		this.font = shop.font;
	}
	
	/**
	 * Zeigt die shopbaren Waffen an
	 */
	public void showWeapons()
	{
		shop.maxSelection = 0;
		
		for(int i = 0; i < weapons.length; i++)
		{
			if(desc.getReqLvl(weapons[i]) > shop.field.player1.getLevel())
			{
				StdDraw.setPenColor(Color.DARK_GRAY);
			}
			else
			{
				StdDraw.setPenColor(Color.WHITE);
				shop.maxSelection++;
			}
			
			StdDraw.textLeft(screenMidX - 30, screenMidY + 80 - i * (21), weapons[i].toString());
		}

		StdDraw.setPenColor(Color.WHITE);
		
		StdDraw.picture(screenMidX - 45, screenMidY + 82 - 21 * shop.selection, "images/menu/in_game/selection_arrow.png");
		
		StdDraw.picture(screenMidX - 245, screenMidY + 70, weapons[shop.selection].picture);
		
		desc.getWeaponInfo(weapons[shop.selection]);
		
		shop.price = desc.getPrice(weapons[shop.selection]);
		shop.item = weapons[shop.selection];
	}
	
	/**
	 * Zeigt die shopbaren Ruestungen an
	 */
	public void showArmors()
	{
		shop.maxSelection = 0;
		
		for(int i = 0; i < armors.length; i++)
		{
			if(desc.getReqLvl(armors[i]) > shop.field.player1.getLevel())
			{
				StdDraw.setPenColor(Color.DARK_GRAY);
			}
			else
			{
				StdDraw.setPenColor(Color.WHITE);
				shop.maxSelection++;
			}
			
			StdDraw.textLeft(screenMidX - 30, screenMidY + 80 - i * (21), armors[i].toString());
		}

		StdDraw.setPenColor(Color.WHITE);
		
		StdDraw.picture(screenMidX - 45, screenMidY + 82 - 21 * shop.selection, "images/menu/in_game/selection_arrow.png");
		
		StdDraw.picture(screenMidX - 245, screenMidY + 70, armors[shop.selection].picture);
		
		desc.getArmorInfo(armors[shop.selection]);
		
		shop.price = desc.getPrice(armors[shop.selection]);
		shop.item = armors[shop.selection];
	}
	
	/**
	 * Zeigt die shopbaren Items an
	 */
	public void showItems()
	{
		shop.maxSelection = 0;
		
		for(int i = 0; i < items.length; i++)
		{
			if(desc.getReqLvl(items[i]) > shop.field.player1.getLevel())
			{
				StdDraw.setPenColor(Color.DARK_GRAY);
			}
			else
			{
				StdDraw.setPenColor(Color.WHITE);
				shop.maxSelection++;
			}
			
			StdDraw.textLeft(screenMidX - 30, screenMidY + 80 - i * (21), items[i].toString());
		}

		StdDraw.setPenColor(Color.WHITE);
		
		StdDraw.picture(screenMidX - 45, screenMidY + 82 - 21 * shop.selection, "images/menu/in_game/selection_arrow.png");
		
		StdDraw.picture(screenMidX - 245, screenMidY + 70, items[shop.selection].picture);
		
		desc.getItemInfo(items[shop.selection]);
		
		shop.price = desc.getPrice(items[shop.selection]);
		shop.item = items[shop.selection];
	}
	
	/**
	 * Zeigt einen Bestaetigungsdialog an, ob das ausgewaehlte Item wirklich
	 * gekauft werden soll
	 */
	public void showConfirmDialog()
	{
		StdDraw.picture(screenMidX, screenMidY, "images/shop/bg_confirm.png");

		StdDraw.textLeft(screenMidX - 90, screenMidY + 40, "Wollen Sie das");
		StdDraw.textLeft(screenMidX - 90, screenMidY + 10, "Item kaufen?");

		StdDraw.textLeft(screenMidX - 60, screenMidY - 40, "Ja");
		StdDraw.textLeft(screenMidX + 20, screenMidY - 40, "Nein");
		
		if(shop.buy)
			StdDraw.picture(screenMidX - 75, screenMidY - 35, "images/menu/in_game/selection_arrow.png");
		else
			StdDraw.picture(screenMidX + 5, screenMidY - 35, "images/menu/in_game/selection_arrow.png");			
	}
	
	/**
	 * Zeigt an, ob der Kauf eines Items erfolgreich war
	 */
	public void showSuccess()
	{
		StdDraw.picture(screenMidX, screenMidY, "images/shop/bg_confirm.png");

		StdDraw.textLeft(screenMidX - 70, screenMidY + 30, "Sie haben das");
		StdDraw.textLeft(screenMidX - 70, screenMidY, "Item erfolg-");
		StdDraw.textLeft(screenMidX - 70, screenMidY - 30, "reich gekauft.");
	}
	
	/**
	 * Zeigt an, ob der Kauf eines Items nicht erfolgreich war
	 */
	public void showError()
	{
		StdDraw.picture(screenMidX, screenMidY, "images/shop/bg_confirm.png");

		StdDraw.textLeft(screenMidX - 70, screenMidY + 30, "Nicht genug");
		StdDraw.textLeft(screenMidX - 70, screenMidY, "Geld. Item");
		StdDraw.textLeft(screenMidX - 70, screenMidY - 30, "nicht gekauft.");
	}
	
	//------------------------------------------------------------------------------------------

	/**
	 * Speichert die shopbaren Waffen
	 */
	private Weapon[] weapons = new Weapon[] {
			new Weapon_Kurzschwert(0,0), new Weapon_Streitkolben(0,0), new Weapon_Kleine_Axt(0,0),
			new Weapon_Langschwert(0,0), new Weapon_Krummsaebel(0,0), new Weapon_Krummschwert(0,0),
			new Weapon_Morgenstern(0,0), new Weapon_Grosse_Axt(0,0), new Weapon_Prunkschwert(0,0)
	};
	
	/**
	 * Speichert die shopbaren Ruestungen
	 */
	private Armor[] armors = new Armor[] {
			new Armor_Bauernkleidung(0,0), new Armor_Leder_Harnisch(0,0)
	};
	
	/**
	 * Speichert die shopbaren Items
	 */
	private Collectable[] items = new Collectable[] {
			new Item_HealthBottle(0,0), new Item_ManaBottle(0,0)
	};
}
