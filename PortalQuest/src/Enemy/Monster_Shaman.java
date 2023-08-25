package Enemy;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;
import object.Object_AttackPotion;
import object.Object_DefensePotion;
import object.Object_ForestBall;
import object.Object_HealthPotion;
import object.Object_Key;
import object.Object_Laser;
import object.Object_ManaPotion;
import object.Object_PoisonPotion;
import object.Object_SpeedPotion;

public class Monster_Shaman extends Entity{
	
	GamePanel gp;
	public Monster_Shaman(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = typeMonster;
		name = "Shaman";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 16;
		
		
		life = maxLife;
		attack = 12;
		defense = 6; // If defense is 1, player will not be able to do any damage at the very start
		exp = 20; // exp per monster
		projectile = new Object_ForestBall(gp);
		rangedMonster = true;
		
		solidArea.x = 4;
		solidArea.y = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;				
		solidArea.width = 40;
		solidArea.height = 40;
		getImage();
		direction = "DownNone";
		spawnCounter = 1800;
		
	}
	
	public void getImage() {
		
		up1 = setUp("/Monster/ShamanUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Monster/ShamanUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Monster/ShamanDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Monster/ShamanDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Monster/ShamanLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Monster/ShamanLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Monster/ShamanRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Monster/ShamanRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/Monster/ShamanDownStand1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/Monster/ShamanDownStand2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/Monster/ShamanUpStand1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/Monster/ShamanUpStand2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/Monster/ShamanLeftStand1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/Monster/ShamanLeftStand2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/Monster/ShamanRightStand1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/Monster/ShamanRightStand2", gp.tileSize, gp.tileSize);
	
		
	}
	
	public void setAction() {
		
		
		actionLockCounter++;
		int playerX = gp.player.worldX;
		int playerY = gp.player.worldY;
		
		int xMovement = worldX - playerX;
		int yMovement = worldY - playerY;
		
		
		if (Math.abs(xMovement) < 250 && Math.abs(yMovement) < 250 && lockedOn == false) {
			lockedOn = true;
			
			spawnTreeGhosts();
			
		} else {
			
			if (lockedOn == true) {
				searchingPlayer();
				lockCounter++;
				spawnCounter++;
				if (lockCounter >= 300) {
					lockedOn = false;
					lockCounter = 0;
				}
			} 
			
			if (actionLockCounter >= 120 && lockedOn == false) {
				spawnCounter++;
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
		shotAvailableCounter = 0;
		// Checking Vacancy
		for (int i = 0; i < gp.projectile[1].length; i++) {
			if (gp.projectile[gp.currentMap][i] == null) {
				gp.projectile[gp.currentMap][i] = projectile;
				gp.playSoundEffect(18);
				break;
			}
		}
		
		
		
	}	

	public void checkDrop() {
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		
		if (i <= 10) {
			//nothing
		} else if (i > 10 && i <= 100) {
			
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
	
	public void spawnTreeGhosts() {
		
		
		if (spawnCounter >= 1800) {
			
			gp.aSetter.spawnMonster(this);
			spawnCounter = 0;
		}
		
		spawnCounter++;
		
				
	}


	
	
	
	
	
}

