package Main;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	EventRect eventRect[][];
	public boolean dialogueEvent = false;
	
	
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;	
		eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventRect(); // Small 2pixels by 2 pixels rect at the center of a tile
			eventRect[col][row].x = 23;
			eventRect[col][row].y = 23;
			eventRect[col][row].width = 8;
			eventRect[col][row].height = 8;
			eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;
			// Very small rectangle so that event is not triggered when player touches a tile, only when entering a specific tile
			
			
			col++;
			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
			
			
		}
		
		
			
	}
	
	public void checkEvent() {
		
		//example: damage pit at 29, 18
//		if (hit(1, 29, 18, "any") == true) { damagePit(1, 29, 18, gp.playState);}
//		if (hit(1, 29, 26, "any") == true) { healingPool(1, 29, 26, gp.dialogueState);}
		
	}
	
	public boolean hit(int map, int col, int row, String requiredDirection) {
		
		boolean hit = false;
		
		if (gp.currentMap == map) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
			eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
			
			if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
				
				if (gp.player.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
					hit = true;
				}
				
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
			eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
		}
		
		
		
		
		
		
		return hit;
		
	}
	
	public void damagePit(int map, int col, int row, int gameState) {
		
//		gp.gameState = gp.dialogueState;
		dialogueEvent = false;
		if (gp.player.tookDamage == false) {
			gp.playSoundEffect(7);
			gp.player.life--;			
			gp.player.tookDamage = true;
//			eventRect[col][row].eventDone = true;
			
		}
		
		
		
	}
	
	public void healingPool(int map, int col, int row, int gameState) {
		
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCancel = true;
			gp.ui.currentDialogue = "You drink the water!\nYou gained some health!";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			dialogueEvent = true;
			gp.aSetter.setMonster(); // respawns monsters when at healing pool
		}
		
		gp.keyH.enterPressed = false;
		
	}
	

	
	
}
