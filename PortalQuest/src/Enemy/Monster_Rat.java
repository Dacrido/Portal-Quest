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

public class Monster_Rat extends Entity{
	
	GamePanel gp;
	public Monster_Rat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = typeMonster;
		name = "Rat";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 3;
		life = maxLife;
		attack = 3;
		defense = 0; // If defense is 1, player will not be able to do any damage at the very start
		exp = 1; // exp per monster
		
		solidArea.x = 2;
		solidArea.y = 6;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;				
		solidArea.width = 44;
		solidArea.height = 30;
		getImage();
		direction = "DownNone";
		
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/Monster/RatUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/Monster/RatUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/Monster/RatDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/Monster/RatDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/Monster/RatLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/Monster/RatLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/Monster/RatRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/Monster/RatRight2", gp.tileSize, gp.tileSize);
		stand1 = setUp("/Monster/RatDown1", gp.tileSize, gp.tileSize);
		stand2 = setUp("/Monster/RatDown2", gp.tileSize, gp.tileSize);
		upStand1 = setUp("/Monster/RatUp1", gp.tileSize, gp.tileSize);
		upStand2 = setUp("/Monster/RatUp2", gp.tileSize, gp.tileSize);
		leftStand1 = setUp("/Monster/RatLeft1", gp.tileSize, gp.tileSize);
		leftStand2 = setUp("/Monster/RatLeft2", gp.tileSize, gp.tileSize);
		rightStand1 = setUp("/Monster/RatRight1", gp.tileSize, gp.tileSize);
		rightStand2 = setUp("/Monster/RatRight2", gp.tileSize, gp.tileSize);
	
		
	}
	
	public void setAction() {
		
		
			
		if (lockedOn == true) {
			searchingPlayer();
			lockCounter++;
			
			if (lockCounter >= 1200) {
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
	
	public void damageReaction() {
		lockCounter = 0;
		for (int i = 0; i < gp.monster[1].length; i++) {
			
			
			if (gp.monster[gp.currentMap][i] != null) {
				if (gp.monster[gp.currentMap][i].name == "Rat") {
					int xMovement = gp.monster[gp.currentMap][i].worldX - gp.player.worldX;
					int yMovement = gp.monster[gp.currentMap][i].worldY - gp.player.worldY;
					
					if (Math.abs(xMovement) < 400 && Math.abs(yMovement) < 400) {
						gp.monster[gp.currentMap][i].lockedOn = true;
						gp.monster[gp.currentMap][i].lockCounter = 0;
					}	
				}
			}
			
			
		}
	
	}
	

	
	public void checkDrop() {
		
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		
		// Item drop chance
		if (i <= 95) {
			//nothing
		} else if (i > 95 && i <= 100) {
			
			int q = random.nextInt(100) + 1;
			
			if (q > 0 && q <= 20) {
				dropItem(new Object_HealthPotion(gp));
			} else if (q > 20 && q <= 40) {
				dropItem(new Object_SpeedPotion(gp));
			} else if (q > 40 && q < 60) {
				dropItem(new Object_DefensePotion(gp));
			} else if (q >= 60 && q < 70) {
				dropItem(new Object_AttackPotion(gp));
			} else if (q > 70 && q <= 90) {
				dropItem(new Object_PoisonPotion(gp));
			} else if (q > 90 && q <= 100) {
				dropItem(new Object_ManaPotion(gp));
			}
			
			
		}
		
		
		
		
	}


	
	
	
	
	
}

