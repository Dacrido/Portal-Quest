package Enemy;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;
import object.Object_AttackPotion;
import object.Object_DefensePotion;
import object.Object_HealthPotion;
import object.Object_LeafWand;
import object.Object_ManaPotion;
import object.Object_PoisonPotion;
import object.Object_SpeedPotion;

public class Monster_TreeGhost extends Entity{
	
	GamePanel gp;
	public Monster_TreeGhost(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = typeMonster;
		name = "TreeGhost";
		defaultSpeed = 3;
		speed = defaultSpeed;
		maxLife = 6;
		life = maxLife;
		attack = 8;
		defense = 4; // If defense is 1, player will not be able to do any damage at the very start
		exp = 8; // exp per monster
		
		solidArea.x = 2;
		solidArea.y = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;				
		solidArea.width = 44;
		solidArea.height = 36;
		getImage();
		direction = "DownNone";
		
		
		
	}
	
	public void getImage() {
		
		
		up1 = setUp("/Monster/TreeGhostUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Monster/TreeGhostUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Monster/TreeGhostDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Monster/TreeGhostDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Monster/TreeGhostLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Monster/TreeGhostLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Monster/TreeGhostRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Monster/TreeGhostRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/Monster/TreeGhostDown1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/Monster/TreeGhostDown2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/Monster/TreeGhostUp1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/Monster/TreeGhostUp2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/Monster/TreeGhostLeft1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/Monster/TreeGhostLeft2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/Monster/TreeGhostRight1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/Monster/TreeGhostRight2", gp.tileSize, gp.tileSize);
	
		
	}
	
	public void setAction() {
		
		
		int xRightBorder = worldX + 300;
		int xLeftBorder = worldX - 300;
		int yTopBorder = worldY - 300;
		int yBottomBorder = worldY + 300;
		
		if (spawnAnimation == true) {
			
		}
		
		else if (gp.player.worldX > xLeftBorder && gp.player.worldX < xRightBorder && gp.player.worldY < yBottomBorder &&
				gp.player.worldY > yTopBorder && lockedOn == false) {
			
			lockedOn = true;
			searchingPlayer();
			
									
			
			
		} else {
			
			if (lockedOn == true) {
				searchingPlayer();
				lockCounter++;
				
				if (lockCounter >= 240) {
					lockedOn = false;
					lockCounter = 0;
				}
			} 
			
			if (actionLockCounter >= 120 && lockedOn == false) {
				
				Random random = new Random();
				int i = random.nextInt(100) + 1;
				
				if (i <= 50) {
					
					int a = random.nextInt(100) + 1;
					
					if (a <= 25) {
						direction = "up";
					} else if (a > 25 && a <= 50) {
						 direction = "down";
					} else if (a > 50 && a <= 75) {
						 direction = "left";
					} else if (a > 75 && a <= 100) {
						direction = "right";
					}
					
					
				} else if (i > 50 && i <= 100) {
					
					int a = random.nextInt(100) + 1;
					
					if (a <= 25) {
						direction = "UpNone";
					} else if (a > 25 && a <= 50) {
						 direction = "DownNone";
					} else if (a > 50 && a <= 75) {
						 direction = "LeftNone";
					} else if (a > 75 && a <= 100) {
						direction = "RightNone";
					}
					
					
					
					
				}
				
				actionLockCounter = 0;			
			}
			
			
			
			
		}
		actionLockCounter++;
		
	}

	public void searchingPlayer() {
		int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
		int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
		
		searchPath(goalCol, goalRow);
	}
	
	public void searchPath(int goalCol, int goalRow) {
		super.searchPath(goalCol, goalRow, rangedMonster);
	}

	

	
	public void checkDrop() {
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		
		// Item drop chance
		if (i <= 60) {
			//nothing
		} else if (i > 60 && i <= 100) {
			
			int q = random.nextInt(100) + 1;
						
			if (q > 0 && q <= 10) {
				dropItem(new Object_HealthPotion(gp));
			} else if (q > 10 && q <= 40) {
				dropItem(new Object_SpeedPotion(gp));
			} else if (q > 40 && q < 60) {
				dropItem(new Object_DefensePotion(gp));
			} else if (q >= 60 && q < 75) {
				dropItem(new Object_AttackPotion(gp));
			} else if (q > 75 && q <= 80) {
				dropItem(new Object_PoisonPotion(gp));
			} else if (q > 80 && q <= 100) {
				dropItem(new Object_ManaPotion(gp));
			}
			
			
		}
		
		
		
		
		
	}


	
	
	
	
	
}

