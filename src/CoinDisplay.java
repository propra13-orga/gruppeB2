
public class CoinDisplay extends Block
{
	private int posX, posY;
	
	private int coins;
	
	public CoinDisplay(int posX, int posY)
	{
		super(posX, posY, 40, 40);
		
		this.posX = posX;
		this.posY = posY;
	}
	@Override
	boolean isSolid() 
	{
		return true;
	}

	@Override
	public String toString()
	{
		return "coins";
	}

	@Override
	void drawImg()
	{
		for(int i=0;i<10;i++)
		{
			if(coins==i)
				StdDraw.picture(posX, posY, "images/status/coin"+i+".png");
		}		
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	

}
