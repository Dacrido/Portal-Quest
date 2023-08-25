package Enemy;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;
import object.Object_AttackPotion;
import object.Object_DefensePotion;
import object.Object_HealthPotion;
import object.Object_Laser;
import object.Object_ManaPotion;
import object.Object_PoisonPotion;
import object.Object_SpeedPotion;

public class Monster_TreeEye extends Entity{
	
	GamePanel gp;
	public Monster_TreeEye(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = typeMonster;
		name = "TreeEye";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 4;
		life = maxLife;
		attack = 4;
		defense = 1; // If defense is 1, player will not be able to do any damage at the very start
		exp = 4; // exp per monster
		projectile = new Object_Laser(gp);
		rangedMonster = true;
		
		solidArea.x = 4;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;				
		solidArea.width = 40;
		solidArea.height = 32;
		getImage();
		direction = "down";
		
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/Monster/TreeEyeUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Monster/TreeEyeUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Monster/TreeEyeDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Monster/TreeEyeDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Monster/TreeEyeLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Monster/TreeEyeLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Monster/TreeEyeRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Monster/TreeEyeRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/Monster/TreeEyeDown1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/Monster/TreeEyeDown2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/Monster/TreeEyeUp1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/Monster/TreeEyeUp2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/Monster/TreeEyeLeft1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/Monster/TreeEyeLeft2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/Monster/TreeEyeRight1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/Monster/TreeEyeRight2", gp.tileSize, gp.tileSize);
	
		
	}
	
	public void setAction() {
		
		
		actionLockCounter++;
		int playerX = gp.player.worldX;
		int playerY = gp.player.worldY;
		
		int xMovement = worldX - playerX;
		int yMovement = worldY - playerY;
		
		
		
		if (Math.abs(xMovement) < 300 && Math.abs(yMovement) < 300 && lockedOn == false) {			
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
	
	
	
	
	public void setProjectile() {
		projectile.set(worldX, worldY, direction, true, this);
//		gp.projectileList.add(projectile);
		shotAvailableCounter = 0;
		// Checking Vacancy
		for (int i = 0; i < gp.projectile[1].length; i++) {
			if (gp.projectile[gp.currentMap][i] == null) {
				gp.projectile[gp.currentMap][i] = projectile;
				gp.playSoundEffect(17);
				break;
			}
		}
		
		
		
	}

//	public void treeEyeMovement(int xMovement, int yMovement) {
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
//		if (xMovement < 10 && xMovement > -10) {
//			if (yMovement > 0) {
//				direction = "UpNone";
//				if (projectile.alive == false && shotAvailableCounter == 45) {
//					setProjectile();
//				}
//				
//			} else if (yMovement < 0) {
//				direction ="DownNone";
//				if (projectile.alive == false && shotAvailableCounter == 45) {
//					setProjectile();
//				}
//				
//			}
//		}
//		
//		if (yMovement < 10 && yMovement > -10) {
//			if (xMovement > 0) {
//				direction = "LeftNone";
//				if (projectile.alive == false && shotAvailableCounter == 45) {
//					setProjectile();
//				}
//			} else if (xMovement < 0) {
//				direction = "RightNone";
//				if (projectile.alive == false && shotAvailableCounter == 45) {
//					setProjectile();
//				}
//			}
//		}
//		 
//		
//		 if (collisionOn == true) {
//			if (direction == "left" || direction == "right") {
//				if (yMovement > 0) {
//					direction = "down";
//				} else if (yMovement < 0) {
//					direction ="up";
//				}
//	
//			}
//			
//			if (direction == "up" || direction == "down" ) {
//	
//				if (xMovement > 0) {
//					direction = "right";
//				} else if (xMovement < 0) {
//					direction = "left";
//				}
//				
//			}
//		}
//	}

	

	public void checkDrop() {
		
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		
		// Item drop chance
		if (i <= 70) {
			//nothing
		} else if (i > 70 && i <= 100) {
			
			int q = random.nextInt(100) + 1;
			
			if (q > 0 && q <= 15) {
				dropItem(new Object_HealthPotion(gp));
			} else if (q > 15 && q <= 35) {
				dropItem(new Object_SpeedPotion(gp));
			} else if (q > 35 && q < 50) {
				dropItem(new Object_DefensePotion(gp));
			} else if (q >= 50 && q < 65) {
				dropItem(new Object_AttackPotion(gp));
			} else if (q > 65 && q <= 75) {
				dropItem(new Object_PoisonPotion(gp));
			} else if (q > 75 && q <= 100) {
				dropItem(new Object_ManaPotion(gp));
			}
			
			
		}
		
		
		
		
	}


	
	
	
	
	
}

