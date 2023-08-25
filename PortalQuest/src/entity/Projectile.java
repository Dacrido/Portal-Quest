package entity;

import Main.GamePanel;

public class Projectile extends Entity{

	Entity user;

	public Projectile(GamePanel gp) {
		super(gp);
		
	}
	
	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		// Last user parameter is only if other entities like monsters can also shoot projectiles
		
		this.worldX = worldX;
		this.worldY = worldY;
		
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
		
		if (direction == "UpNone") {
			direction = "up";
		} else if (direction == "DownNone") {
			direction = "down";
		} else if (direction == "LeftNone") {
			direction = "left";
		} else if (direction == "RightNone") {
			direction = "right";
		}
		this.direction = direction;
		
	}
	
	public void update() {
		
//		collisionOn = false;
//		gp.cChecker.checkTile(this);
//		if (collisionOn == true && life <= (int) maxLife*0.9) {
//			alive = false;
//		}
//		collisionOn = false;
		// Check whether user is monster or player
		
		if (user == gp.player) {
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);	
			
			if (monsterIndex != 999) {
				gp.player.damageMonster(monsterIndex, attack, isRanged, stun, knockBackPower); // Not player's attack, but the projectile's attack
				alive = false;
			}
			
		} else {
			boolean contactPlayer = gp.cChecker.checkPlayer(this);
			
			if (gp.player.tookDamage == false && contactPlayer == true) {
				damagePlayer(attack);
				alive = false;
			}
			
			
		}
		switch(direction) {
		case "up": worldY -= speed; break;
		case "down": worldY += speed; break;
		case "left": worldX -= speed; break;
		case "right": worldX += speed; break;
		}
		life--;		
		
		if (life <= 0 || alive == false) {
			alive = false;
//			gp.projectileList.remove(this);
			
			for (int i = 0; i < gp.projectile[1].length; i++) {
				if (gp.projectile[gp.currentMap][i] != null) {
					if (gp.projectile[gp.currentMap][i] == this) {
						gp.projectile[gp.currentMap][i] = null;
						break;
					}
				}
			}
			
		}
		
		spriteCounter++;
		if (spriteCounter > 12) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;	 
		}
		
	}

}
