package Main;

import javax.swing.JFrame;

public class Main {
	
	// tutorial videos --> https://www.youtube.com/watch?v=VpH33Uw-_0E&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=2
	
	//public static JFrame window; // static --> only one type?
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setResizable(false);
		window.setTitle("Portal Quest");
		//window.setUndecorated(true); // No Top Bar
		
		// Make game auto detect resolution of screen, and fills up the screen. 
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		gamePanel.config.loadConfig();
		
		
		window.pack(); // Causes this window to be sized to perfectly fit the preferred size of its subcomponents (ie, gamePanel)			
		
		window.setLocationRelativeTo(null); // window will be displayed at the center of the screen
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
		
		// Stopping player camera at edge of map tutorial --> do later, and look in comments in that video
		// to apply fixes to NPCs and other issues with stopping the camera at the edge
		// Shooting system --> Rarely, a shot will travel at an increased pace for no reason
		
		
		
	}

}
