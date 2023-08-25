package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class NPC_TreeWizard extends Entity{
	
	public NPC_TreeWizard(GamePanel gp) {
		super(gp);
		
		direction = "DownNone";
		defaultSpeed = 1;
		speed = defaultSpeed;
		type = typeNPC;
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;				
		solidArea.width = 40;
		solidArea.height = 36;
		// same as player for now
		canDialogue = true;
		getImage();
		setDialogue();

	}
	
	
	public void getImage() {
				
		camo = null;
		transition = null;
		up1 = setUp("/npc/TreeWizardUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/npc/TreeWizardUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/npc/TreeWizardDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/npc/TreeWizardDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/npc/TreeWizardLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/npc/TreeWizardLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/npc/TreeWizardRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/npc/TreeWizardRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/npc/TreeWizardStand1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/npc/TreeWizardStand2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/npc/TreeWizardUpStand1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/npc/TreeWizardUpStand2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/npc/TreeWizardLeftStand1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/npc/TreeWizardLeftStand2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/npc/TreeWizardRightStand1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/npc/TreeWizardRightStand2", gp.tileSize, gp.tileSize);
		
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Who are you? What are you doing here?";
		dialogues[1] = "You should not have come! \nThese woods are filled with monsters!";
		dialogues[2] = "Not going to back away eh? \nYou are certainly brave...";
		dialogues[3] = "Well, you may even help and heal the woods of \nthis monstrous plague my kin brought to \nthis world!";
		dialogues[4] = "Hmmph";
		dialogues[5] = "You must travel into the woods filled with horrors \nand monsters. You must stop it's destruction!";
		dialogues[6] = "Good luck adventurer, may you succeed!";
		
		
	}
	
	public void dialogueUpdate() {
		if (direction == "down") {
			direction = "DownNone";
		} else if (direction == "up") {
			direction = "UpNone";
		} else if (direction == "left") {
			direction = "LeftNone";
		} else if (direction == "right") {
			direction = "RightNone";
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum ==  1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void setAction() {
		
		
		int playerX = gp.player.worldX;
		int playerY = gp.player.worldY;
		
		int xMovement = worldX - playerX;
		int yMovement = worldY - playerY;
		
		
		if (Math.abs(xMovement) < 300 && Math.abs(yMovement) < 300 && firstContact == true) {
			searchingPlayer();
		} else {
			actionLockCounter++;
			
			
			
			if (actionLockCounter >= 120) {
				// character's behavior / AI
				// If none in specific class, it will automatically call the one in entity due to inheritence, which allows
				// for same ai in general, but for specific cases, it's own setAction is necessary
				
				Random random = new Random();
			
				int move = random.nextInt(100) + 1;
				
				int i = random.nextInt(100) + 1; // random number from 1 to 100
				
				if (move <= 30) {

					if (direction == "up") {
						direction = "UpNone";
					} else if (direction == "down") {
						direction = "DownNone";
					} else if (direction == "left") {
						direction = "LeftNone";
					} else if (direction == "right") {
						direction = "RightNone";
					}
					
				} else {
					if (i <= 25) {
						direction = "up";

					}
					
					if (i > 25 && i <= 50) {
						direction = "down";
						
					}
					
					if (i > 50 && i <= 75) {
						direction = "left";
						
					}
					
					if (i > 75 && i <= 100) {
						direction = "right";
						
					}
				}

				actionLockCounter = 0;
			}	
			
			
		}
		
		
		
	}
	
	public void searchingPlayer() {
		int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
		int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
		
		searchPath(goalCol, goalRow);
	}
	
	public void searchPath(int goalCol, int goalRow) {
		super.searchPath(goalCol, goalRow, rangedMonster);		
	}	
		
	public void speak() {
		super.speak();	
	}	
		
	
	
	
}
