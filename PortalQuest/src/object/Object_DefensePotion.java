package object;

import Main.GamePanel;
import entity.Entity;


public class Object_DefensePotion extends Entity{
	
	int defenseValue = 3;
	GamePanel gp;
	
	public Object_DefensePotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		isObject = true;
		type = consumable;
		name = "Defense Potion";
		obj_image = setUp("/objects/DefensePotion", gp.tileSize, gp.tileSize);
		description = "[Defense Potion]\nOnly the toughest can \ndrink pure lead. Increases\ndefensive stats \ntemporarily.";		
	}
	
	public void use(Entity entity) {
		if (gp.player.defensePotionActivated == false) {

			gp.player.defenseValue = 3;
			gp.player.getDefense();
			gp.playSoundEffect(3);
			gp.player.defensePotionActivated = true;
			consumableUsed = true;
		} else {
			gp.ui.addMessage("Already in use!");
		}
		
		
		
		
	}

}
