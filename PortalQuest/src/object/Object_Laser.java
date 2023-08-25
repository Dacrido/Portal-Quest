package object;

import Main.GamePanel;
import entity.Projectile;

public class Object_Laser extends Projectile{
	
	GamePanel gp;
	
	public Object_Laser(GamePanel gp) { // Projectile is a subclass of entity, and this is a subclass of projectile and entity
		super(gp);
		this.gp = gp;
		
		
		name = "Laser";
		speed = 5;
		maxLife = 100;
		life = maxLife;
		attack = 4;
		alive = false;
		isRanged = true;
		solidArea.x = 8;
		solidArea.y = 0;
		solidArea.width = 32;
		solidArea.height = 36;
		getImage();
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/monsterProjectile/LaserUp1", gp.tileSize, gp.tileSize);
		up2 = setUp("/monsterProjectile/LaserUp2", gp.tileSize, gp.tileSize);
		down1 = setUp("/monsterProjectile/LaserDown1", gp.tileSize, gp.tileSize);
		down2 = setUp("/monsterProjectile/LaserDown2", gp.tileSize, gp.tileSize);
		left1 = setUp("/monsterProjectile/LaserLeft1", gp.tileSize, gp.tileSize);
		left2 = setUp("/monsterProjectile/LaserLeft2", gp.tileSize, gp.tileSize);
		right1 = setUp("/monsterProjectile/LaserRight1", gp.tileSize, gp.tileSize);
		right2 = setUp("/monsterProjectile/LaserRight2", gp.tileSize, gp.tileSize);
		
		
		
		
	}

}
