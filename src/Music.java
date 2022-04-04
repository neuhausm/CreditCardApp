import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Music {
//just making one static method don't want to make an object for it 
	public static void Sound() throws  UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		AudioInputStream audioInputStream =
				AudioSystem.getAudioInputStream(new File("src/Sound.wav")); 
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		return;
	}
}

