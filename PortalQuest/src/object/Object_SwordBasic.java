package object;

import Main.GamePanel;
import entity.Entity;

public class Object_SwordBasic extends Entity{

	public Object_SwordBasic(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeSword;
		name = "Basic Sword";
		obj_image = setUp("/objects/Sword", gp.tileSize, gp.tileSize);
		attackValue = 1;
		knockBackPower = 1;
		
		description = "[Basic Sword]\nA basic sword for basic \npeople.";
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		
	}

}
