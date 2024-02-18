package viewmodel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author fauzan
 * 
 */

public class Sound {
    public static Clip playSound(Clip clip, String filename, Boolean looping){     
        // constructor
        try {
            // mengambil audio input
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("assets/audios/" + filename).getAbsoluteFile());
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start(); // mulai audio
            if (looping){
                clip.loop(Clip.LOOP_CONTINUOUSLY); // loop audio
            }
            
        } catch (UnsupportedAudioFileException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (LineUnavailableException e) {
           e.printStackTrace();
        }
        return clip;
    }
    
    public static void stopSound(Clip clip){
        // untuk menyetop musik
        // System.out.println("Stopping");
        clip.stop();
    }
}
