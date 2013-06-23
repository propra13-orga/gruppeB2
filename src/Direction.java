/**
 *  <i>Direction</i>. Diese Klasse speichert eigene statische Variablen, um
 *  in den anderen Klassen schneller auf spezielle Werte zugreifen zu koennen. 
 */

public class Direction 
{
	//Variablen zur "schnelleren" Kollisionsabfrage
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String UP = "up";
	public static final String DOWN = "down";

	public static final int NO_COLLISION = 0;
	public static final int COLLIDE_UP = 1;
	public static final int COLLIDE_RIGHT = 2;
	public static final int COLLIDE_DOWN = 3;
	public static final int COLLIDE_LEFT = 4;
	
	public static final int COLLIDE_DOOR = 5;
	public static final int COLLIDE_BACK = 6;
	
}
