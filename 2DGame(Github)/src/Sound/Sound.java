package Sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

//\res\sound\coin.wav

// \res\audio\coin.wav

public class Sound {
	
	Clip clip; //store the sound file
	URL soundURL[] = new URL[5]; //stores the location of the sound file
	
	public Sound() {
		soundURL[0] = getClass().getResource("coin.wav");
		soundURL[1] = getClass().getResource("menu.wav");
		soundURL[2] = getClass().getResource("Nintendub.wav");
	}
	
	public void loadSound(int soundIndex) {
	    try {
	        System.out.println("Loading sound from: " + soundURL[soundIndex]);
	        AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[soundIndex]);
	        clip = AudioSystem.getClip();
	        clip.open(audio);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error loading sound clip");
	    }
	}

	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {

		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop() {
		
		clip.stop();
		
	}
	
	
	
	
	
	

}
