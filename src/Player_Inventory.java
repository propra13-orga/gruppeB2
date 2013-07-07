import java.util.ArrayList;


public class Player_Inventory 
{
	private Player player;
	private ArrayList<Collectable> items;
	
	public Player_Inventory(Player player)
	{
		this.player = player;
		items = new ArrayList<Collectable>();
	}
	
	public boolean canAddItem(Collectable item)
	{
		if(items.size() < 15 || items.contains(item))
			return true;
		else
			return false;
	}
	
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
	
	public void addItem(Collectable item)
	{
		int pos = this.containsItem(item);
		
		if(pos != -1)
			items.get(pos).increaseCount(item.getCount());
		else
			items.add(item);
	}
	
	public Collectable getItemAt(int pos)
	{
		return items.get(pos);
	}
	
	public void removeOneItem(Collectable item)
	{
		int pos = this.containsItem(item);
		int count = items.get(pos).getCount();
		
		if(count > 1)
			items.get(pos).decreaseCount(1);
		else
			items.remove(pos);
	}
	
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
	
	public int size()
	{
		return items.size();
	}
	
	public String[] itemPicked(Collectable item)
	{
		return new String[]{player.getPlayerName().toUpperCase() + " findet " + item.toString().toUpperCase() + ".",
							" ", " ", "                                      weiter mit [e]"};
	}	
	
	public String[] itemNotPicked(Collectable item)
	{
		return new String[]{"INVENTAR ist voll.",
							" ", " ", "                                      weiter mit [e]"};
	}
}
