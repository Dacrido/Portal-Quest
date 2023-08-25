package object;

import Main.GamePanel;
import entity.Entity;


public class Object_PoisonPotion extends Entity{
	
	
	GamePanel gp;
	
	public Object_PoisonPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isObject = true;
		type = consumable;
		name = "Poison Potion";
		obj_image = setUp("/objects/PoisonPotion", gp.tileSize, gp.tileSize);
		description = "[Poison Potion]\nPouring this liquid on\nyour blade will guarantee\nto poison anyone \nyou hit.";		
	}
	
	public void use(Entity entity) {
		if (gp.player.poisonPotionActivated == false) {

			gp.player.poisonValue = 2;
			gp.playSoundEffect(3);
			gp.player.poisonPotionActivated = true;
			consumableUsed = true;
		} else {
			gp.ui.addMessage("Already in use!");
		}
		
		
		
		
	}

}
