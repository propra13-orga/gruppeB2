/**
 * <b>final</b> Klasse, welche die im Spiel enthaltenen Attacken und Magie speichert.
 * @author Mike Bechtel
 *
 */
public final class Attacks 
{
	/**
	 * Schwerthieb-Attacke des Players
	 */
	public static Attack schwert_hieb = new Attack("Schwerthieb", Types.normal, true, 4);
	/**
	 * Schild-Block-Attacke des Players
	 */
	public static Attack schild_block = new Attack("Schildblock", Types.normal, "vert.", false, 5, "duckt sich.");
	/**
	 * Blue-Jeans-Attacke des Players
	 */
	public static Attack blue_jeans = new Attack("Blue Jeans", Types.trueschool, true, 5);
	
	/**
	 * Magie-Pfeil-Magie des Players
	 */
	public static Magic magie_pfeil = new Magic("Magiepfeil", Types.magic, true, 15, 2);
	/**
	 * Magie-Ruestung-Magie des Players
	 */
	public static Magic magie_ruestung = new Magic("Magieruestung", Types.magic, false, 20, 3, "vert.");
	/**
	 * Doener-Magie des Players
	 */
	public static Magic doener = new Magic("Doener", Types.fire, true, 30, 3);
	
	/**
	 * Gibt zurueck, welche Attacke neu erlernt werden kann
	 * @param level - Level, um zugehoerige Attacke zu finden
	 * @return Attacke, die auf dem Level erlernt werden kann
	 */
	public static Attack learnAtt(int level)
	{
		Attack att = null;
		
		switch(level)
		{
		case 3: 
			att = blue_jeans;
			att.setStrength(32);
			break;
		
		default: return null;
		}
		
		return att;
	}	
	
	/**
	 * Gibt zurueck, welcher Zauber neu erlernt werden kann
	 * @param level - Level, um zugehoerigen Zauber zu finden
	 * @return Zauber, der auf dem Level erlernt werden kann
	 */
	public static Magic learnMag(int level)
	{
		Magic att = null;
		
		switch(level)
		{
		case 5: 
			att = doener;
			att.setStrength(55);
			break;
		
		default: return null;
		}
		
		return att;
	}
	
	//------------EnemyAttacks----------------------------------------------------------------------------------
	
	
	//------------KidHipster----------------------------------------------------------------------------------	
	/**
	 * Smartphone-Attacke des KidHipster Gegners
	 */
	public static Attack kidhipster_smartphone = new Attack("Smartphone", Types.cool, true, 5);	
	/**
	 * Metrobarriere-Attacke des KidHipster Gegners
	 */
	public static Attack kidhipster_metrobarriere = new Attack("Metrobarriere", Types.normal, "vert.", false, 3, "wird metro.");
	/**
	 * Obay-Magie des KidHipster Gegners
	 */
	public static Magic kidhipster_obey = new Magic("Obey", Types.swag, true, 15, 2);	
	
	//------------AttentionWhore----------------------------------------------------------------------------------	
	/**
	 * Geschrei-Attacke der AttentionWhore
	 */
	public static Attack attention_geschrei = new Attack("Geschrei",Types.normal, "angr.", false, 2, "ist in Rage.");	
	/**
	 * Frauenlogik-Attacke der AttentionWhore
	 */
	public static Attack attention_logic = new Attack("Frauenlogik", Types.whore, true, 3);
}
