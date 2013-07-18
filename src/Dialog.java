/**
 * <b>final</b> Klasse, welche die im Spiel enthaltenen Dialog-Optionen speichert.
 * @author Mike Bechtel
 *
 */
public class Dialog 
{
	/**
	 * Standard-Info, dass der Spieler zur NPC-Interaktion E druecken muss
	 */
	public static final String [] INFO_INTERACTION = new String[] {"Druecke <E> zur Interaktion."};
	
	/**
	 * Abbruch-Option
	 */
	public static final String ABORT = "VAR_OPT_ABORT";
	/**
	 * Akzeptiert-Option
	 */
	public static final String APPROVE = "VAR_OPT_APPROVE";
	/**
	 * Keine Option wurde gewaehlt
	 */
	public static final String NO_OPT = "VAR_OPT_NONE";	
}
