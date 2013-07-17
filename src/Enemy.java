
import java.awt.Rectangle;

/**
 *  Diese abstrakte Klasse stellen die Enemys des Spiels dar.
 *  Sie erben von der Klasse Rectangle, um die Kollisionen mit dem Spieler zu realisieren.
 * 
 *  <p>
 *  Die vererbten, abstrakten Methoden beschreiben genauer die Eigenschaften eines
 *  Enemy fuer die Logik des Spiels.
 *  </p>
 *  
 *  @author Mike Bechtel
 */
abstract class Enemy extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert die momentanen und maximalen Lebenspunkte
	 */
	protected double health, maxHealth;
	/**
	 * Speichert die momentanen und maximalen Manapunkte
	 */
	protected double mana, maxMana;
	/**
	 * Speichert die Angriffs-, Verteidigungs-, Spezial- und Genauigkeitswerte
	 */
	protected double attack, defense, spez, gena;
	
	/**
	 * Speichert die Erfahrung sowie das Gold, welches der Spieler bei einem
	 * Sieg erhaelt
	 */
	protected int xp, gold;
	
	/**
	 * Speichert den Typ des Gegners (Feuer, Wasser, Swag, etc.)
	 */
	protected String type;
	
	/**
	 * Speichert temporaere Attributsaenderungen (waehrend des Kampfs durch Attacken
	 * und Zauber beeinflusst)
	 */
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
	
	/**
	 * Methode zum Zeichnen des Gegners
	 */
	public abstract void drawImg();
	/**
	 * Methode, die prueft, ob der Spieler in Angriffslinie ist
	 */
	public abstract boolean playerInLine(Player player);
	/**
	 * Methode, die den Gegner zum Spieler bewegt
	 * @param player - Spieler, zu dem der Gegner sich bewegt
	 * @param delta - Hilfsvariable zur fluessigen Animation
	 */
	public abstract void moveToPlayer(Player player, long delta);
	/**
	 * Prueft die Kollisionen mit einem Rectangle
	 * @param rect - Rectangle, mit dem Kollision geprueft wird
	 * @return Ein <b>int</b>, welches die Seite der Kollision angibt 
	 */
	public abstract int checkCollision(Rectangle rect);
	
	/**
	 * Gibt den Dateipfad des Avatars zurueck
	 * @return Dateipfad des Avatars
	 */
	public abstract String getAvatar();
	/**
	 * Gibt den Level des Gegners zurueck
	 * @return Level des Gegners
	 */
	public abstract int getLevel();	
	/**
	 * Gibt die Dialogseite <i>page</i> zurueck
	 * @param page - Seite, die zurueckgegeben wird
	 * @return Dialogseite
	 */
	public abstract String [] getDialog(int page);
	/**
	 * Gibt den gesamten Dialog zurueck
	 * @return Dialog-Array
	 */
	public abstract String [] getDialog();
	/**
	 * Gibts die erste Seite des Dialogs zurueck
	 * @return Startdialog
	 */
	public abstract String [] startDialog();
	/**
	 * Gibt die naechste Dialogseite zurueck
	 * @return Naechste Dialogseite
	 */
	public abstract String [] getNextDialogPage();
	/**
	 * Gibt zurueck, ob eine weitere Seite existiert
	 * @return <b>true</b> wenn eine weitere Seite existiert, <b>false</b> sonst
	 */
	public abstract boolean hasNextPage();
	
	/**
	 * Gibt eine String-Repraesentation des Gegners zurueck
	 */
	public abstract String toString();
	/**
	 * Gibt den Namen des Gegners zur konkrekten Dialoganzeige zurueck
	 */
	public abstract String getName();
	/**
	 * Gibt die Ausrichtung des Gegners zurueck
	 */
	public abstract String getDirection();

	/**
	 * Erhoeht die Lebenspunkte 
	 * @param amount - Erhoehe LP um <i>amount</i>
	 */
	public void increaseHealth(double amount)
	{
		this.health += amount;
	}
	/**
	 * Erhoeht die Manapunkte 
	 * @param amount - Erhoehe MP um <i>amount</i>
	 */
	public void increaseMana(double amount)
	{
		this.mana += amount;
	}
	/**
	 * Verkleiner die Lebenspunkte 
	 * @param amount - Verkleiner LP um <i>amount</i>
	 */
	public void decreaseHealth(double amount)
	{
		this.health -= amount;
		
		if(health < 0)
			health = 0;
	}
	/**
	 * Verkleiner die Manapunkte 
	 * @param amount - Verkleiner MP um <i>amount</i>
	 */
	public void decreaseMana(double amount)
	{
		this.mana -= amount;
		
		if(mana < 0)
			mana = 0;
	}

	/**
	 * Gibt die maximalen Lebenspunkte zurueck
	 * @return maximale LP
	 */
	public double getMaxHealth()
	{
		return this.maxHealth;
	}
	/**
	 * Gibt die maximalen Manapunkte zurueck
	 * @return maximale MP
	 */
	public double getMaxMana()
	{
		return this.maxMana;
	}

	/**
	 * Gibt die aktuellen Lebenspunkte zurueck
	 * @return momentane LP
	 */
	public double getHealth()
	{
		return this.health;
	}
	/**
	 * Gibt die aktuellen Manapunkte zurueck
	 * @return momentane MP
	 */
	public double getMana()
	{
		return this.mana;
	}

	/**
	 * Erhoeht das Attribut Armor
	 * @param amount - Erhoehe Armor um <i>amount</i>
	 */
	public void increaseArmor(double amount)
	{
		this.defense += amount;
		tempDef += amount;
	}
	/**
	 * Erhoeht das Attribut Attack
	 * @param amount - Erhoehe Attack um <i>amount</i>
	 */
	public void increaseAttack(double amount)
	{
		this.attack += amount;
		tempAtt += amount;
	}
	/**
	 * Erhoeht das Attribut Spez
	 * @param amount - Erhoehe Spez um <i>amount</i>
	 */
	public void increaseSpez(double amount)
	{
		this.spez += amount;
		tempSpez += amount;
	}
	/**
	 * Erhoeht das Attribut Gena
	 * @param amount - Erhoehe Gena um <i>amount</i>
	 */
	public void increaseGanu(double amount)
	{
		this.gena += amount;
		tempGena += amount;
	}

	/**
	 * Verkleiner das Attribut Armor
	 * @param amount - Verkleiner Armor um <i>amount</i>
	 */
	public void decreaseArmor(double amount)
	{
		this.defense -= amount;
	}
	/**
	 * Verkleiner das Attribut Attack
	 * @param amount - Verkleiner Attack um <i>amount</i>
	 */
	public void decreaseAttack(double amount)
	{
		this.attack -= amount;		
	}
	/**
	 * Verkleiner das Attribut Spez
	 * @param amount - Verkleiner Spez um <i>amount</i>
	 */
	public void decreaseSpez(double amount)
	{
		this.spez -= amount;		
	}
	/**
	 * Verkleiner das Attribut Gena
	 * @param amount - Verkleiner Gena um <i>amount</i>
	 */
	public void decreaseGena(double amount)
	{
		this.gena -= amount;	
	}

	/**
	 * Gibt eine zufaellige Attacke zurueck (fuer Battle)
	 * @return Zufaellige Attacke fuer Kaempfe
	 */
	public abstract Attack getEnemyAttack();
	/**
	 * Gibt eine zufaellige Magie zurueck (fuer Battle)
	 * @return Zufaellige Magie fuer Kaempfe
	 */
	public abstract Magic getEnemyMagic();

	/**
	 * Gibt das Attribut Attacke zurueck
	 * @return Attribut Attacke
	 */
	public double getAtt()
	{
		return this.attack;
	} 
	/**
	 * Gibt das Attribut Armor zurueck
	 * @return Attribut Armor
	 */
	public double getDef()
	{
		return this.defense;
	} 
	/**
	 * Gibt das Attribut Spez zurueck
	 * @return Attribut Spez
	 */
	public double getSpez()
	{
		return this.spez;
	} 
	/**
	 * Gibt das Attribut Gena zurueck
	 * @return Attribut Gena
	 */
	public double getGena()
	{
		return this.gena;
	} 
	
	/**
	 * Gibt die XP zurueck, die der Spieler erhalten kann
	 * @return XP, wenn der Spieler gewinnt
	 */
	public int givesXP()
	{
		return this.xp;
	}

	/**
	 * Gibt das Gold zurueck, das der Spieler erhalten kann
	 * @return Gold, wenn der Spieler gewinnt
	 */
	public int givesGold()
	{
		return this.gold;
	}
	
	/**
	 * Gibt den Typ des Gegners zurueck
	 * @return Typ des Gegners
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Methode, die passive Attacken / Zauber verarbeitet (Attributsaenderung)
	 * @param attack - Attributsaendernde Attacke
	 */
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

	/**
	 * Methode, die passive Attacken / Zauber verarbeitet (Attributsaenderung)
	 * @param attack - Attributsaendernder Zauber
	 */
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