import java.util.ArrayList;

/**
 * Stellt das Inventar des Spieler bereit.
 * @author Mike Bechtel
 *
 */
public class Player_Inventory 
{
	/**
	 * Speichert den Spieler, dem das Inventar gehoert
	 */
	private Player player;
	/**
	 * ArrayList in der die gesammelten Items gelagert werden
	 */
	private ArrayList<Collectable> items;
	
	/**
	 * Konstruktor eines Inventars
	 * @param player - Spieler, dem das Inventar gehoert
	 */
	public Player_Inventory(Player player)
	{
		this.player = player;
		items = new ArrayList<Collectable>();
	}
	
	/**
	 * Gibt zurueck, ob das Inventar voll ist (mehr als 14 Objekte)
	 * @return <b>true</b> wenn mehr als 14 Items im Inventar sind, <b>false</b> sonst
	 */
	public boolean isFull()
	{
		return items.size() > 14;
	}
	
	/**
	 * Prueft, ob ein weiteres Item in das Inventar passt (Ist das Item bereits im Inventar
	 * und stapelbar, so wird gestapelt)
	 * @param item - Item, das hinzugefuegt werden soll
	 * @return <b>true</b> wenn das Item noch passt, <b>false</b> sonst
	 */
	public boolean canAddItem(Collectable item)
	{
		if(items.size() < 15 || items.contains(item))
			return true;
		else
			return false;
	}
	
	/**
	 * Prueft, ob das Inventar ein bestimmtes Item enthaelt. Wenn ja, wird die Position in der
	 * ArrayList zurueckgegeben
	 * @param item - Zu pruefendes Item
	 * @return Index-Position des Items in der ArrayList
	 */
	public int containsItem(Collectable item)
	{
		int contains = -1;
		
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i).type() == item.type())
			{
				contains = i;
				break;
			}
		}
		
		return contains;
	}
	
	/**
	 * Fuegt ein Item in das Inventar ein
	 * @param item - Einzufuegendes Item
	 */
	public void addItem(Collectable item)
	{
		int pos = this.containsItem(item);
		
		if(pos != -1)
			items.get(pos).increaseCount(item.getCount());
		else
			items.add(item);
	}
	
	/**
	 * Gibt das Item an der Index-Position zurueck
	 * @param pos - Index-Position
	 * @return Item an der Index-Position <i>pos</i>
	 */
	public Collectable getItemAt(int pos)
	{
		return items.get(pos);
	}
	
	/**
	 * Entfernt genau ein Item aus der ArrayList. Wenn das Item als Stapel in der Liste liegt,
	 * so wird des Stapel um eins erniedrigt. Ist der Stapel danach leer, so wird er aus
	 * der Liste entfernt.
	 * @param item - Zu entfernendes Item
	 */
	public void removeOneItem(Collectable item)
	{
		int pos = this.containsItem(item);
		int count = items.get(pos).getCount();
		
		if(count > 1)
			items.get(pos).decreaseCount(1);
		else
			items.remove(pos);
	}
	
	/**
	 * Entfernt ein Item komplett aus der ArrayList (auch wenn stapelbar)
	 * @param pos - Index-Position des zu entfernenden Items
	 * @return <b>true</b> wenn das Item geloescht werden konnte, <b>false</b> sonst
	 */
	public boolean removeItemAt(int pos)
	{
		if(items.get(pos) != null)
		{
			items.remove(pos);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Gibt die Groesse des Inventars zurueck
	 * @return Groesse des Inventars
	 */
	public int size()
	{
		return items.size();
	}
	
	/**
	 * Gibt einen String zurueck, dass das Item aufgenommen wurde
	 * @param item - Aufgenommenes Item
	 * @return String-Nachricht, dass das Item aufgenommen wurde
	 */
	public String[] itemPicked(Collectable item)
	{
		return new String[]{player.getPlayerName().toUpperCase() + " findet " + item.toString().toUpperCase() + ".",
							" ", " ", "                                      weiter mit [e]"};
	}	

	/**
	 * Gibt einen String zurueck, dass das Item nicht aufgenommen wurde
	 * @param item - Nicht aufgenommenes Item
	 * @return String-Nachricht, dass das Item nicht aufgenommen wurde
	 */
	public String[] itemNotPicked(Collectable item)
	{
		return new String[]{"INVENTAR ist voll.",
							" ", " ", "                                      weiter mit [e]"};
	}
}
