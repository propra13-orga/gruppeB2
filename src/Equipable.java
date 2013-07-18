/**
 * Interface Equipable wird von Items implementiert, die vom Spieler in irgendeiner
 * Weise benutzt werden koennen
 * @author Mike Bechtel
 *
 */
public interface Equipable 
{
	/**
	 * Prueft, ob das Objekt ausgereustet ist
	 * @return <b>true</b> wenn das Objekt ausgeruestet ist, <b>false</b> sonst
	 */
	public abstract boolean isEquipped();
}
