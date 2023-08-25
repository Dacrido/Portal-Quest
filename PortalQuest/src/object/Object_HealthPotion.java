package object;

import Main.GamePanel;
import entity.Entity;


public class Object_HealthPotion extends Entity{
	
	int healValue = 5;
	GamePanel gp;
	
	public Object_HealthPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		isObject = true;
		type = consumable;
		name = "Health Potion";
		obj_image = setUp("/objects/HealthPotion", gp.tileSize, gp.tileSize);
		description = "[Health Potion]\nHeals your life by " + healValue + ".";		
	}
	
	public void use(Entity entity) {
		
		gp.gameState = gp.dialogueState;
		gp.eHandler.dialogueEvent = true;
		gp.ui.currentDialogue = "You gained " + healValue + " health!";
		entity.life += healValue;
		
		if (gp.player.life > gp.player.maxLife) {
			gp.player.life = gp.player.maxLife;
		}
		consumableUsed = true;
		gp.playSoundEffect(3);
	}

}
