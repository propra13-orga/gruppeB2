
public class MapIntroduction 
{
	String text1, text2, text3;
	
	public MapIntroduction()
	{
		
	}
	
	public void setText(int i)
	{
		switch(i)
		{
		case(0): 	text1 = "Dies ist ein Step by Step Mapeditor.";
					text2 = "Es gitl: Erst denken dann druecken.";
					text3 = "Weiter mit <LEERTASTE>";
					break;
				 
		case(1): 	text1 = "Mit rechter und linker <RICHTUNGSTASTEN>";
					text2 = "wird das Feld vergroessern bzw verkleinern";
					text3 = "Mit <F> festgesetzt";
					break;
					
		case(2): 	text1 = "Nun ist die Groesse festgelegt und es kann";
					text2 = "jetzt mit den <RICHTUNGSTASTEN> der Cursor bewegt werde";
					text3 = "weiter mit <LEERTASTE>";
					break;
					
		case(3):	text1 = "Als Erstes benötigen wir einen Ausgang";
					text2 = "Der wird entlang der Mauer mit <E> gesetzt";
					text3 = "Witzig bist du nicht, wenn du es an den Ecken versuchst";
		
		}
	}
	
	public String getText1() {
		return text1;
	}

	public String getText2() {
		return text2;
	}

	public String getText3() {
		return text3;
	}
	
}
