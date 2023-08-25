package Main;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import object.Object_Heart;
import object.Object_Key;


public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font arial_40, arial_80Bold;
//	BufferedImage keyImage;
	BufferedImage heartFull, heartHalf, heartEmpty;
	ArrayList <String> message = new ArrayList<>();
	ArrayList <Integer> messageCounter = new ArrayList<>();
	public boolean messageOn = false;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	// Cursors current position in the inventory screen
	public int slotCol = 0;
	public int slotRow = 0;
	
	
	// Options screen
	int subState = 0;
	boolean onOffSwitch;
	
	
	public UI(GamePanel gp) {
		this.gp = gp;

		arial_40 = new Font("Cambria", Font.PLAIN, 40); // way to initialize the font
		arial_80Bold = new Font("Arial", Font.BOLD, 80);
//		Object_Key key = new Object_Key(gp); 
//		keyImage = key.image;
		
		// Create HUD Object
		Entity heart = new Object_Heart(gp);
		heartFull = heart.obj_image;
		heartHalf = heart.obj_image2;
		heartEmpty = heart.obj_image3;
		
		

	}

	public void addMessage(String text) {
		
		message.add(text);
		messageCounter.add(0); // default value of messageCouner
		
		
		
		
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2; // so that we can use g2 for other methods in this class

		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		
		// Title State
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		
		
		// Play State
		if (gp.gameState == gp.playState) {
			drawPlayerLife();
			drawPlayerMana();
			drawMessage();
		}
		
		
		// Pause State
		if (gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		
		
		// Dialogue State
		if (gp.gameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
		
		// Character State
		if (gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(); 
			drawMessage();
			
			
		}
		
		// Options State
		if (gp.gameState == gp.optionsState) {
			drawOptionScreen();
		}
		
		// Game Over State
		if (gp.gameState == gp.gameOverState) {
			gameOverScreen();
		}
		
		if (gp.gameState == gp.gameWinState) {
			gameWinScreen();
		}

	}
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		
		// Draw Blank Hearts
		while (i < gp.player.maxLife/2) {
			
			g2.drawImage(heartEmpty, x, y, null);
			x += gp.tileSize;
			i++;
			
		}
		
		// Reset
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		// Draw Current Life
		
		while (i < gp.player.life) {
			g2.drawImage(heartHalf, x, y, null);
			i++;
			if (i < gp.player.life) {
				g2.drawImage(heartFull,  x,  y,  null); 
			}
			i++;
			x += gp.tileSize; 
		}
		
		
		
	}
	
	public void drawPlayerMana() {
		// Mana Bar
		
		double oneScale = (double)gp.tileSize*4/gp.player.maxMana; // divide the bar's length by the monster's max life
		double manaBarValue = oneScale * gp.player.mana; // amount of bar that should be red
		
		int x = gp.tileSize / 2;
		int y = gp.tileSize * 2; // below hearts
		
		// Outline
		g2.setColor(new Color(35, 35, 35));
		g2.fillRect(x - 1, y - 16, (int) gp.tileSize*4+2, 12);
		
		// Health Bar
		g2.setColor(new Color(0, 128, 128));
		g2.fillRect(x, y - 15, (int) manaBarValue, 10); 
		
		
	}
	
	
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 5;
		
		if (gp.gameState == gp.characterState) {
			messageX = gp.tileSize * 10 - gp.tileSize/3;
			messageY = gp.tileSize*11;
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for (int i = 0; i < message.size(); i++) {
			
			if (message.get(i) != null && message.get(i) != "") {
				
				if (gp.gameState == gp.characterState) {
					if (message.get(i) == "Already in use!") { 
						
						if (onOffSwitch == false) {
							g2.setColor(Color.BLACK);
							g2.drawString(message.get(i), messageX+2, messageY+2);
							
							g2.setColor(Color.WHITE);
							g2.drawString(message.get(i), messageX, messageY);
							
							messageY += gp.tileSize; 
						}
										
										
						int counter = messageCounter.get(i) + 1; // For every specific message, there is a counter that tells how long it lasts
						messageCounter.set(i, counter);
						
						
						if (messageCounter.get(i) > 200) {
							message.remove(i);
							messageCounter.remove(i); 
						}
					}
				} else {
					if (onOffSwitch == false) {

						g2.setColor(Color.BLACK);
						g2.drawString(message.get(i), messageX+2, messageY+2);
						
						g2.setColor(Color.WHITE);
						g2.drawString(message.get(i), messageX, messageY);	
						
						messageY += gp.tileSize; 
					}
					
									
					int counter = messageCounter.get(i) + 1; // For every specific message, there is a counter that tells how long it lasts
					
					messageCounter.set(i, counter);					
					
					if (messageCounter.get(i) > 200) {
						message.remove(i);
						messageCounter.remove(i); 
					}
				}
				
				
				
				
			}
		}
	}
	
	public void drawTitleScreen() {
		
		g2.setColor(new Color(0, 0, 0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight); //For changing background color
		
		// Title Name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
		String text = "Portal Quest"; // change to a better title later
		int x = getXCenteredText(text);
		int y = gp.tileSize * 3;
		
		// Shadow
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 4);
		
		
		
		// Main Color
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		// Character Image
		x = gp.screenWidth/2 - (gp.tileSize * 2)/2;
		y += gp.tileSize * 2;
		g2.drawImage(gp.player.stand1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
		
		// Menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		
		text = "NEW GAME";		
		x = getXCenteredText(text);
		y += gp.tileSize * 3;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text,  x,  y);
		if (commandNum  == 0) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}
		
		
		
		text = "LOAD GAME";		
		x = getXCenteredText(text);
		y += gp.tileSize * 1;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x,  y);
		if (commandNum  == 1) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}

		
		text = "QUIT";		
		x = getXCenteredText(text);
		y += gp.tileSize * 1;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text,  x,  y);
		if (commandNum  == 2) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}

		
		
	}
	
	
	public void drawPauseScreen() {
		
		
		g2.setColor(new Color(0, 0, 0, 200));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight); 
		
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXCenteredText(text);
		int y = gp.screenHeight / 2;
		
		
		// Shadow
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);		
		
		// Main Color
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		text = "BACK";		
		x = getXCenteredText(text);
		y += gp.tileSize * 2;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		if (commandNum  == 0) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		text = "OPTIONS";		
		x = getXCenteredText(text);
		y += gp.tileSize;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		if (commandNum  == 1) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}	
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		text = "SAVE AND QUIT";		
		x = getXCenteredText(text);
		y += gp.tileSize;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		if (commandNum  == 2) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		text = "MENU";		
		x = getXCenteredText(text);
		y += gp.tileSize;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		if (commandNum  == 3) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize/2 + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize/2, y);
		}
	}
	
	public void drawDialogueScreen() {
		
		// Window
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 24F)); 	
		x += gp.tileSize;
		y += gp.tileSize;
		
		for (String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
		
	}
	
	
	public void drawCharacterScreen() {
		
		
		// FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize/2;
		final int frameWidth = gp.tileSize * 6;
		final int frameHeight = gp.tileSize * 11;
		
		
		 drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		// TEXT
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(32F));
		 
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 45;
		
		// NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		g2.drawString("Wand", textX, textY);
		textY += lineHeight;
		
		// Values
		int tailX = (frameX + frameWidth) - 30; // Text cannot go after. 30 so that it isn't the edge
		
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXAlignForRight(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight/4;
		
		// DRAW IMAGES
		g2.drawImage(gp.player.currentWeapon.obj_image, tailX - gp.tileSize, textY-3, null);
		textY += gp.tileSize;
		
		g2.drawImage(gp.player.currentShield.obj_image, tailX - gp.tileSize, textY, null);		
		textY += gp.tileSize;
		if (gp.player.currentWand != null) {
			g2.drawImage(gp.player.currentWand.obj_image, tailX-gp.tileSize, textY, null);
		} else {
			textY += lineHeight/2 + 10;
			value = "None";
			textX = getXAlignForRight(value, tailX);
			g2.drawString(value, textX, textY);
		}
		
		
		
		
	}
	
	
	public void drawInventory() {
		
		// Max of 20 items at a time! 5 * 4 inventory map
		
		int frameX = gp.tileSize * 9;
		int frameY = gp.tileSize/2;
		int frameWidth = gp.tileSize * 6;
		int frameHeight = (int) (gp.tileSize * 4.8);
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// Slot
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize;
		
		// Draw Player items
		for (int i = 0; i < gp.player.inventory.size(); i++) {
			
			
			// Equip Cursor
			if (gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield ||gp.player.inventory.get(i) == gp.player.currentWand) {
				g2.setColor(new Color(240, 190, 90)); // Yellow-orange color
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(gp.player.inventory.get(i).obj_image, slotX, slotY, null);
			slotX += slotSize;
			
			if (i == 4 || i == 9 || i == 14) { // Indexes where items move on to the next line
				slotX = slotXStart;
				slotY += gp.tileSize;
			}
			
			
		}
		
		
		
		// Cursor
		int cursorX = slotXStart + (slotSize * slotCol); // The cursors position 
		int cursorY = slotYStart + (slotSize * slotRow);
		int cursorWidth = gp.tileSize;
		int cursorHeight = gp.tileSize;
		
		// Draw
		g2.setColor(Color.WHITE);
		g2.setStroke(new BasicStroke(3)); // Thickness
		g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10 );
		
		// Description frame
		int descriptionFrameX = frameX;
		int descriptionFrameY = frameY + frameHeight + 3;
		int descriptionFrameWidth = frameWidth;
		int descriptionFrameHeight = gp.tileSize * 4;
		
		
		// Draw Description Text
		int textX = descriptionFrameX + 20;
		int textY = descriptionFrameY + gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(22F));
		
		int itemIndex = getItemIndex();
		
		if (itemIndex < gp.player.inventory.size()) {
			drawSubWindow(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight);
			for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
				g2.drawString(line, textX, textY);
				textY += 32;
			}
			
			
		}		
		
	}
	
	public void drawOptionScreen() {
		
		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		// Subwindow
		int frameX = gp.tileSize * 4;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 9;
		int frameHeight = gp.tileSize * 10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		switch (subState) {
		
		case 0: optionsTop(frameX, frameY);
			break;
		case 1: controls(frameX, frameY);
			break;
			
		
		}		
		
	}
	
	public void optionsTop(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// TITLE
		String text = "OPTIONS";
		textX = getXCenteredText(text);
		textY = frameY + gp.tileSize;		
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		
		// MUSIC
		textX = frameX + gp.tileSize;
		textY += 2*gp.tileSize;
		text = "MUSIC";
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		if (commandNum  == 0) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", textX - gp.tileSize/2 + 2, textY + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", textX - gp.tileSize/2, textY);
		}
		
		// SOUND EFFECT
		textY += gp.tileSize;
		text = "S.E.";
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		if (commandNum  == 1) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", textX - gp.tileSize/2 + 2, textY + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", textX - gp.tileSize/2, textY);
		}
		
		// CONTROLS
		textY += gp.tileSize;
		text = "CONTROLS";
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		if (commandNum  == 2) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", textX - gp.tileSize/2 + 2, textY + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", textX - gp.tileSize/2, textY);
			
			if (gp.keyH.enterPressed == true) {
				gp.keyH.enterPressed = false;
				subState = 1;
				commandNum = 0;
			}
		}
		
		// DISABLE SCROLLING MESSAGE OPTION
		textY += gp.tileSize;
		text = "MESSAGES";
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		if (commandNum  == 3) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", textX - gp.tileSize/2 + 2, textY + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", textX - gp.tileSize/2, textY);
		}		
		
		// BACK
		textY += 3*gp.tileSize;
		text = "BACK";
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		if (commandNum  == 4) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", textX - gp.tileSize/2 + 2, textY + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", textX - gp.tileSize/2, textY);
			if (gp.keyH.enterPressed == true) {
				gp.keyH.enterPressed = false;
				gp.gameState = gp.pauseState;
				commandNum = 0;
			}
		}
		
		
		
		// MUSIC VOLUME
		textX = frameX + (int) (gp.tileSize * 5.5);
		textY = frameY + gp.tileSize*2 + 24;
		g2.setStroke(new BasicStroke(3)); // setting the thickness of the rectangle
		g2.drawRect(textX, textY, 140, 24); // 140/10 = 14
		int volumeWidth = 14*gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		// SOUND EFFECT
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 140, 24);
		volumeWidth = 14*gp.soundEffect.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		
		// MESSAGES ON/OFF
		textY += (int) (2.5*gp.tileSize);		
		text = "ON / OFF";
		String textOn = "ON";
		String textOff = "OFF";
		String textSlash = "ON /";
		int textWidthOn = (int)g2.getFontMetrics().getStringBounds(textOn, g2).getWidth() + 8;
		int textWidthOff = (int)g2.getFontMetrics().getStringBounds(textOff, g2).getWidth() + 8;
		int textWidthSlash = (int)g2.getFontMetrics().getStringBounds(textSlash, g2).getWidth() + 8;
		int textHeight = gp.tileSize;		
		g2.setColor(new Color(240, 190, 90));
		if (onOffSwitch == true) {
			g2.fillRect(textX + textWidthSlash,  textY - 24,  textWidthOff,  textHeight/2 + 7);
		} else if (onOffSwitch == false) {
			g2.fillRect(textX - 4,  textY - 24,  textWidthOn,  textHeight/2 + 7);
		}
		
		// The baseline of the leftmost character is at position (x, y)!!!
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY);
		 // Yellow-orange color		
	
