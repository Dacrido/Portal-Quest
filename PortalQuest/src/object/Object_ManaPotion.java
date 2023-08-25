package object;

import Main.GamePanel;
import entity.Entity;


public class Object_ManaPotion extends Entity{
	
	int attackValue = 3;
	GamePanel gp;
	
	public Object_ManaPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isObject = true;
		type = consumable;
		name = "Mana Potion";
		obj_image = setUp("/objects/ManaPotion", gp.tileSize, gp.tileSize);
		description = "[Mana Potion]\nThis purple goo increases\nyour mana recharge speed.\nDon't ask how.";		
	}
	
	public void use(Entity entity) {
		if (gp.player.manaPotionActivated == false) {

			gp.player.currentManaRechargeValue = 40;
			gp.playSoundEffect(3);
			gp.player.manaPotionActivated = true;
			consumableUsed = true;
		} else {
			gp.ui.addMessage("Already in use!");
		}
		
		
		
		
	}

}