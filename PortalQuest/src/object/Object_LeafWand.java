package object;

import Main.GamePanel;
import entity.Entity;

public class Object_LeafWand extends Entity{

	public Object_LeafWand(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeWand;
		name = "Leaf Wand";
		obj_image = setUp("/objects/LeafStunWand", gp.tileSize, gp.tileSize);
		description = "[Leaf Wand]\nStuns monsters for 5\nseconds. How? Leaves!";
		
		
		
	}

}
