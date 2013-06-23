

public class Mana extends Item
{
	private static final long serialVersionUID = 1L;
	
	private int posX, posY;	
	
	private int anim;

	public Mana(int posX, int posY) 
	{
		super(posX, posY, 32, 32);
		
		this.posX = posX;
		this.posY = posY;
		
		anim = 0;
	}
	
	public boolean checkCollision(Player player)
	{
		if(this.intersects(player))
		{
			player.increaseMana(15);
			return true;
		}
		else
			return false;
	}

	public String toString() 
	{
		return "Mana";
	}

	public void drawImg() 
	{
		if(anim < 15)
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+1+".png");
			anim++;
		}
		else if(anim < 30)
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+2+".png");
			anim++;
		}
		else if(anim < 45)
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+1+".png");
			anim++;
		}
		else 
		{
			StdDraw.picture(posX, posY, "images/items/manastar/manastar_"+3+".png");
			anim++;
			
			if(anim == 59)
				anim = 0;
		}
	}
}
