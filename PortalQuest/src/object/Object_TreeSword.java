package object;

import Main.GamePanel;
import entity.Entity;

public class Object_TreeSword extends Entity{

	public Object_TreeSword(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeSword;
		name = "Tree Sword";
		obj_image = setUp("/objects/TreeSword", gp.tileSize, gp.tileSize);
		attackValue = 2;
		knockBackPower = 2;
		
		description = "[Tree Sword]\nBlessed by the Tree Gods,\nthis sword gives anyone a \nsense of power.";
		
		attackArea.width = 44;
		attackArea.height = 44;
		
		
	}

}
