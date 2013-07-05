import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MapEditor
{
	private final int MAX_SIZE = 15;
	private final int MIN_SIZE = 10;
	
	private int fieldSize;
	
	MapEditCursor cursor = new MapEditCursor(0, 0);
	
	private int posX, posY;
	
	//Hilfsvariablen zum zwischenspeichern
	int x=0,y=0;
	int oldX=0, oldY=0;

	boolean showCursor, isFieldSet, isExitSet, isPlayerSet;
	
	Block[][] field;
	
	//zum abspeichern in einer Datei
	FileWriter file;
	PrintWriter print;
	
	public MapEditor()
	{
		fieldSize = MIN_SIZE;
		
		field = new Block[MAX_SIZE][MAX_SIZE];
		
		showCursor = false;
		isFieldSet = false;
		isExitSet = false;
		isPlayerSet = false;
		
		start();
		run();
	}
	
	/*
	 * stellt das Feld dar mir Objekten Wall und Floor
	 * Cursor wird gesetzt 
	 */
	public void start()
	{
		StdDraw.setCanvasSize(40 * fieldSize, 40 * (fieldSize + 2));
		StdDraw.setXscale(0, 40 * fieldSize);
		StdDraw.setYscale(0, 40 * (fieldSize + 2));
	
		for(int i=0;i<fieldSize;i++)
			for(int j=0;j<fieldSize;j++)
			{
				int posX = j * 40 + 40/2;
				int posY = (40 * (fieldSize + 2))-(i * 40 + 40/2);
				
				if(i<1 || i>=fieldSize-1 || j<1 || j>=fieldSize-1)
				{
					field[i][j] = new Wall(posX, posY, 40, 40);
					if(i==0 && j==0)
						cursor.setPos(posX, posY);
				}
				else
					field[i][j] = new Floor(posX, posY, 40, 40);
			}
	}
	
	/*
	 * Feldgroesse wird veraendert nur quadratische Spielfelder moeglich 
	 * und Groessenwahl begrenzt von 10mal10 bis 15mal15
	 */
	public void changeFieldSize(int act)
	{
		if(fieldSize + act >= MIN_SIZE && fieldSize +act <= MAX_SIZE && isFieldSet==false)
		{	
			fieldSize = fieldSize + act;  
				
			StdDraw.setCanvasSize(40 * fieldSize, 40 * (fieldSize + 2));
			StdDraw.setXscale(0, 40 * fieldSize);
			StdDraw.setYscale(0, 40 * (fieldSize + 2));
		
			for(int i=0;i<fieldSize;i++)
				for(int j=0;j<fieldSize;j++)
				{
					int posX = j * 40 + 40/2;
					int posY = (40 * (fieldSize + 2))-(i * 40 + 40/2);
					
					if(i<1 || i>=fieldSize-1 || j<1 || j>=fieldSize-1)
						{
							field[i][j] = new Wall(posX, posY, 40, 40);
							if(i==0 && j==0)
								cursor.setPos(posX, posY);
						}
					else
						field[i][j] = new Floor(posX, posY, 40, 40);
				}
			
			}
	}
	
	/*
	 * Tastatureingaben
	 */
	public void keyCommand()
	{
		//Feldgroesse wird festgelegt
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_F) && !isFieldSet)
			isFieldSet = true;
		
		/*
		 * Solange die Feldgroesse nicht festgelegt ist, kann diese vergroessert werden,
		 * andernfalls dient die Richtungstate zur Navigation des Cursors 
		 */
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_RIGHT))
		{
			if(!isFieldSet)
				changeFieldSize(1);
			else if(isFieldSet)
				if(cursor.getPosX()<(fieldSize-1)*40)
					cursor.moveX(40);
		}
		
		/*
		 * Solange die Feldgroesse nicht festgelegt ist, kann diese vergroessert werden,
		 * andernfalls dient die Richtungstate zur Navigation des Cursors 
		 */
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_LEFT))
		{
			if(!isFieldSet)
				changeFieldSize(-1);
			else if(isFieldSet)
				if(cursor.getPosX()>40)
					cursor.moveX(-40);
		}
		
		//Navigation des Cursors
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP) && isFieldSet)
			if(cursor.getPosY()<(fieldSize*40)+60)
				cursor.moveY(40);
		//Navigation des Cursors
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN) && isFieldSet)
			if(cursor.getPosY()>100)
				cursor.moveY(-40);
		
		//setzt den Ausgang
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_E))
			setExit();
		
		//setzt die Mauer innerhalb des begehbaren Feldes
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_W))
			setWall();
					
		//speichern
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_S))
			saveField();
	}
	
	//Ausgang setzen
	public void setExit()
	{	
		//die Array eintraege der aktuellen Position des Cursors
		int j = ((int)cursor.getPosX() - 40/2)/40;
		int i = (-((int) cursor.getPosY()) + (40 * (fieldSize + 2)) - 40/2)/40;
				
//		if(field[i][j].toString().equalsIgnoreCase("wall") && i!=j && !(i<1 && j==fieldSize-1) && !(j<1 && i==fieldSize-1))
		if((i<1 || i>=fieldSize-1 || j<1 || j>=fieldSize-1) && i!=j && !(i<1 && j==fieldSize-1) && !(j<1 && i==fieldSize-1))
		{	
			//Ausgang wird gesetzt und daten gespeichert			
			if(!isExitSet)	
			{
				int posX = j * 40 + 40/2;
				int posY = (40 * (fieldSize + 2))-(i * 40 + 40/2);
		
				field[i][j] = new Door(posX, posY, 40, 40);
				
				isExitSet = true;
				
				x=i;
				y=j;
				oldX = posX;
				oldY = posY;
			}
			//Ausgang wird versetzt, alter Ausgang wird mit einem Objekt Wall ersetzt
			else if(isExitSet)	
			{
				int posX = j * 40 + 40/2;
				int posY = (40 * (fieldSize + 2))-(i * 40 + 40/2);
		
				field[i][j] = new Door(posX, posY, 40, 40);
				
				field[x][y]  = new Wall(oldX, oldY, 40, 40);
				
				x=i;
				y=j;
				oldX = posX;
				oldY = posY;
			}
			
					
		}	
	}
	
	
	public void setWall()
	{
		
		//die Array eintraege der aktuellen Position des Cursors
		int j = ((int)cursor.getPosX() - 40/2)/40;
		int i = (-((int) cursor.getPosY()) + (40 * (fieldSize + 2)) - 40/2)/40;
		
		if(field[i][j].toString().equalsIgnoreCase("floor") && !(field[i][j].toString().equalsIgnoreCase("door")) && i!=0 && j!=0)
		{
			int posX = j * 40 + 40/2;
			int posY = (40 * (fieldSize + 2))-(i * 40 + 40/2);
	
			field[i][j] = new Wall(posX, posY, 40, 40);
		}
		else if(field[i][j].toString().equalsIgnoreCase("wall") && !(field[i][j].toString().equalsIgnoreCase("door")) && i!=0 && j!=0)
		{
			int posX = j * 40 + 40/2;
			int posY = (40 * (fieldSize + 2))-(i * 40 + 40/2);
			
			field[i][j] = new Floor(posX, posY, 40, 40);
		}
	}
	
	//zeichnet die aktuelle Karte
	public void drawMap()
	{
		for(int i=0;i<fieldSize;i++)
			for(int j=0;j<fieldSize;j++)
			{
				if(field[i][j].toString().equalsIgnoreCase("floor") || field[i][j].toString().equalsIgnoreCase("door") || field[i][j].toString().equalsIgnoreCase("wall"))
					field[i][j].drawImg();
			}
		cursor.drawImg();
	}
	
	public void saveField()
	{	
		try
		{
			
			file = new FileWriter("saveMap");
			print = new PrintWriter(file);
			String info;
		   
		   for(int i=0;i<fieldSize;i++)
		   {
			   info = "";
			   for(int j=0;j<fieldSize;j++)
		       {
				   if(field[i][j].toString().equalsIgnoreCase("wall"))
					   info = info+'#';
				   else if(field[i][j].toString().equalsIgnoreCase("door"))
					   info = info+'E';
				   else if(field[i][j].toString().equalsIgnoreCase("floor"))
					   info = info+' ';
					   
			   }
			   print.println(info);
		   }
		   //specihert den String(infos) in die Datei
//		   file.write(info); 
		   

		}
		catch(IOException ex)
		{
		   System.out.println("Datei konnte nicht gespeichert werden!");
		}
		
		finally
		{
			//prueft, ob die Datei leer ist
		   if (file != null)
		      try
		      {
		        file.close();
		      }
		      catch(Exception ex)
		      {
		    	  System.out.println("Beim speichern, Datei konnte nicht geschlossen werden!");
		      }
		}
	}
	
	public void run()
	{
		while(true)
		{
			StdDraw.show(3);
			{
				StdDraw.clear(StdDraw.BLACK);
				
				drawMap();
				
				keyCommand();
			}
		}
		
	}

}
