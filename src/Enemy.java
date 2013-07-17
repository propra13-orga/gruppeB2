
import java.awt.Rectangle;

/**
 *  <i>NPC</i>. Diese abstrakte Klasse stellen die Enemys des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  Enemy fuer die Logik des Spiels.
 *  </p>
 */
abstract class Enemy extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	protected double health, maxHealth;
	protected double mana, maxMana;
	protected double attack, defense, spez, gena;
	
	protected int xp, gold;
	
	public double tempAtt, tempDef, tempSpez, tempGena;
	
    /**
     * Konstruktor eines Enemy-Objekts
     *
     * @param posX - die x-Position (Feld) des Enemy (Rectangle)
     * @param posY - die y-Position (Feld) des Enemy (Rectangle)
     * @param sizeX - die x-Groesse des Enemy fuer die Kollision(Rectangle)
     * @param sizeY - die y-Groesse des Enemy fuer die Kollision(Rectangle)
     */
	public Enemy(int posX, int posY, int sizeX, int sizeY)
	{
		super(posX, posY, sizeX, sizeY);
	}
	
	public abstract void drawImg();
	public abstract boolean playerInLine(Player player);
	public abstract void moveToPlayer(Player player, long delta);
	public abstract int checkCollision(Rectangle rect);
	
	public abstract String getAvatar();
	public abstract int getLevel();	
	public abstract String [] getDialog(int page);
	public abstract String [] getDialog();
	public abstract String [] startDialog();
	public abstract String [] getNextDialogPage();
	public abstract boolean hasNextPage();
	
	public abstract String toString();
	public abstract String getName();
	public abstract String getDirection();

	public void increaseHealth(double amount)
	{
		this.health += amount;
	}
	public void increaseMana(double amount)
	{
		this.mana += amount;
	}
	public void decreaseHealth(double amount)
	{
		this.health -= amount;
		
		if(health < 0)
			health = 0;
	}
	public void decreaseMana(double amount)
	{
		this.mana -= amount;
		
		if(mana < 0)
			mana = 0;
	}

	public double getMaxHealth()
	{
		return this.maxHealth;
	}
	public double getMaxMana()
	{
		return this.maxMana;
	}

	public double getHealth()
	{
		return this.health;
	}
	public double getMana()
	{
		return this.mana;
	}

	public void increaseArmor(double amount)
	{
		this.defense += amount;
		tempDef += amount;
	}
	public void increaseAttack(double amount)
	{
		this.attack += amount;
		tempAtt += amount;
	}
	public void increaseSpez(double amount)
	{
		this.spez += amount;
		tempSpez += amount;
	}
	public void increaseGanu(double amount)
	{
		this.gena += amount;
		tempGena += amount;
	}
	
	public void decreaseArmor(double amount)
	{
		this.defense -= amount;
	}
	public void decreaseAttack(double amount)
	{
		this.attack -= amount;		
	}
	public void decreaseSpez(double amount)
	{
		this.spez -= amount;		
	}
	public void decreaseGena(double amount)
	{
		this.gena -= amount;	
	}

	public abstract Attack getEnemyAttack();
	public abstract Magic getEnemyMagic();

	public double getAtt()
	{
		return this.attack;
	} 
	public double getDef()
	{
		return this.defense;
	} 
	public double getSpez()
	{
		return this.spez;
	} 
	public double getGena()
	{
		return this.gena;
	} 
	
	
	public int givesXP()
	{
		return this.xp;
	}
	
	public int givesGold()
	{
		return this.gold;
	}
	
	
	public void handleAttack(Attack attack)
	{
		switch(attack.getEffect())
		{
		case "none": break;
		case "vert.": 
			if(tempDef <= 30)
				this.increaseArmor(attack.getStrength()/200); 
			break;
		case "angr.": 
			if(tempAtt <= 30)
				this.increaseAttack(attack.getStrength()/200); 
			break;
		
		default: break;
		}
	}
	
	public void handleMagic(Magic attack)
	{
		switch(attack.getEffect())
		{
		case "none": break;
		case "vert.": 
			if(tempDef <= 30)
				this.increaseArmor(attack.getStrength()/200); 
			break;
		case "angr.": 
			if(tempAtt <= 30)
				this.increaseAttack(attack.getStrength()/200); 
			break;
		
		default: break;
		}
	}
}