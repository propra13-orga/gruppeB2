import java.applet.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Manager_Sound 
{
	private File file;
	private ArrayList<AudioClip> sounds;
	
	public Manager_Sound()
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
			file = new File("music/sounds/magic/magical_arrow.wav");
			sounds.add(2, Applet.newAudioClip(file.toURL()));
			file = new File("music/sounds/magic/magical_armor.wav");
			sounds.add(3, Applet.newAudioClip(file.toURL()));
			file = new File("music/sounds/attacks/sword_stroke.wav");
			sounds.add(4, Applet.newAudioClip(file.toURL()));
			file = new File("music/sounds/attacks/shield_block.wav");
			sounds.add(5, Applet.newAudioClip(file.toURL()));
			file = new File("music/intro.wav");
			sounds.add(6, Applet.newAudioClip(file.toURL()));
			file = new File("music/sounds/battle/victory.wav");
			sounds.add(7, Applet.newAudioClip(file.toURL()));
			
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
	public void stopSound(int i)
	{
		AudioClip play = sounds.get(i);
		play.stop();
	}
}
