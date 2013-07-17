import java.awt.Color;
import java.awt.Font;

/**
 * Stellt die Statusleiste des Spielfeldes zur Verfuegung
 * @author Mike Bechtel
 *
 */
public class Status 
{
	/**
	 * Speichert den Spieler
	 */
	Player player;
	/**
	 * Platzhalter fuer die NPC- und Enemy-Avatare
	 */
	String avatar;
	
	/**
	 * Konstruktor der Statusleiste
	 * @param player - Spieler, von dem der Status gezeichnet wird
	 */
	public Status(Player player)
	{
		this.player = player;
	}
	
	/**
	 * Zeichnet die Statusleiste
	 */
	public void draw()
	{
		Font font = new Font("Arial", Font.BOLD, 18);
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(font);
		
		StdDraw.picture(90, 28, "images/status/status_low_midSize.png");
		StdDraw.text(167, 9, "" + player.getCoins());

		StdDraw.picture(59.5, 45, "images/status/health_bar/health_bar.png");
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledRectangle(24 + player.getHealth() * (50.0 / player.getMaxHealth()), 45, player.getHealth() * (50.0 / player.getMaxHealth()), 11);
		
		StdDraw.picture(59.5, 9, "images/status/mana_bar/mana_bar.png");
		StdDraw.setPenColor(Color.YELLOW);
		StdDraw.filledRectangle(24 + player.getMana() * (50.0 / player.getMaxMana()), 9, player.getMana() * (50.0 / player.getMaxMana()), 11);
		
		StdDraw.picture(503, 28, "images/status/dialog_field.png");
	}
	
	/**
	 * Zeichnet die NPC- und Enemy-Dialoge
	 */
	public void drawDialog(String [] dialog)
	{
		StdDraw.picture(503, 28, "images/status/dialog_field.png");
		StdDraw.picture(637, 35, avatar);
		
		int lines = dialog.length;
		
		Font font = new Font("Arial", Font.PLAIN, 13);
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(font);
		
		for(int l = 0; l < lines; l++)
			StdDraw.textLeft(353, 48 - l * 15, dialog[l]);
	}
	
	/**
	 * Zeichnet die Interaktions-Info (Druecke E fuer Interaktion)
	 * @param dialog - Interaktionsdialog
	 */
	public void drawInfo(String [] dialog)
	{
		StdDraw.picture(503, 28, "images/status/dialog_field.png");
		
		int lines = dialog.length;
		
		Font font = new Font("Arial", Font.PLAIN, 13);
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(font);
		
		for(int l = 0; l < lines; l++)
			StdDraw.textLeft(353, 48 - l * 15, dialog[l]);
	}
	
	/**
	 * Setzt das zu zeichnende Avatar
	 * @param avatar - Zu zeichnendes Avatar
	 */
	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}
}
