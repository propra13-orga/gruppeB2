import java.applet.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class SoundManager 
{
	private File file;
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
			file = new File("music/sounds/battle/battle_01.wav");
			sounds.add(0, Applet.newAudioClip(file.toURL()));
			file = new File("music/sounds/battle/enemy_ready.wav");
			sounds.add(1, Applet.newAudioClip(file.toURL()));
			
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
