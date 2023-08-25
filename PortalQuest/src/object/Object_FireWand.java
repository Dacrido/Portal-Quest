package object;

import Main.GamePanel;
import entity.Entity;

public class Object_FireWand extends Entity{

	public Object_FireWand(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeWand;
		name = "Fire Wand";
		obj_image = setUp("/objects/FireWand", gp.tileSize, gp.tileSize);
		
		description = "[Fire Wand]\nThe devastation this \ncaused throughout the\ncenturies is unimaginable. \nIn fact, non-existent.";
		
		
		
	}

}
