
public final class Attacks 
{
	public static Attack schwert_hieb = new Attack("Schwerthieb", "Normal", true, 4);
	public static Attack schild_block = new Attack("Schildblock", "Normal", "vert.", false, 5, "duckt sich.");
	
	
	public static Magic magie_pfeil = new Magic("Magiepfeil", "Magie", true, 15, 2);
	public static Magic magie_ruestung = new Magic("Magieruestung", "Magie", false, 20, 3, "vert.");
	
	
	
	
	//------------EnemyAttacks----------------------------------------------------------------------------------
	
	
	//------------KidHipster----------------------------------------------------------------------------------	
	public static Attack kidhipster_smartphone = new Attack("Smartphone", "Normal", true, 5);	
	public static Attack kidhipster_metrobarriere = new Attack("Metrobarriere", "Normal", "vert.", false, 3, "wird metro.");
	public static Magic kidhipster_obey = new Magic("Obey", "SWAG", true, 15, 2);
}
