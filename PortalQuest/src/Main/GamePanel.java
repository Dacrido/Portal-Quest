package Main;

import java.awt.Color;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import AI.PathFinder;
import entity.Entity;
import entity.Player;

import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// Game Settings
	final int originalTileSize = 16; // 16 x 16 tile
	// Must scale as 16 x 16 player in a 1920 x 1080 screen is very small. So the
	// player must be scaled to support higher resolutions
	final int scale = 3; // originally 3

	public final int tileSize = originalTileSize * scale; // 48 x 48 tile
	public int maxScreenCol = 16; // 16 tiles horizontal for game screenv
	public int maxScreenRow = 12; // 12 tiles vertical for game screen --> originally: 16 x 12 tiles

	public int screenWidth = tileSize * maxScreenCol; 
	public int screenHeight = tileSize * maxScreenRow; 

	// WOLRD SETTINGS
	public int maxWorldCol = 100; // changes depending on map
	public int maxWorldRow = 100;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// FPS --> number of times the screen updates per second
	// In order for an actual 2D game, we need time. A game clock with start, stop,
	// which will enable the use of FPS and other mechanics.

	// FPS

	// long currentTime = System.nanoTime(); Returns the current value of the
	// running program high resolution time source in nanoseconds
	// long currentTime2 = System.currentTimeMillis(); Same as above, but returns
	// time in milliseconds, not nanoseconds. Less precise.

	int FPS = 60;
	
	
//	// FULL SCREEN
//	int screenWidth2 = screenWidth;
//	int screenHeight2 = screenHeight;
//	BufferedImage tempScreen;
//	Graphics2D g2;
//	Graphics g;
	
	
	// Maps
	
	public TileManager tileM = new TileManager(this);
	public keyHandler keyH = new keyHandler(this);
	
	public final int maxMap = 10;
	public int currentMap = 0;
	
	// User Interface
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	
	// Save Files
	Config config = new Config(this);
	
	// PathFinding
	public PathFinder pFinder = new PathFinder(this);
	
	Thread gameThread; // With a thread started, it keeps a program running. Thread is the tool for
						// dealing with 'time' in any program. When closed, the program 'stops'

	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);

	// SOUND
	Sound music = new Sound();
	Sound soundEffect = new Sound();

	// Entity
	public Player player = new Player(this, keyH);

	// Objects
	public Entity object[][] = new Entity[maxMap][40]; // 10 object slots --> only 10 objects can be displayed at a
														// time. Increase to display more objects. BUT, the map can have more than 10 objects
	public Entity npc[][] = new Entity[maxMap][10];
	public Entity monster[][] = new Entity[maxMap][40];
	// RENDER ORDER
	ArrayList<Entity> entityList = new ArrayList<>(); // list of player, npc and objects
	// entity with lowest worldY comes at index 0, as it will be rendered first (remember, smallY means above)
	// this way, entities and objects can overlap correctly in those specific situations
	
	// Projectiles
	public Entity projectile[][] = new Entity[maxMap][20]; // array as collision checkers are based on arrays
