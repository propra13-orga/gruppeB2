import java.applet.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Der Sound-Manager laedt die versch. Sounddateien und spielt diese bei Bedarf ab bzw.
 * beendet das Abspielen
 * @author Mike Bechtel
 *
 */
public class Manager_Sound 
{
	/**
	 * File fuer die jeweilige Sound-Datei
	 */
	private File file;
	/**
	 * ArrayList aus AudioClips, in dem alle Sounds gespeichert werden
	 */
	private ArrayList<AudioClip> sounds;
	
	/**
	 * Konstruktor des Managers. Bei der Konstruktion des Managers werden direkt
	 * die Sounds geladen
	 */
	public Manager_Sound()
	{
		sounds = new ArrayList<AudioClip>();
		this.loadSounds();
	}
	
	/**
	 * Liest die Sound-Dateien ein
	 * @throws MalformedURLException Wenn die Sound-Datei nicht gefunden wird.
	 */
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
			file = new File("music/sounds/magic/duckface.wav");
			sounds.add(8, Applet.newAudioClip(file.toURL()));
			file = new File("music/sounds/battle/battle_02.wav");
			sounds.add(9, Applet.newAudioClip(file.toURL()));
			
		} catch (MalformedURLException e) 
		{
			JOptionPane.showMessageDialog(null, "Es trat ein Fehler beim Laden der Sounds auf! (MalformesURLException)");
		}
	}
	
	/**
	 * Spiel einen Sound aus der ArrayList ab
	 * @param i - Spiel den Sound an der Position <b>i</b> in der ArrayList ab
	 */
	public void playSound(int i)
	{
		AudioClip play = sounds.get(i);
		play.play();
	}
	/**
	 * Stoppt wenn moeglich einen Sound aus der ArrayList
	 * @param i - Stoppt den Sound an der Position <b>i</b> in der ArrayList
	 */
	public void stopSound(int i)
	{
		AudioClip play = sounds.get(i);
		play.stop();
	}
}
