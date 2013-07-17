import java.awt.Color;
import java.awt.Font;

/**
 * Enthaelt die Informationen zu den einzelnen Shop-Items, wie zum Beispiel deren
 * Preis, das benoetigte Level und eine Kurzbeschreibung.
 * @author Mike Bechtel
 *
 */
public class Shop_Descriptions 
{
	/**
	 * Speichert den geoeffneten Shop
	 */
	Shop shop;
	
	/**
	 * Konstruktor des Beshreibung
	 * @param shop - Zugehoeriger Shop
	 */
	public Shop_Descriptions(Shop shop)
	{
		this.shop = shop;
	}
	
	/**
	 * Gibt die Kurzbeschreibung der selektierten Waffe zurueck und zeichnet diese
	 * @param wep - Selektierte Waffe
	 */
	public void getWeaponInfo(Weapon wep)
	{
		String[] info;

		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(new Font("Arial", Font.BOLD, 13));
		
		switch(wep.type())
		{
		case CollectableTypes.WEAPON_KURZSCHWERT: 
			info = new String[] {
				"Leicht handhabbares", 
				"Schwert. Wegen den ge-",
				"ringen Herstellunskosten", 
				"billig.",
				"",
				"Geringe Wuchtkraft und",
				"wegen des haeufigen Vor-",
				"kommens bei Hipstern",
				"sehr unbeliebt."
			};
			break;
			
		case CollectableTypes.WEAPON_STREITKOLBEN: 
			info = new String[] {
				"Billige Wuchtwaffe. Gut", 
				"gegen gepanzerte",
				"Gegner.",
				"",
				"Schlaege auf den Kopf",
				"helfen das YOLO-Motto zu",
				"verdraengen und hebt",
				"den IQ des Opfers erheb-",
				"lich."
			};
			break;
			
		case CollectableTypes.WEAPON_KLEINE_AXT: 
			info = new String[] {
				"Einfache Holzaxt.", 
				"Schneidet Haare, Haut",
				"und Swag.",
				"",
				"Nimm dir ein Stueck",
				"Swag mit nach Hause und",
				"das fuer wenig Geld.",
				"Garantiert schmerzfrei!"
			};
			break;
			
		case CollectableTypes.WEAPON_LANGSCHWERT: 
			info = new String[] {
				"Oft benutztes und er-", 
				"probtes Schwert mit",
				"guter Reichweite",
				"",
				"Halte dir Duckfaces vom",
				"Hals oder tranchier",
				"sie mit Bratensauce zu",
				"Weihnachten."
			};
			break;
		
		case CollectableTypes.WEAPON_KRUMMSAEBEL: 
			info = new String[] {
					"Fast wie das Langschwert", 
					"doch wegen der coolen",
					"Kruemmung haste mehr",
					"Swag.",
					"",
					"Rumsitzen, Aufschlitzen",
					"- Das war gestern. Mach",
					"dich auf den Weg und",
					"nimm ein paar Hipster",
					"aus der Evolutions-",
					"kette!"
			};
			break;
			
		case CollectableTypes.WEAPON_KRUMMSCHWERT: 
			info = new String[] {
					"Schon Mal an Piraten", 
					"gedacht? - Brauchst du",
					"nicht, wenn du Swag",
					"hast!",
					"",
					"Und den hast du mit",
					"dieser Waffe.",
					"Geh raus und zeig",
					"allen dein neues Ich."
			};
			break;
			
		case CollectableTypes.WEAPON_MORGENSTERN: 
			info = new String[] {
					"Wo ist die Kette?", 
					"- Welche Kette? -",
					"Die du gleich in",
					"deinem Duckface hast!",
					"",
					"Das ist der Morgen-",
					"stern. Wuchtig und",
					"rabiat. Schlag ein paar",
					"Hippis zu Brei.",
					"",
					"Viel Spass!"
			};
			break;
			
		case CollectableTypes.WEAPON_GROSSE_AXT: 
			info = new String[] {
					"Doppelt so gross -", 
					"Doppelte K/D.",
					"Massiver Schaden und",
					"fehlende Glied-",
					"maﬂen!",
					"",
					"So lautet das Re-",
					"sultat dieser Wunder-",
					"waffe.",
					"",
					"Schnipp Schnapp -",
					"Pipi-Mann ab!"
			};
			break;
			
		case CollectableTypes.WEAPON_PRUNKSCHWERT: 
			info = new String[] {
					"Wie der Name schon", 
					"sagt, hat diese Waffe",
					"nett viel BLING-BLING.",
					"",
					"Teuer aber effektiv",
					"im Kampf gegen jede",
					"Form von Coolness."
			};
			break;
		
		default: info = new String[] {""};
	}
		
		drawInfo(info);

		StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 80, "Lvl - " + getReqLvl(wep));
		StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 55, "Dmg - " + wep.getBonus());
		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
		
		StdDraw.textLeft(shop.screenMidX + 30, shop.screenMidY - 139, "" + getPrice(wep));
	}
	
	/**
	 * Gibt die Kurzbeschreibung der selektierten Ruestung zurueck und zeichnet diese
	 * @param wep - Selektierte Ruestung
	 */
	public void getArmorInfo(Armor wep)
	{
		String[] info;
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(new Font("Arial", Font.BOLD, 13));
		
		switch(wep.type())
		{
		case CollectableTypes.ARMOR_BAUER: 
			info = new String[] {
				"Bauer sucht Frau - bzw.", 
				"Bauer sucht Attention-",
				"whore.", 
				"",
				"Luftig geschnitten und",
				"lecker riechend, doch",
				"kaum Schutz. Und billig",
				"noch dazu!"
			};
			break;
			
		case CollectableTypes.ARMOR_LEDER_HARNISCH: 
			info = new String[] {
				"Lack und Leder, lieber", 
				"Peter! Schliess dich",
				"unseren Spielchen an!",
				"",
				"Die patentierte Leder-",
				"garnitur ist Swag-sicher",
				"und schuetzt den Traeger",
				"vor leichten Attacken."
			};
			break;
			
		default: info = new String[] {""};
	}
		
		drawInfo(info);

		StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 80, "Lvl - " + getReqLvl(wep));
		StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 55, "Def - " + wep.getBonus());
		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
		
		StdDraw.textLeft(shop.screenMidX + 30, shop.screenMidY - 139, "" + getPrice(wep));
	}
	
	/**
	 * Gibt die Kurzbeschreibung des selektierten Items zurueck und zeichnet diese
	 * @param wep - Selektiertes Item
	 */
	public void getItemInfo(Collectable wep)
	{
		String[] info;
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(new Font("Arial", Font.BOLD, 13));
		
		switch(wep.type())
		{
		case CollectableTypes.HEALTH_BOTTLE: 
			info = new String[] {
				"Der grosse Heiltrank", 
				"fuer den grossen Hunger.",
				"Im Notfall ein echter",
				"Lebensretter!",
				"",
				"Ganze 50 Prozent der HP",
				"wird durch dieses Gesoeff",
				"regeneriert. - Was drinn",
				"ist? I dont know!"
			};
			StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 55, "Heilt " + wep.getBonus() + " HP");
			break;
			
		case CollectableTypes.MANA_BOTTLE: 
			info = new String[] {
				"Du brauchst irgendeine", 
				"mysterioese magische",
				"Kraft? Dieser Trank kann",
				"das.",
				"",
				"Nicht ganz billig, aber",
				"dann kannst du wieder",
				"Feuer und Eis und so",
				"machen!",
				"",
				"Whhooosshh!"
			};
			StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 55, "Heilt " + wep.getBonus() + " MP");
			break;
			
		default: info = new String[] {""};
	}
		
		drawInfo(info);

		StdDraw.textLeft(shop.screenMidX - 200, shop.screenMidY + 80, "Lvl - All");
		
		StdDraw.setFont(new Font("Arial", Font.BOLD, 20));
		
		StdDraw.textLeft(shop.screenMidX + 30, shop.screenMidY - 139, "" + getPrice(wep));
	}
	
	/**
	 * Gibt das benoetigte Level der Auswahl zurueck
	 * @param itm - Ausgewaehltes Item
	 * @return Benoetigtes Level
	 */
	public int getReqLvl(Collectable itm)
	{
		int lvl = 1;
		
		switch(itm.type())
		{
		case CollectableTypes.WEAPON_KURZSCHWERT: lvl = 1; break;
		case CollectableTypes.WEAPON_STREITKOLBEN: lvl = 3; break;
		case CollectableTypes.WEAPON_KLEINE_AXT: lvl = 3; break;
		case CollectableTypes.WEAPON_LANGSCHWERT: lvl = 5; break;
		case CollectableTypes.WEAPON_KRUMMSAEBEL: lvl = 6; break;
		case CollectableTypes.WEAPON_KRUMMSCHWERT: lvl = 7; break;
		case CollectableTypes.WEAPON_MORGENSTERN: lvl = 9; break;
		case CollectableTypes.WEAPON_GROSSE_AXT: lvl = 9; break;
		case CollectableTypes.WEAPON_PRUNKSCHWERT: lvl = 12; break;
		

		case CollectableTypes.ARMOR_BAUER: lvl = 1; break;
		case CollectableTypes.ARMOR_LEDER_HARNISCH: lvl = 3; break;
		}
		
		return lvl;
	}
	
	/**
	 * Gibt den Preis der Auswahl zurueck
	 * @param itm - Ausgewaehltes Item
	 * @return Preis
	 */
	public int getPrice(Collectable itm)
	{
		int price = 0;
		
		switch(itm.type())
		{
		case CollectableTypes.WEAPON_KURZSCHWERT: price = 200; break;
		case CollectableTypes.WEAPON_STREITKOLBEN: price = 450; break;
		case CollectableTypes.WEAPON_KLEINE_AXT: price = 550; break;
		case CollectableTypes.WEAPON_LANGSCHWERT: price = 1000; break;
		case CollectableTypes.WEAPON_KRUMMSAEBEL: price = 1200; break;
		case CollectableTypes.WEAPON_KRUMMSCHWERT: price = 1350; break;
		case CollectableTypes.WEAPON_MORGENSTERN: price = 1800; break;
		case CollectableTypes.WEAPON_GROSSE_AXT: price = 2100; break;
		case CollectableTypes.WEAPON_PRUNKSCHWERT: price = 2500; break;
		

		case CollectableTypes.ARMOR_BAUER: price = 150; break;
		case CollectableTypes.ARMOR_LEDER_HARNISCH: price = 500; break;
		

		case CollectableTypes.HEALTH_BOTTLE: price = 100; break;
		case CollectableTypes.MANA_BOTTLE: price = 150; break;
		}
		
		return price;
	}
	
	/**
	 * Zeichnet die Information
	 * @param info - Zu zeichnende Information
	 */
	private void drawInfo(String [] info)
	{
		int lines = info.length;
		
		for(int l = 0; l < lines; l++)
			StdDraw.textLeft(shop.screenMidX - 270, shop.screenMidY + 15 - l * 15, info[l]);
	}
}
