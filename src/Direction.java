/**
 * <b>final</b> Klasse, welche die im Spiel Kollisions-Variablen speichert.
 *
 *@author Mike Bechtel
 */
public class Direction 
{
	/**
	 * Richtungsangabe Links
	 */
	public static final String LEFT = "left";
	/**
	 * Richtungsangabe Rechts
	 */
	public static final String RIGHT = "right";
	/**
	 * Richtungsangabe Hoch
	 */
	public static final String UP = "up";
	/**
	 * Richtungsangabe Runter
	 */
	public static final String DOWN = "down";

	/**
	 * Kollisionsangabe Keine Kollision aufgetreten
	 */
	public static final int NO_COLLISION = 0;
	/**
	 * Kollisionsangabe Kollision Oben
	 */
	public static final int COLLIDE_UP = 1;
	/**
	 * Kollisionsangabe Kollision Rechts
	 */
	public static final int COLLIDE_RIGHT = 2;
	/**
	 * Kollisionsangabe Kollision Unten
	 */
	public static final int COLLIDE_DOWN = 3;
	/**
	 * Kollisionsangabe Kollision Links
	 */
	public static final int COLLIDE_LEFT = 4;
	

	/**
	 * Kollisionsangabe Kollision Tuer
	 */
	public static final int COLLIDE_DOOR = 5;
	/**
	 * Kollisionsangabe Kollision Treppe
	 */
	public static final int COLLIDE_BACK = 6;
	
}
