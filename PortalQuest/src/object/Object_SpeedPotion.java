package object;

import Main.GamePanel;
import entity.Entity;


public class Object_SpeedPotion extends Entity{
	
	int speedValue = 2;
	GamePanel gp;
	
	public Object_SpeedPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		isObject = true;
		type = consumable;
		name = "Speed Potion";
		obj_image = setUp("/objects/SpeedPotion", gp.tileSize, gp.tileSize);
		description = "[Speed Potion]\nMakes you move faster\ntemporarily.";		
	}
	
	public void use(Entity entity) {
		if (gp.player.speedPotionActivated == false) {
			gp.player.speed += speedValue;
			gp.playSoundEffect(3);
			gp.player.speedPotionActivated = true;
			consumableUsed = true;
		} else {
			gp.ui.addMessage("Already in use!");
		}
		
	}

}
