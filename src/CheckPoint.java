/**
 * Stellt einen Speicherpunkt im Spiel dar.
 * @author Mike Bechtel
 *
 */
public class CheckPoint 
{
	/**
	 * Speichert, in welchem Level der Speicherpunkt erzeugt wurde
	 */
	private int level;
	
	/**
	 * Speichert x- und y-Position des Checkpoints auf dem Spielfeld
	 */
	private double checkPosX, checkPosY;
	
	/**
	 * Konstruktor eines CheckPoints
	 * @param level - Level, in dem der Speicherpunkt erzeugt wird
	 * @param checkPosX - x-Position des CheckPoints auf dem Spielfeld
	 * @param checkPosY - y-Position des CheckPoints auf dem Spielfeld
	 */
	public CheckPoint(int level, double checkPosX, double checkPosY)
	{
		this.level = level;
		this.checkPosX = checkPosX;
		this.checkPosY = checkPosY;
	}
	
	/**
	 * Gibt zurueck, in welchem Level der Speicherpunkt erzeugt wurde
	 * @return Level, wo der CheckPoint gesetzt wurde
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gibt die x-Position des Speicherpunkts auf dem Spielfeld zurueck
	 * @return x-Position auf dem Spielfeld
	 */
	public double getPosX()
	{
		return checkPosX;
	}
	
	/**
	 * Gibt die y-Position des Speicherpunkts auf dem Spielfeld zurueck
	 * @return y-Position auf dem Spielfeld
	 */
	public double getPosY()
	{
		return checkPosY;
	}
	
	/**
	 * Setzt den Level des CheckPoints
	 * @param lvl - Zu setzendes Level
	 */
	public void setLevel(int lvl)
	{
		this.level = lvl;
	}
	
	/**
	 * Setzt die x-Position des CheckPoints auf dem Spielfeld
	 * @param posX - x-Position auf dem Spielfeld
	 */
	public void setPosX(double posX)
	{
		this.checkPosX = posX;
	}
	
	/**
	 * Setzt die y-Position des CheckPoints auf dem Spielfeld
	 * @param posY - y-Position auf dem Spielfeld
	 */
	public void setPosY(double posY)
	{
		this.checkPosY = posY;
	}
}
