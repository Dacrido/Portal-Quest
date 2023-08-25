package object;

import Main.GamePanel;
import entity.Entity;


public class Object_AttackPotion extends Entity{
	
	int attackValue = 3;
	GamePanel gp;
	
	public Object_AttackPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		isObject = true;
		type = consumable;
		name = "Attack Potion";
		obj_image = setUp("/objects/AttackPotion", gp.tileSize, gp.tileSize);
		description = "[Attack Potion]\nThis liquid increases your\nattack stats temporarily.";		
	}
	
	public void use(Entity entity) {
		if (gp.player.attackPotionActivated == false) {

			gp.player.attackValue = 3;
			gp.player.getAttack();
			gp.playSoundEffect(3);
			gp.player.attackPotionActivated = true;
			consumableUsed = true;
		} else {
			gp.ui.addMessage("Already in use!");
		}
		
		
		
		
	}

}
