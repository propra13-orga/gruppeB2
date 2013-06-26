import java.applet.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SoundManager 
{
	private ArrayList<AudioClip> sounds;
	
	public SoundManager()
	{
		sounds = new ArrayList<AudioClip>();
		this.loadSounds();
	}
	
	@SuppressWarnings("deprecation")
	public void loadSounds()
	{
		try 
		{
			File file = new File("music/sounds/battle/battle_01.wav");
			sounds.add(0, Applet.newAudioClip(file.toURL()));

			System.out.println(file.exists());
			
		} catch (MalformedURLException e) 
		{
			JOptionPane.showMessageDialog(null, "Es trat ein Fehler beim Laden der Sounds auf! (MalformesURLException)");
		}
	}
	
	public void playSound(int i)
	{
		AudioClip play = sounds.get(i);
		play.play();
	}
}
