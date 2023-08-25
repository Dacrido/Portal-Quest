package Enemy;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;
import object.Object_AttackPotion;
import object.Object_DefensePotion;
import object.Object_HealthPotion;
import object.Object_ManaPotion;
import object.Object_PoisonPotion;
import object.Object_SpeedPotion;

public class Monster_FlowerMonster extends Entity{
	
	GamePanel gp;
	public Monster_FlowerMonster(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = typeMonster;
		name = "FlowerMonster";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 6;
		life = maxLife;
		attack = 6;
		defense = 0; // If defense is 1, player will not be able to do any damage at the very start
		exp = 3; // exp per monster
		
		solidArea.x = 2;
		solidArea.y = 12;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;				
		solidArea.width = 44;
		solidArea.height = 36;
		canTransform = true;
		getImage();
		direction = "camo";
		
		
		
	}
	
	public void getImage() {
		
		camo = setUp("/Monster/FlowerMonsterCamo", gp.tileSize, gp.tileSize);
		transition = setUp("/Monster/FlowerMonsterTransition", gp.tileSize, gp.tileSize);
		up1 = setUp("/Monster/FlowerMonsterUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Monster/FlowerMonsterUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Monster/FlowerMonsterDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Monster/FlowerMonsterDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Monster/FlowerMonsterLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Monster/FlowerMonsterLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Monster/FlowerMonsterRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Monster/FlowerMonsterRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/Monster/FlowerMonsterStand1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/Monster/FlowerMonsterStand2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/Monster/FlowerMonsterUpStand1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/Monster/FlowerMonsterUpStand2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/Monster/FlowerMonsterLeftStand1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/Monster/FlowerMonsterLeftStand2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/Monster/FlowerMonsterRightStand1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/Monster/FlowerMonsterRightStand2", gp.tileSize, gp.tileSize);
	
		
	}
	
	public void setAction() {
		
		
		int xRightBorder = worldX + 150;
		int xLeftBorder = worldX - 150;
		int yTopBorder = worldY - 150;
		int yBottomBorder = worldY + 150;
		
		
		if (gp.player.worldX > xLeftBorder && gp.player.worldX < xRightBorder && gp.player.worldY < yBottomBorder &&
				gp.player.worldY > yTopBorder) {
			
			
			if (direction == "camo") {
				transformed = true;
			} else if (transformed == false) {
				lockedOn = true;
				lockCounter = 0;
				if (ambush == true) {
					ambush = false;
					ambush();
				}
				
				searchingPlayer();
			}						
			
			
		} else {
			
			if (lockedOn == true) {
				searchingPlayer();
				lockCounter++;
				
				if (lockCounter >= 1200) {
					lockedOn = false;
					lockCounter = 0;
					transformed = true;
				}
			} 
			
			
			if (transformed == false && direction == "transition") {
				direction = "camo";
			}
			
			
			
			
		}
//		actionLockCounter++;
		
	}

	public void searchingPlayer() {
		int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
		int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
		
		searchPath(goalCol, goalRow);
	}
	
	public void searchPath(int goalCol, int goalRow) {
		super.searchPath(goalCol, goalRow, rangedMonster);
	}

	public void ambush() {
		
		for (int i = 0; i < gp.monster[1].length; i++) {
			
			if (gp.monster[gp.currentMap][i] != null) {
				if (gp.monster[gp.currentMap][i].name == "FlowerMonster" && gp.monster[gp.currentMap][i].ambush == true) {
					gp.monster[gp.currentMap][i].lockedOn = true;	
				}
			}
			
			
		}
		
		
	}

//	public void flowerMonsterMovement() {
//		actionLockCounter++;
//		int playerX = gp.player.worldX;
//		int playerY = gp.player.worldY;
//		
//		int xMovement = worldX - playerX;
//		int yMovement = worldY - playerY;
//		
//		// ActionLockCounter
//		// collisionOn --> general collision
//
//		if (actionLockCounter >= 120) {
//			
//			Random random = new Random();
//			int movement = random.nextInt(100) + 1;
//			
//			if (movement <= 50) {
//				if (xMovement > 0) {
//					direction = "left";
//				} else if (xMovement < 0) {
//					direction = "right";
//				}
//				
//				if (yMovement > 0) {
//					direction = "up";
//				} else if (yMovement < 0) {
//					direction ="down";
//				}
//			} else {
//				
//				if (yMovement > 0) {
//					direction = "up";
//				} else if (yMovement < 0) {
//					direction ="down";
//				}
//				
//				if (xMovement > 0) {
//					direction = "left";
//				} else if (xMovement < 0) {
//					direction = "right";
//				}
//				
//			}
//			
//			
//			actionLockCounter = 0;
//		}
//		
//		
//		
//		if (direction == "left" || direction == "right") {
//			if (xMovement == 0) {
//				if (yMovement > 0) {
//					direction = "up";
//				} else if (yMovement < 0) {
//					direction ="down";
//				}
//			}
//		}
//		
//		if (direction == "up" || direction == "down" ) {
//			if (yMovement == 0) {
//				if (xMovement > 0) {
//					direction = "left";
//				} else if (xMovement < 0) {
//					direction = "right";
//				}
//			}
//		}
//		 
//		
//		 if (collisionOn == true) {
//			if (direction == "left" || direction == "right") {
//				if (yMovement > 0) {
//					direction = "up";
//				} else if (yMovement < 0) {
//					direction ="down";
//				}
//	
//			}
//			
//			if (direction == "up" || direction == "down" ) {
//	
//				if (xMovement > 0) {
//					direction = "left";
//				} else if (xMovement < 0) {
//					direction = "right";
//				}
//				
//			}
//		}
//	
//	}
	
	public void checkDrop() {
		
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		
		// Item drop chance
		if (i <= 80) {
			//nothing
		} else if (i > 80 && i <= 100) {
			
			int q = random.nextInt(100) + 1;
			
			if (q > 0 && q <= 20) {
				dropItem(new Object_HealthPotion(gp));
			} else if (q > 20 && q <= 40) {
				dropItem(new Object_SpeedPotion(gp));
			} else if (q > 40 && q < 60) {
				dropItem(new Object_DefensePotion(gp));
			} else if (q >= 60 && q < 80) {
				dropItem(new Object_AttackPotion(gp));
			} else if (q > 80 && q <= 90) {
				dropItem(new Object_PoisonPotion(gp));
			} else if (q > 90 && q <= 100) {
				dropItem(new Object_ManaPotion(gp));
			}
			
			
		}
		
		
		
		
	}


	
	
	
	
	
}
