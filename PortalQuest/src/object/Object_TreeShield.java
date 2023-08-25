package object;

import Main.GamePanel;
import entity.Entity;

public class Object_TreeShield extends Entity{

	public Object_TreeShield(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeShield;
		name = "Tree Shield";
		obj_image = setUp("/objects/TreeShield", gp.tileSize, gp.tileSize);
		defenseValue = 2;
		
		description = "[Tree Shield]\nBlessed by the Tree Gods,\nthis block of wood can\nwithstand even the\ntoughest of blows.";
		
		
		
		
	}

}