//	public ArrayList<Entity> projectileList = new ArrayList<>();
	
	// Game State
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int gameWinState = 7;
	
	
	
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true); // When true, all drawing from this computer will be done in an offscreen
										// painting buffer; improves a game's rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true); // The game panel can be 'focused' to receive key input.

	}

	public void setupGame() {

		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();

		playMusic(13);
		
		
		
//		playMusic(0); // want to play game music, in this case, index 0
		gameState = titleState; // does not need playState, but easier to read/understand
		
		// Full screen
		//tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB); 
		// A blank buffered image as large as game window width and height
		//g2 = (Graphics2D)tempScreen.getGraphics(); // anything g2 draws will be recorded in tempScreen
		// attached g2 to tempScreen
		
		
		// Full screen:
		// we draw tempScreen first of all, then draw tempScreen to the jPanel
        //setFullScreen();
	}
	
	public void retry() {
		
		player.setDefaultPositions();
		player.restoreLife();
		aSetter.setNPC();
		aSetter.setMonster();	
		
		for (int i = 0; i < projectile[1].length; i++) {
			if (projectile[currentMap][i] != null) {
				projectile[currentMap][i] = null;
			}
		}
		
		for (int i = 20; i < monster[1].length; i++) {
			if (monster[currentMap][i] != null) {
				monster[currentMap][i] = null;
			}
		}
		
		
	}
	
	public void restart() {
		
		player.setDefaultValues();
		player.setDefaultPositions();
		player.restoreLife();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		
		
		for (int i = 0; i < projectile[1].length; i++) {
			if (projectile[currentMap][i] != null) {
				projectile[currentMap][i] = null;
			}
		}
		
		for (int i = 20; i < monster[1].length; i++) {
			if (monster[currentMap][i] != null) {
				monster[currentMap][i] = null;
			}
		}
		
		
		
	}
	
//	public void setFullScreen() {
//		
//		// GET LOCAL SCREEN DEVICE SIZE
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice gd = ge.getDefaultScreenDevice(); // LOCAL SCREEN DEVICE INFO
//		gd.setFullScreenWindow(Main.window); //sets full screen of the JFrame
//		// Get Full Screen Width and Height
//		screenWidth2 = Main.window.getWidth();
//		screenHeight2 = Main.window.getHeight();	
//	
//	}

	public void startGameThread() {
		gameThread = new Thread(this); // this means this class or game panel
		gameThread.start(); // calls the run method

	}

	// Run method is where the game loop is created
	// SLEEP GAME LOOP