//		int textXtempOn = getXCenteredText("ON /");
//		int textXtempOff = getXCenteredText(" OFF");
//		if (onOffSwitch == false) {
//			g2.fillRoundRect(textX, textY, textXtempOn-2, gp.tileSize, 10, 10);
//		} else if (onOffSwitch == true) {
//			g2.fillRoundRect(textX + textXtempOn + 2, textY, textXtempOff, gp.tileSize, 10, 10);
//		}
		
		
		gp.config.saveConfig();
		
		
	}
	
	public void controls(int frameX, int frameY) {
		
		int textX;
		int textY;
		
		// Title
		String text = "CONTROLS";
		textX = getXCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.setColor(Color.GRAY);
		g2.drawString(text, textX + 1, textY + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, textX, textY); 	
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Attack", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Talk", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Cast", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Inventory", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Inventory Select", textX, textY);
		textY += gp.tileSize;
		
		textX = frameX + (int) (gp.tileSize * 6.5);
		textY = frameY = gp.tileSize * 3;
		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += gp.tileSize;
		g2.drawString("E", textX, textY);
		textY += gp.tileSize;
		g2.drawString("C", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY);
		textY += gp.tileSize;
		
		// BACK
		textX = frameX + gp.tileSize;
		textY = frameY + (int) (gp.tileSize * 7.5);
		g2.drawString("BACK", textX, textY);
		if (commandNum == 0) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", textX - gp.tileSize/2 + 2, textY + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", textX - gp.tileSize/2, textY);
			if (gp.keyH.enterPressed == true) {		
				gp.keyH.enterPressed = false;
				subState = 0;
			}
		}
		
		
	}
	
	
	public int getItemIndex() {
		 int itemIndex = slotCol + (slotRow * 5);		
		 return itemIndex;		
	}
	
	
	public void gameOverScreen() {
		
		
		g2.setColor(new Color(0, 0, 0, 180));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110));
		
		text = "GAME OVER";
		x = getXCenteredText(text);
		y = gp.tileSize * 4;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 4, y + 6);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y); 	
		
		
		// Start over
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "RETRY";
		x = getXCenteredText(text);
		y += gp.tileSize * 4;		
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y); 
		if (commandNum == 0) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize, y);
			if (gp.keyH.enterPressed == true) {		
				gp.keyH.enterPressed = false;
				subState = 0;
			}
		}
		
		
		// Title Screen
		text = "MENU";
		x = getXCenteredText(text);
		y += (int) (gp.tileSize * 1.5);
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y); 
		if (commandNum == 1) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize, y);
			if (gp.keyH.enterPressed == true) {		
				gp.keyH.enterPressed = false;
				subState = 0;
			}
		}
		
		
		
		
		
		
		
		
	}
	
	
