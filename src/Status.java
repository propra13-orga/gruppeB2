import java.awt.Color;
import java.awt.Font;


public class Status 
{
	Player player;
	
	public Status(Player player)
	{
		this.player = player;
	}
	
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
	
	public void drawDialog(String [] dialog)
	{
		int lines = dialog.length;
		
		Font font = new Font("Arial", Font.PLAIN, 13);
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.setFont(font);
		
		for(int l = 0; l < lines; l++)
			StdDraw.textLeft(353, 48 - l * 15, dialog[l]);
	}
	
	public void setAvatar(String avatar)
	{
		StdDraw.picture(637, 35, avatar);
	}
}
