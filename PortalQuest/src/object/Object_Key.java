package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class Object_Key extends Entity{
	
	
	
	
	public Object_Key(GamePanel gp) {
		super(gp);
		name = "Key";
		obj_image = setUp("/objects/Key", gp.tileSize, gp.tileSize);
		isObject = true;
		
		description = "[Key]\nThis can probably open \nsomething.";
		
	}
	
	
}
