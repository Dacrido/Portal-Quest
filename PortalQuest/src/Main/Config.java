package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	
	GamePanel gp;
	
	public Config(GamePanel gp) {
		
		this.gp = gp;
		
	}
	
	public void saveConfig() {
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"));
			
			// Save Music
			bw.write(String.valueOf(gp.music.volumeScale));
			bw.newLine();
			
			// Save Sound Effects
			bw.write(String.valueOf(gp.soundEffect.volumeScale));			
			bw.newLine();
			
			// Messages On/Off
			System.out.println(gp.ui.onOffSwitch);
			bw.write(String.valueOf(gp.ui.onOffSwitch));
			bw.newLine();
			
			bw.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void loadConfig() {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("Config.txt"));
			
			String s = br.readLine();
			
			// Music
			gp.music.volumeScale = Integer.parseInt(s);
			
			
			// Sound Effects
			s = br.readLine();
			gp.soundEffect.volumeScale = Integer.parseInt(s);
			
			// Messages On/Off
			s = br.readLine();
			if (s.equals("true")) {
				gp.ui.onOffSwitch = true;
			} else if (s.equals("false")) {
				gp.ui.onOffSwitch = false;
			}
			
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}