//	@Override
//	public void run() {
//		
//		double drawInterval = 1000000000 / FPS; // 1 billion --> 1 billion nanoseconds = 1 seconds. In this case: 0.01666666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		
//		
//		
//		while (gameThread != null) { // as long as this gameThread exists, it will keep repeating itself
//			
//			// 1 UPDATE: update information such as character positions. Remember --> 0, 0 is at top left, so down is higher y
//			update();
//			// 2 DRAW: draw the screen with the updated information
//			repaint(); // paintCompoenet is a built-in method in java, and is called differently from other methods
//			 
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime /= 1000000;
//				
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime); // First type of game loop.
//				// Pauses the game loop and doesn't do anything until sleep time is over. Uses milliseconds, so nano is converted to milli
//				
//				
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}  
//			
//		}
//		
//	}

	// DELTA / ACCUMULATOR Game loop

	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		// display FPS
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;

			timer += (currentTime - lastTime);

			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint(); // Unfortunately, full screen does not work
				delta--;
				drawCount++;
			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}

		}

	}

	// Note: Both game loops above work fine and no longer need to be edited. Either
	// can be chose. Apparently, there may
	// be a problem with Thread.sleep, so delta might be a safer option. Both are
	// good.

	public void update() {
		
		if (gameState == playState) {
			// Player
			player.update();
			
			// NPC
			for (int i = 0; i < npc[currentMap].length; i++) {
				if (npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			
			// Monster
			for (int i = 0; i < monster[currentMap].length; i++) {
				if (monster[currentMap][i] != null) {   
					if (monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					else if (monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
					
				}
			}
			
			// Projectile
			for (int i = 0; i < projectile[1].length; i++) {
				if (projectile[currentMap][i] != null) {
					if (projectile[currentMap][i].alive == true) {
						projectile[currentMap][i].update();
					}
					else if (projectile[currentMap][i].alive == false) {						
						projectile[currentMap][i] = null;
					}
					
				}
			}
			
		}
		if (gameState == pauseState) {
			
		}
		
		if (gameState == dialogueState) {
			player.dialogueUpdate();
			if (eHandler.dialogueEvent == false) {
				for (int i = 0; i < npc[currentMap].length; i++) {
					if (npc[currentMap][i] != null) {
						npc[currentMap][i].dialogueUpdate();
					} 
				}
			}
			
			for (int i = 0; i < monster[currentMap].length; i++) {
				if (monster[currentMap][i] != null) {
					if (monster[currentMap][i].dying == true && monster[currentMap][i].alive == true) {
						monster[currentMap][i].update();
					}
					else if (monster[currentMap][i].dying == true && monster[currentMap][i].alive == false) {
						monster[currentMap][i] = null;
					}
					
				}
			}
			
			
			
		}
		
		if (gameState == characterState) {
			player.dialogueUpdate();
			for (int i = 0; i < monster[currentMap].length; i++) {
				if (monster[currentMap][i] != null) {
					if (monster[currentMap][i].dying == true) {
						monster[currentMap][i].update();
					}
					else if (monster[currentMap][i].alive == false) {
						monster[currentMap][i] = null;
					}
					
				}
			}
		}


	}
	
	
	
//	public void paintComponent(Graphics g) { // paintComponent is a built-in method in java --> a standard method to
//												// draw things on a JPanel
//												// Graphics is a class that has multiple functions to draw objects on a
//												// screen
//
//		super.paintComponent(g); // super means the parent class of this class, which is JPanel as gamePanel is a
//									// subclass of JPanel
//		
//		// DIFFERENT G2 from tempSCREEN
//		Graphics2D g2 = (Graphics2D) g; // changing g to g2, as it provides more functions
//		// Graphics2D class extends the Graphics class to provide more sophisticated
//		// control over geometry, coordinate transformations, color management and text
//		// layout.
//		
//		
//		
//		
//		g2.dispose(); // dispose of this graphics context and release any system resources that it is
//						// using.
//
//	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// DEBUG / OPTIMIZATION
		long drawStart = 0;
		if (keyH.showDebugText == true) {
			drawStart = System.nanoTime();
		}
				
		// Title Screen
					
		if (gameState == titleState) {
			ui.draw(g2);
		}
		// Others
		else {				
			// Tile
			tileM.draw(g2); // tiles must be drawn before the player, else, the tiles will hide the player
			//// ADDING ALL ENTITIES TO THE LIST
			// Player
			entityList.add(player);
			// NPC
			for (int i = 0; i < npc[currentMap].length; i++) {
						
				if (npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}			
			}
			// Object
					
			for (int i = 0; i < object[currentMap].length; i++) {
				if (object[currentMap][i] != null) { // Without checking for null, we have nullPointer error
					entityList.add(object[currentMap][i]);									
				}
			}
							
			// Monster
			for (int i = 0; i < monster[currentMap].length; i++) {
				if (monster[currentMap][i] != null) { // Without checking for null, we have nullPointer error
					entityList.add(monster[currentMap][i]);									
				}
			}
							
			// Projectile
			for (int i = 0; i < projectile[1].length; i++) {
				if (projectile[currentMap][i] != null) { // Without checking for null, we have nullPointer error
					entityList.add(projectile[currentMap][i]);									
				}
			}
							
							
			// SORT
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				} // sorts Entity list based on world Y values, with smaller ones being up the screen
							
			});
							
			// Draw Entities
			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			// Empty Entity List
			entityList.clear();
							
							

			ui.draw(g2);
		}


						
		if (keyH.showDebugText == true && gameState == playState) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			int x = 10;
			int y = 400;
			int lineHeight = 20;
			g2.drawString("WorldX: " + player.worldX, x, y);
			y+= lineHeight;
			g2.drawString("WorldY: " + player.worldY, x, y);
			y+= lineHeight;
			g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y);
			y+= lineHeight;
			g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y);
			y+= lineHeight;
			g2.drawString("Draw Time: "  + passed, x, y);
			}
				
		g2.dispose();
	}

	public void playMusic(int i) {

		music.setFile(i);
		music.play();
		music.loop();

	}

	public void stopMusic() {
		music.stop();
	}

	public void playSoundEffect(int i) {

		soundEffect.setFile(i);
		soundEffect.play();
		// no loop as sound effects are short

	}
}
