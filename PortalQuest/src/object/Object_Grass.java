package object;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_Grass extends Entity{
	
	
	GamePanel gp;
	
	public Object_Grass(GamePanel gp) {
		super(gp);
		this.gp = gp;
		obj_image = setUp("/Monster/FlowerMonsterCamo", gp.tileSize, gp.tileSize);
		isObject = true;
		name = "Grass";
		
	}
	
	public void checkDrop() {
		
		Random random = new Random();
		int i = random.nextInt(100) + 1;
		
		if (guarantee == false) {
			// Item drop chance
			if (i <= 60) {
				//nothing
			} else if (i > 60 && i <= 100) {
				
				int q = random.nextInt(100) + 1;
				
				if (q > 0 && q <= 20) {
					dropItem(new Object_HealthPotion(gp));
				} else if (q > 20 && q <= 45) {
					dropItem(new Object_SpeedPotion(gp));
				} else if (q > 45 && q < 60) {
					dropItem(new Object_DefensePotion(gp));
				} else if (q >= 60 && q < 75) {
					dropItem(new Object_AttackPotion(gp));
				} else if (q > 75 && q <= 90) {
					dropItem(new Object_PoisonPotion(gp));
				} else if (q > 90 && q <= 100) {
					dropItem(new Object_ManaPotion(gp));
				}
				
				
			}
		} else {
			int q = random.nextInt(100) + 1;
			
			if (q > 0 && q <= 20) {
				dropItem(new Object_HealthPotion(gp));
			} else if (q > 20 && q <= 45) {
				dropItem(new Object_SpeedPotion(gp));
			} else if (q > 45 && q < 60) {
				dropItem(new Object_DefensePotion(gp));
			} else if (q >= 60 && q < 75) {
				dropItem(new Object_AttackPotion(gp));
			} else if (q > 75 && q <= 90) {
				dropItem(new Object_PoisonPotion(gp));
			} else if (q > 90 && q <= 100) {
				dropItem(new Object_ManaPotion(gp));
			}
		}
		
		
		
		
		
		
		
	}
	
	
}
