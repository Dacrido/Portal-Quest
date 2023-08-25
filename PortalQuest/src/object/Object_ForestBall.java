package object;

import Main.GamePanel;
import entity.Projectile;

public class Object_ForestBall extends Projectile{
	
	GamePanel gp;
	
	public Object_ForestBall(GamePanel gp) { // Projectile is a subclass of entity, and this is a subclass of projectile and entity
		super(gp);
		this.gp = gp;
		
		
		name = "ForestBall";
		speed = 2;
		maxLife = 400;
		life = maxLife;
		attack = 12;
		alive = false;
		isRanged = true;
		solidArea.x = 4;
		solidArea.y = 0;
		solidArea.width = 40;
		solidArea.height = 36;
		getImage();
		
		
	}
	
	public void getImage() {
		
		up1 = setUp("/monsterProjectile/ForestBall1", gp.tileSize*2, gp.tileSize*2);
		up2 = setUp("/monsterProjectile/ForestBall2", gp.tileSize*2, gp.tileSize*2);
		down1 = setUp("/monsterProjectile/ForestBall1", gp.tileSize*2, gp.tileSize*2);
		down2 = setUp("/monsterProjectile/ForestBall2", gp.tileSize*2, gp.tileSize*2);
		left1 = setUp("/monsterProjectile/ForestBall1", gp.tileSize*2, gp.tileSize*2);
		left2 = setUp("/monsterProjectile/ForestBall2", gp.tileSize*2, gp.tileSize*2);
		right1 = setUp("/monsterProjectile/ForestBall1", gp.tileSize*2, gp.tileSize*2);
		right2 = setUp("/monsterProjectile/ForestBall2", gp.tileSize*2, gp.tileSize*2);
		
		
		
		
	}

}
