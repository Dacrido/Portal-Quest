package object;

import Main.GamePanel;
import entity.Entity;

public class Object_BubbleWand extends Entity{

	public Object_BubbleWand(GamePanel gp) {
		super(gp);
		isObject = true;
		type = typeWand;
		name = "Bubble Wand";
		obj_image = setUp("/objects/BubbleWand", gp.tileSize, gp.tileSize);
		
		description = "[Bubble Wand]\nHow can bubbles hurt\nmonsters you ask? They're\nallergic to the smell!";
		
		
		
	}

}