public void gameWinScreen() {
		
		
		g2.setColor(new Color(0, 0, 0, 180));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110));
		
		text = "YOU WIN!";
		x = getXCenteredText(text);
		y = gp.tileSize * 4;
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 4, y + 6);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y); 	
		
		
		// Start over
		g2.setFont(g2.getFont().deriveFont(30f));
		text = "LEVEL: " + gp.player.level;
		x = getXCenteredText(text);
		y += gp.tileSize*2;		
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 1, y + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y);
		
		text = "TOTAL EXP: " + gp.player.exp;
		x = getXCenteredText(text);
		y += gp.tileSize;		
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 1, y + 2);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y); 
		
		
		g2.setFont(g2.getFont().deriveFont(50f));
		// Title Screen
		text = "MENU";
		x = getXCenteredText(text);
		y += (int) (gp.tileSize * 4);
		g2.setColor(Color.GRAY);
		g2.drawString(text, x + 2, y + 3);
		g2.setColor(Color.WHITE);
		g2.drawString(text, x, y); 
		if (commandNum == 0) {
			g2.setColor(Color.GRAY);
			g2.drawString(">", x - gp.tileSize + 2, y + 3);
			g2.setColor(Color.WHITE);
			g2.drawString(">", x - gp.tileSize, y);
			if (gp.keyH.enterPressed == true) {		
				gp.keyH.enterPressed = false;
				subState = 0;
			}
		}
		
		
		
		
		
		
		
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0, 0, 0, 200); // R, G, B. 0, 0, 0 is Black
		// Fourth number --> alpha value. Transparency/opacity level. 255 is max
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		// Rectangle with round edges. The last 2 values determine the roundness, with arcWidth, arcHeight
		
		c = new Color(255, 255, 255); // white
		g2.setColor(c);
		g2.setStroke(new BasicStroke(4));
		// Defines the width of outlines of graphics which are rendered with a Graphics2D
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	
	public int getXCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;		
		return x;
	}
	
	public int getXAlignForRight(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length; // Given the end position of the text and the length of the text, the start position is found
		return x;
	}
	

}
