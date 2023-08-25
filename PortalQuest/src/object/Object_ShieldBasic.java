package object;

import Main.GamePanel;
import entity.Entity;

public class Object_ShieldBasic extends Entity{

	public Object_ShieldBasic(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeShield;
		name = "Basic Shield";
		obj_image = setUp("/objects/Shield", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		
		description = "[Basic Shield]\nWeak shield. Still better \nthan none.";
		
	}

}
