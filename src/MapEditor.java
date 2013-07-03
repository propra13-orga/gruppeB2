import java.awt.event.KeyEvent;


public class MapEditor
{
	private final int MAX_SIZE = 15;
	private final int MIN_SIZE = 10;
	
	private int fieldSize;
	
	MapEditCursor cursor = new MapEditCursor(0, 0);
	
	private int posX, posY;
	
	boolean showCursor;
	
	Block[][] field;
	
	
	public MapEditor()
	{
		fieldSize = MIN_SIZE;
		
		field = new Block[MAX_SIZE][MAX_SIZE];
		
		showCursor = false;
		
		start();
		run();
	}
	
	public void start()
	{
//		StdDraw.setCanvasSize(600,600);
		StdDraw.setCanvasSize(40 * fieldSize, 40 * (fieldSize + 2));
		StdDraw.setXscale(0, 40 * fieldSize);
		StdDraw.setYscale(0, 40 * (fieldSize + 2));
		
//		for(int i=0;i<field.length;i++)
//			for(int j=0;j<field[0].length;j++)
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
	
	public void changeFieldSize(int act)
	{
		if(fieldSize + act >= MIN_SIZE && fieldSize +act <= MAX_SIZE)
		{	
			fieldSize = fieldSize + act;  
				
			StdDraw.setCanvasSize(40 * fieldSize, 40 * (fieldSize + 2));
			StdDraw.setXscale(0, 40 * fieldSize);
			StdDraw.setYscale(0, 40 * (fieldSize + 2));
		
			//		for(int i=0;i<field.length;i++)
			//			for(int j=0;j<field[0].length;j++)
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
	
	public void calc()
	{
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_A))
		{
			changeFieldSize(1);
			System.out.println("a");
		}
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_S))
		{
			changeFieldSize(-1);
			System.out.println("s");
		}
		
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_RIGHT))
			cursor.moveX(40);
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_LEFT))
			cursor.moveX(-40);
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_UP))
			cursor.moveY(40);
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_DOWN))
			cursor.moveY(-40);
		
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_E))
			setExit();
		
		if(StdDraw.isKeyPressedSingle(KeyEvent.VK_W))
			setWall();
	}
	
	//Ausgang setzen
	public void setExit()
	{	
		int j = ((int)cursor.getPosX() - 40/2)/40;
		
		System.out.println(j);
		
		int i = (-((int) cursor.getPosY()) + (40 * (fieldSize + 2)) - 40/2)/40;
		
		System.out.println(i);
	}
	
	public void setWall()
	{
		
	}
	
	public void drawMap()
	{
		for(int i=0;i<fieldSize;i++)
			for(int j=0;j<fieldSize;j++)
			{
				if(field[i][j].toString().equalsIgnoreCase("floor") || field[i][j].toString().equalsIgnoreCase("wall"))
					field[i][j].drawImg();
			}
		cursor.drawImg();
	}
	
	public void run()
	{
		while(true)
		{
			StdDraw.show(3);
			{
				StdDraw.clear(StdDraw.BLACK);
				
				drawMap();
				
				calc();
			}
		}
		
	}

}
