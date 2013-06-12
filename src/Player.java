import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;

/**
 *  <i>Player</i>. Respaesentiert das Spieler-Objekt, welches die von Benutzer
 *  gesteuerte Spielfigut darstellt.
 */
public class Player extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	//x- und y-Position, an welcher der Spieler sich auf dem Spielfeld befindet.
	private int posX, posY;

	// energywert 
	private double health=50.0; 
	
	// manawert
	private double mana=0.0;
	
	//Anzahl Gold
	private int money;
	
	//Schwert
	private int sword;
	
	private Fireball fire;

	//Variablen zur Animation der Bewegung
	int swpL = 0;
	int swpR = 0;
	int swpU = 0;
	int swpD = 0;
	
    /**
     * Konstruktor eines Player-Objekts
     *
     * @param posX - die x-Position des Spielers zu Beginn (Rectangle)
     * @param posY - die y-Position des Spielers zu Beginn (Rectangle)
     */
	public Player(int posX, int posY)
	{
		//Das Player-Objekt ist ein Rectangle-Objekt der Groesse 26x26 
		//(Etwas kleiner als ein Block fuer flexibelere Bewegung). Entsprechend
		//wird die Position des Rectangles etwas versetzt angegeben
		super(posX + 3, posY - 6, 26, 26);
		
		this.posX = posX;
		this.posY = posY;
		
		fire = new Fireball(posX, posY);
	}

	/**
     * Methode zum Zeichnen der Bewegungsanimation. Die Bilder wechseln periodisch
     * je nach Beweungsrichtung
     * 
     * @param direction - In welche Richtung bewegt sich der Spieler momentan
     */
	public void swapImg(String direction)
	{
		if(direction.equalsIgnoreCase(Direction.LEFT))
		{
			if(swpL < 6)
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_left_"+1+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_left_"+1+".1.png");
				swpL++;
			}
			else
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_left_"+2+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_left_"+2+".1.png");
				swpL++;
				
				if(swpL == 11)
					swpL = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.RIGHT))
		{
			if(swpR < 6)
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_right_"+1+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_right_"+1+".1.png");
				swpR++;
			}
			else
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_right_"+2+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_right_"+2+".1.png");
				swpR++;
				
				if(swpR == 11)
					swpR = 0;				
			}
		}
		else if(direction.equalsIgnoreCase(Direction.UP))
		{
			if(swpU < 6)
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_up_"+1+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_up_"+1+".1.png");
				swpU++;
			}
			else
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_up_"+2+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_up_"+2+".1.png");
				swpU++;
				
				if(swpU == 11)
					swpU = 0;					
			}
		}
		else if(direction.equalsIgnoreCase(Direction.DOWN))
		{
			if(swpD < 6)
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_down_"+1+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_down_"+1+".1.png");
				swpD++;
			}
			else
			{
				if (sword==0)
					StdDraw.picture(posX, posY, "images/player/player_down_"+2+".png");
				else
					StdDraw.picture(posX, posY, "images/player/player_down_"+2+".1.png");
				swpD++;
				
				if(swpD == 11)
					swpD = 0;				
			}
		}
	}
	public void attack(String direction)
	{
		if (direction.equalsIgnoreCase(Direction.LEFT))
				StdDraw.picture(posX, posY, "images/player/player_left_"+1+".attack.png");
		if (direction.equalsIgnoreCase(Direction.RIGHT))
				StdDraw.picture(posX, posY, "images/player/player_right_"+1+".attack.png");
		if (direction.equalsIgnoreCase(Direction.UP))
				StdDraw.picture(posX, posY, "images/player/player_up_"+1+".attack.png");
		if (direction.equalsIgnoreCase(Direction.DOWN))
				StdDraw.picture(posX, posY, "images/player/player_down_"+1+".attack.png");
	}
	
	 /**
     * Methode zum Zeichnen des Players, wenn er sich nicht bewegt
     */
	public void draw()
	{
		
		if (sword==0)
			StdDraw.picture(posX, posY, "images/player/player_down_"+1+".png");
		else
			StdDraw.picture(posX, posY, "images/player/player_down_"+1+".1.png");
	}

	 /**
     * Methode, welche die Position des Player-Objekts und des Rectangles setzt
     * 
     * @param posX - x-Position des Spielers
     * @param posY - y-Position des Spielers
     */
	public void setPos(int posX, int posY)
	{
		this.posX = posX;
		this.posY = posY;
		
		this.setLocation(posX + 3, posY - 6);
	}

	 /**
     * Methode, welche die x-Position des Player-Objekts und des Rectangles setzt
     *      
     * @param posX - x-Position des Spielers
     */
	public void setPosX(int posX)
	{
		this.posX = posX;
		this.setLocation(posX + 3, posY - 6);
	}

	 /**
     * Methode, welche die y-Position des Player-Objekts und des Rectangles setzt
     *
     * @param posY - y-Position des Spielers
     */
	public void setPosY(int posY)
	{
		this.posY = posY;
		this.setLocation(posX + 3, posY - 6);
	}
	
	 /**
     * Methode, welche die x-Position des Spielers zurueckgibt
     *
     * @return x-Position des Spieler-Objekts
     */
	public double getPosX()
	{
		return posX;
	}
	
	/**
    * Methode, welche die y-Position des Spielers zurueckgibt
    *
    * @return y-Position des Spieler-Objekts
    */
	public double getPosY()
	{
		return posY;
	}
	public double getHealth() {
		if (health>100.0)
			health=100.0;
		else if(health<0)
				health=0;
		return health;
		
	}

	public void setHealth(double health) {
		this.health = this.health + health;
	}
	
	public void setHealthDown(double health) {
		this.health = this.health - health;
	}
	
	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = this.money + money;
	}
	
	public double getMana() {
		if (mana>100.0)
			mana=100.0;
		else if(mana<0)
				mana=0;
		return mana;
	}

	public void setMana(double mana) {
		this.mana = this.mana + mana;
	}

	public int getSword() {
		if(sword>0)
			sword=1;
			else if(sword<0)
				sword=0;
		return sword;
	}

	public void setSword(int sword) {
		this.sword = this.sword + sword;
	}
	
	public Fireball getFire() {
		return fire;
	}

	public void setFire(Fireball fire) {
		this.fire = fire;
	}
	
}
