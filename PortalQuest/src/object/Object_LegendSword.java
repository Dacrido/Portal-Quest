package object;

import Main.GamePanel;
import entity.Entity;

public class Object_LegendSword extends Entity{

	public Object_LegendSword(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeSword;
		name = "Legendary Sword";
		obj_image = setUp("/objects/LegendarySword", gp.tileSize, gp.tileSize);
		attackValue = 3;
		knockBackPower = 3;
		
		description = "[Legendary Sword]\nThey say only kings could \nwield a sword of such \nimmense power. They \nalso lie.";
		
		attackArea.width = 52;
		attackArea.height = 52;
		
		
	}

}
