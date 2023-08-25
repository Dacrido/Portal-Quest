package Main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	
	Clip clip; // used to open audio files
	URL soundURL[] = new URL[30]; // number of sound files. Stores file paths of these sound files
	FloatControl fc; // A float that can constantly change and has methods that are useful for slider implementation	
	// Float Control accepts values from -80f to 6f
	int volumeScale = 10;
	float volume;
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/2DGameSong.wav");
		soundURL[1] = getClass().getResource("/sound/DoorOpens.wav");
		soundURL[2] = getClass().getResource("/sound/KeyCollect.wav");
		soundURL[3] = getClass().getResource("/sound/PowerUp.wav");
		soundURL[4] = getClass().getResource("/sound/WinGame.wav");
		soundURL[5] = getClass().getResource("/sound/EndGameSong.wav");
		soundURL[6] = getClass().getResource("/sound/Sword.wav");
		soundURL[7] = getClass().getResource("/sound/PlayerHit.wav");
		soundURL[8] = getClass().getResource("/sound/HitMonster.wav");
		soundURL[9] = getClass().getResource("/sound/LevelUp.wav");
		soundURL[10] = getClass().getResource("/sound/CursorMovement.wav");
		soundURL[11] = getClass().getResource("/sound/PickUpItem.wav");
		soundURL[12] = getClass().getResource("/sound/Bubbles.wav");
		soundURL[13] = getClass().getResource("/sound/TitleScreenSong.wav");
		soundURL[14] = getClass().getResource("/sound/LostGameSong.wav");
		soundURL[15] = getClass().getResource("/sound/leaves.wav");
		soundURL[16] = getClass().getResource("/sound/fire.wav");
		soundURL[17] = getClass().getResource("/sound/laser.wav");
		soundURL[18] = getClass().getResource("/sound/ShamanFire.wav");
		
		// SOUND EFFECTS FOR ALL PROJECTILEs
		
	}
	// after getting the files, the next four methods, setFile, play, loop, and stop must be added to deal with all the sounds and songs
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); // class that deals with sound. We pass URL.
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); // can pass a value to a clip to change the volume
			checkVolume();
			
//			if (i != 0 && i != 5) {
//				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//					gainControl.setValue(10.0f); // Increase volume by 10 decibels.
//					
//			}
//			
//			if (i == 0) { 	
//				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-5.0f); 
//			}
//			
//			if (i == 5) { 	
//				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//				gainControl.setValue(-5.0f); 
//			}
			
			
			
		} catch(Exception e) {
			
		}
		
		
	}

	public void play() {
		
		
		clip.start();
		
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		if (clip!= null) {
			clip.stop();
		}
	}
	
	public void checkVolume() {
		
		
		switch (volumeScale) {
		// The float controls values are not to scale. -70f and -30f will barely have any difference in terms of hearing
		
		case 0: volume = - 80f; break;
		case 1: volume = - 30f;break;
		case 2: volume = - 20f;break;
		case 3: volume = - 16f;break;
		case 4: volume = - 12f;break;
		case 5: volume = - 8f;break;
		case 6: volume = - 4f;break;
		case 7: volume = 0f;break;
		case 8: volume = 3f;break;
		case 9: volume = 6f;break;		
		
		}
		fc.setValue(volume);
		
	}
	
}
